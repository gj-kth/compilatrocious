package mjc.parser;

import java.util.*;
import java.lang.*;
import mjc.parser.VisitorUtil.*;

public class JVMVisitor extends VisitorAdapter{
	
	private SymbolTable symbolTable;

	//Keeps track of how many branches there are so that every branch gets
	//unique labels, like [else_0 and done_0], [else_1 and done_1]
	private int globalNumBranches = 0; 
	

	public JVMVisitor(SymbolTable symbolTable){
		this.symbolTable = symbolTable;	
	}

	public Object visit(ASTIdentifier node, Object data){
		if(data instanceof IdentifierInput){
			IdentifierInput input = (IdentifierInput) data;
			switch(input.usage){
				case IdentifierInput.STORE:
					return storeVar(input.context, getVal(node));// Value on stack, store it in var
				case IdentifierInput.GET_NAME:
					return getVal(node);
				default:
					throw new IllegalStateException("");
			}
		}else{
			return loadVar((JVMContext)data, getVal(node));// Push value in var to stack
		}
		
	}

	//
	public Object visit(ASTProgram node, Object data){
		SimpleNode mainClass = (SimpleNode) node.jjtGetChild(0);
		SimpleNode classDecls = (SimpleNode) node.jjtGetChild(1);
		StringBuilder mainCode = (StringBuilder) mainClass.jjtAccept(this, data);
		return mainCode;
	}

	public Object visit(ASTMainClass node, Object data){
		Node classNameId = node.children[0];
		IdentifierInput input = new IdentifierInput(null, IdentifierInput.GET_NAME);
		String className = (String) classNameId.jjtAccept(this, input);
		Node mainMethod = node.children[1];
		StringBuilder mainMethodCode = (StringBuilder) mainMethod.jjtAccept(this, new Context(className));
		StringBuilder code = new StringBuilder();
		code.append(".class " + className + "\n");
		code.append(".super java/lang/Object\n");
		code.append("\n; default constructor\n");
		code.append(".method public <init>()V\n");
		code.append("   aload_0\n");
		code.append("   invokespecial java/lang/Object/<init>()V\n");
		code.append("   return\n");
		code.append(".end method\n");
		code.append(mainMethodCode);
		return code;
	}

	public Object visit(ASTMainMethod node, Object data){
		StringBuilder code = new StringBuilder();
		code.append(".method public static main([Ljava/lang/String;)V\n");
		//visit children
		SimpleNode body = (SimpleNode) node.jjtGetChild(1);
		JVMContext context = new JVMContext((Context) data);
		context.methodName = "main";

		String argName = getArgNames(context.className, context.methodName).iterator().next().toString();
		context.addLocal(argName);

		int stackSize = 100;
		int numLocals = 5;
		code.append("\n   .limit stack " + stackSize + "\n");
		code.append("   .limit locals " + numLocals + "\n\n");
		StringBuilder bodyCode = (StringBuilder) body.jjtAccept(this, context);
		code.append(bodyCode);
		code.append("   return\n");
		code.append(".end method\n");
		return code;
	}

	private Set<Symbol> getArgNames(String className, String methodName){
		SymbolTable methodsTable = ((ClassData)symbolTable.lookup(className)).methodsTable;
		SymbolTable paramsTable = ((MethodData)methodsTable.lookup(methodName)).paramsTable;
		return paramsTable.getHashMap().keySet();
	}

	private Set<Symbol> getLocalNames(String className, String methodName){
		SymbolTable methodsTable = ((ClassData)symbolTable.lookup(className)).methodsTable;
		SymbolTable localsTable = ((MethodData)methodsTable.lookup(methodName)).localsTable;
		return localsTable.getHashMap().keySet();
	}	

	private String getVarType(Context context, SimpleNode node){
		ClassData classData = (ClassData) symbolTable.lookup(context.className);
		MethodData methodData = (MethodData) classData.methodsTable.lookup(context.methodName);
		String varType;
		if((varType = (String)methodData.localsTable.lookup(context.varName)) == null){
			if((varType = (String)methodData.paramsTable.lookup(context.varName)) == null){
				if((varType = (String)classData.fieldsTable.lookup(context.varName)) == null){
					if(classData.hasSuperClass){
						Context superClassContext = new Context(classData.superClass, "", context.varName);
						return TypeCheckVisitor.getClassFieldVarType(superClassContext, node, symbolTable);
					}else{
						throw new ReferencedMissingVariable(context, node);							
					}
				}
			}
		}
		return varType;
	}


	public Object visit(ASTMethodBody node, Object data){
		Node varDecls = node.children[0];
		Node stmts = node.children[1];
		JVMContext context = (JVMContext) data;
		Set<Symbol> localNames = getLocalNames(context.className, context.methodName);
		for(Symbol name : localNames){
			context.addLocal(name.toString());
		}
		// HashMap<String, Integer> varMappings = (HashMap<String,Integer>)varDecls.jjtAccept(this, data);
		StringBuilder code = (StringBuilder) stmts.jjtAccept(this, context);
		return code;
	}


	public Object visit(ASTStmts node, Object data){
		StringBuilder code = new StringBuilder();
		for(Node stmt : node.children){
			code.append((StringBuilder) stmt.jjtGetChild(0).jjtAccept(this,data));
		}
		return code;
	}

	public Object visit(ASTSingleAssignment node, Object data){
		JVMContext context = (JVMContext) data;
		SimpleNode identifier = (SimpleNode) node.jjtGetChild(0);

		// This will be a chunk of code to store value on stack in var
		IdentifierInput input = new IdentifierInput(context, IdentifierInput.STORE);
		StringBuilder varAccess = (StringBuilder)identifier.jjtAccept(this, input);

		// int varNumber = context.locals.get(varName);
		SimpleNode expr = (SimpleNode) node.jjtGetChild(1);
		StringBuilder exprCode = (StringBuilder) expr.jjtGetChild(0).jjtAccept(this, data); //Increases stacksize by 1
		StringBuilder code = new StringBuilder();
		code.append(exprCode);
		code.append(varAccess);
		return code;
	}

	public Object visit(ASTIntLiteral node, Object data){
		int value = new Integer(((Token)node.value).image);
		StringBuilder code = new StringBuilder();
		if(value > 0 && value < 6){
			code.append("   iconst_" + value + " ; push " + value + " on stack\n");
		}else if(value == -1){
			code.append("   iconst_m1" + " ; push -1 on stack\n");
		}else{
			code.append("   ldc " + value + "\n");
		}
		return code;
	}

	public Object visit(ASTBoolLiteral node, Object data){
		String lit = ((Token) node.value).image;
		StringBuilder code = new StringBuilder();
		if(lit.equals("true")) {
			code.append("   iconst_1 ; push 1 (true) on stack\n");
		}else{
			code.append("   iconst_0 ; push 0 (false) on stack\n");
		}
		return code;
	}

	public Object visit(ASTIfElse node, Object data){
		SimpleNode exp = (SimpleNode) node.jjtGetChild(0).jjtGetChild(0);
		SimpleNode ifStmt = (SimpleNode) node.jjtGetChild(1).jjtGetChild(0);
		SimpleNode elseStmt = (SimpleNode) node.jjtGetChild(2).jjtGetChild(0);
		StringBuilder expCode = (StringBuilder) exp.jjtAccept(this, data);

		int branchNum = globalNumBranches;

		globalNumBranches ++;
		
		StringBuilder code = new StringBuilder();
		code.append(expCode);
		code.append("   ldc 1 ; push 1 (true) on stack\n");
		code.append("   if_icmpne else_" + branchNum + "\n");
		StringBuilder ifCode = (StringBuilder) ifStmt.jjtAccept(this, data);
		code.append(ifCode);
		code.append("   goto done_" + branchNum + "\n");
		code.append("else_" + branchNum + ":\n");
		StringBuilder elseCode = (StringBuilder) elseStmt.jjtAccept(this, data);
		code.append(elseCode);
		code.append("done_" + branchNum + ":\n");

		return code;
	}

	public Object visit(ASTPrint node, Object data){
		StringBuilder code = new StringBuilder();
		SimpleNode exp = (SimpleNode) node.jjtGetChild(0).jjtGetChild(0);
		StringBuilder expCode = (StringBuilder) exp.jjtAccept(this, data);
		code.append(expCode);
		code.append("   getstatic java/lang/System/out Ljava/io/PrintStream;\n");
		code.append("   swap ; value, objectref -> objectref, value\n"); //"System.out" and value are in wrong order on stack
	   	code.append("   invokevirtual java/io/PrintStream/println(I)V\n");
	   	return code;
	}

	public Object visit(ASTBlock node, Object data){
		StringBuilder code = new StringBuilder();
		for(Node stmt : node.children){
			StringBuilder stmtCode = (StringBuilder) stmt.jjtAccept(this, data);
			code.append(stmtCode);
		}
		return code;
	}

	

	private class JVMContext extends Context{
		private int nextVarNumber = 1;
		HashMap<String, Integer> locals = new HashMap<String,Integer>();
		boolean store = false;

		public JVMContext(Context context){
			super(context);
		}

		void addLocal(String varName){
			locals.put(varName, nextVarNumber);
			nextVarNumber ++;
		}

	}

	private boolean isField(JVMContext context, String varName){
		ClassData classData = (ClassData) symbolTable.lookup(context.className);
		MethodData methodData = (MethodData) classData.methodsTable.lookup(context.methodName);
		String varType;
		if((varType = (String)methodData.localsTable.lookup(varName)) == null){
			if((varType = (String)methodData.paramsTable.lookup(varName)) == null){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	//Load the value defined by arguments and put it on the stack
	public StringBuilder loadVar(JVMContext context, String varName){

		String varType = TypeCheckVisitor.getVarType(
			new Context(context.className, context.methodName, varName), null, symbolTable);
		
		if(isField(context, varName)){
			StringBuilder code = new StringBuilder();
			code.append("   aload_0 ; this\n");
			code.append("   getfield " + context.className + "/" + varName + " ");
			if(varType.equals("int") || varType.equals("boolean")) {
				code.append("I");
			}else if(varType.equals("int[]")){
				code.append("[I");
			}else{
				code.append("L" + varType);
			}
			code.append("\n");
			return code;
		}else{
			int varNumber = context.locals.get(varName);
			if(varType.equals("int") || varType.equals("boolean")) {
				return new StringBuilder("   iload" + (varNumber < 4 ? "_" : " ") + varNumber + " ; " + varName + "\n");
			}else{
				return new StringBuilder("   aload" + (varNumber < 4 ? "_" : " ") + varNumber + " ; " + varName + "\n");
			}
		}
	}

	private class IdentifierInput{
		JVMContext context;
		static final int STORE = 1;
		static final int GET_NAME = 2;
		int usage;
		IdentifierInput(JVMContext context, int usage){
			this.context = context;
			this.usage = usage;
		}
	}

	//Pop value from stack and store in var defined by args.
	public StringBuilder storeVar(JVMContext context, String varName){
		String varType = TypeCheckVisitor.getVarType(
			new Context(context.className, context.methodName, varName), null, symbolTable);
		
		if(isField(context, varName)){
			StringBuilder code = new StringBuilder("   aload_0 ; this\n");
			code.append("   swap ; value, objectref -> objectref, value\n"); //"this" and value are in wrong order on stack
			code.append("   putfield " + context.className + "/" + varName + " ");
			if(varType.equals("int") || varType.equals("boolean")) {
				code.append("I");
			}else if(varType.equals("int[]")){
				code.append("[I");
			}else{
				code.append("L" + varType);
			}
			code.append("\n");
			return code ;
		}else{
			int varNumber = context.locals.get(varName);
			if(varType.equals("int") || varType.equals("boolean")) {
				return new StringBuilder("   istore" + (varNumber < 4 ? "_" : " ") + varNumber + " ; " + varName + "\n");
			}else{
				return new StringBuilder("   astore" + (varNumber < 4 ? "_" : " ") + varNumber + " ; " + varName + "\n");
			}
		}
	}

}
