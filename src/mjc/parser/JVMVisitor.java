package mjc.parser;

import java.util.*;
import java.lang.*;
import mjc.parser.VisitorUtil.*;

public class JVMVisitor extends VisitorAdapter{
	
	private SymbolTable symbolTable;


	//TODO Hack warning. Using a typecheckvisitor to determine the class of expr in expr.callMethod()
	private TypeCheckVisitor typeCheckVisitor;


	//Keeps track of how many branches there are so that every branch gets
	//unique labels, like [else_0 and done_0], [else_1 and done_1]
	private int globalNumBranches = 0; 

	public JVMVisitor(SymbolTable symbolTable){
		this.symbolTable = symbolTable;	
		typeCheckVisitor = new TypeCheckVisitor(symbolTable);
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

	// returns List<ClassFile> where each string is contents of a .j-file
	public Object visit(ASTProgram node, Object data){
		SimpleNode mainClass = (SimpleNode) node.jjtGetChild(0);
		SimpleNode classDecls = (SimpleNode) node.jjtGetChild(1);
		ClassFile mainFile = (ClassFile) mainClass.jjtAccept(this, data);
		List<ClassFile> files = (List<ClassFile>)classDecls.jjtAccept(this, data);
		files.add(mainFile);
		return files;
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
		return new ClassFile(className, code.toString());
	}

	public Object visit(ASTMainMethod node, Object data){
		StringBuilder code = new StringBuilder();
		code.append("\n.method public static main([Ljava/lang/String;)V\n");
		SimpleNode body = (SimpleNode) node.jjtGetChild(1);
		JVMContext context = new JVMContext((Context) data);
		context.methodName = "main";

		String argName = getArgNames(context.className, context.methodName).iterator().next().toString();
		context.addLocal(argName);

		int stackSize = 100;
		int numLocals = 10;
		code.append("\n   .limit stack " + stackSize + "\n");
		code.append("   .limit locals " + numLocals + "\n\n");
		StringBuilder bodyCode = (StringBuilder) body.jjtAccept(this, context);
		code.append(bodyCode);
		code.append("   return\n");
		code.append(".end method\n");
		return code;
	}

	public Object visit(ASTMethodDecls node, Object data){
		StringBuilder code = new StringBuilder();
		for(int i = 0; i < node.jjtGetNumChildren(); i++){
			SimpleNode decl = (SimpleNode) node.jjtGetChild(i);
			code.append(decl.jjtAccept(this, data));
		}
		return code;
	}

	public Object visit(ASTMethodDecl node, Object data){

		StringBuilder code = new StringBuilder();
		SimpleNode methodNameId = (SimpleNode) node.jjtGetChild(1);
		SimpleNode body = (SimpleNode) node.jjtGetChild(3);
		SimpleNode returnStmt = (SimpleNode) node.jjtGetChild(4);
		IdentifierInput input = new IdentifierInput(null, IdentifierInput.GET_NAME);
		String methodName = (String) methodNameId.jjtAccept(this, input);

		JVMContext context = new JVMContext((Context) data);
		context.methodName = methodName;
		
		String typeString = typeToJasminType(getMethodType(context));
		StringBuilder argsCode = new StringBuilder();
		for(String argType : getMethodParamTypes(context)){
			argsCode.append(typeToJasminType(argType));
		}

		for(Symbol argName : getArgNames(context.className, context.methodName)){
			context.addLocal(argName.toString());
		}

		code.append("\n.method public " + methodName + "(" + argsCode.toString() + ")" + typeString);
		int stackSize = 100;
		int numLocals = 20;
		code.append("\n   .limit stack " + stackSize + "\n");
		code.append("   .limit locals " + numLocals + "\n\n");
		StringBuilder bodyCode = (StringBuilder) body.jjtAccept(this, context);
		code.append(bodyCode);
		StringBuilder returnStmtCode = (StringBuilder) returnStmt.jjtAccept(this, context);
		code.append(returnStmtCode);

		if(typeString.equals("I")){
			code.append("   ireturn\n");
		}else{
			code.append("   areturn\n"); //TODO only 2 cases, int and reference ?
		}
		code.append(".end method\n");

		return code;
	}

	private String typeToJasminType(String type){
		if(type.equals("int")){
			return "I";
		}
		if(type.equals("boolean")){
			return "I";
		}
		if(type.equals("int[]")){
			return "[I"; //TODO Correct ?
		}
		return "L" + type + ";"; //TODO
	}

	public Object visit(ASTClassDecls node, Object data){
		List<ClassFile> classFiles = new ArrayList<ClassFile>();
		for(int i = 0; i < node.jjtGetNumChildren(); i++){
			Node classDecl = node.jjtGetChild(i);
			classFiles.add((ClassFile)classDecl.jjtAccept(this,data));
		}
		return classFiles;
	}

	public Object visit(ASTClassDecl node, Object data){
		return visitClassDecl(node, data, "java/lang/Object", false);
	}

	public Object visit(ASTSubClassDecl node, Object data){
		SimpleNode superClassId = (SimpleNode) node.jjtGetChild(1);
		String superClassName = getVal(superClassId);
		return visitClassDecl(node, data, superClassName, true);
	}

	private ClassFile visitClassDecl(SimpleNode node, Object data, String superClass, boolean isSubClass){

		Node classNameId = node.children[0];
		IdentifierInput input = new IdentifierInput(null, IdentifierInput.GET_NAME);
		String className = getVal(classNameId);
		Context context = new Context(className);
		StringBuilder code = new StringBuilder();
		code.append(".class " + className + "\n");
		code.append(".super " + superClass + "\n");
		SimpleNode varDecls = (SimpleNode) node.jjtGetChild(1 + (isSubClass? 1 : 0)); 
		SimpleNode methodDecls = (SimpleNode) node.jjtGetChild(2 + (isSubClass? 1 : 0));
		StringBuilder methodsCode = (StringBuilder) methodDecls.jjtAccept(this, context);
		StringBuilder varsCode = (StringBuilder) varDecls.jjtAccept(this, context);
		
		code.append(varsCode);
		code.append("\n; default constructor\n");
		code.append(".method public <init>()V\n");
		code.append("   aload_0\n");
		if(!isSubClass){			
			code.append("   invokespecial java/lang/Object/<init>()V\n");
		}else{
			code.append("   invokespecial " + superClass + "/<init>()V\n");
		}
		code.append("   return\n");
		code.append(".end method\n");
		code.append(methodsCode);	
		
		return new ClassFile(className, code.toString());
	}

	public Object visit(ASTNewObject node, Object data){
		StringBuilder code = new StringBuilder();
		SimpleNode classNameId = (SimpleNode) node.jjtGetChild(0);
		String className = getVal(classNameId);

		code.append("   ; create an " + className + " object on top of stack\n");
		code.append("   new " + className + "\n");
		code.append("   dup\n");
		code.append("   invokespecial " + className + "/<init>()V ; call constructor\n");
		return code;
	}

	public Object visit(ASTCall node, Object data){
		StringBuilder code = new StringBuilder();
		SimpleNode obj = (SimpleNode) node.jjtGetChild(0);
		SimpleNode methodNameId = (SimpleNode) node.jjtGetChild(1);
		SimpleNode expList = (SimpleNode) node.jjtGetChild(2);
		StringBuilder expsCode = (StringBuilder) expList.jjtAccept(this, data);
		StringBuilder loadObjCode = (StringBuilder) obj.jjtAccept(this, data);
		code.append(loadObjCode);
		code.append(expsCode);
		String methodName = getVal(methodNameId);
		String className = (String) obj.jjtAccept(typeCheckVisitor, new TypeCheckVisitor.ExprInput((Context)data, null));
		StringBuilder argsCode = new StringBuilder();
		JVMContext context = new JVMContext(new Context(className, methodName));
		for(String argType : getMethodParamTypes(context)){
			argsCode.append(typeToJasminType(argType));
		}
		String returnTypeString = typeToJasminType(getMethodType(context));
		code.append("   invokevirtual " + className + "/" + methodName + "(" + argsCode + ")" + returnTypeString + "\n");
		return code;
	}

	public Object visit(ASTExprList node, Object data){
		StringBuilder code = new StringBuilder();
		for(int i = 0; i < node.jjtGetNumChildren(); i++){
			SimpleNode exp = (SimpleNode) node.jjtGetChild(i);
			code.append((StringBuilder) exp.jjtAccept(this, data));
		}
		return code;
	}

	public Object visit(ASTVarDecls node, Object data){
		StringBuilder code = new StringBuilder();
		for(int i = 0; i < node.jjtGetNumChildren(); i++){
			SimpleNode decl = (SimpleNode)node.jjtGetChild(i);
			code.append(decl.jjtAccept(this, data));
		}
		return code;
	}

	public Object visit(ASTVarDecl node, Object data){
		SimpleNode id = (SimpleNode)node.jjtGetChild(1);
		String varName = getVal(id);
		Context context = (Context) data;
		context.varName = varName;
		String varType = getVarType(context, node);
		varType = typeToJasminType(varType);
		return ".field private " + cleanVarName(varName) + " " + varType + " = 0\n";
	}

	private String cleanVarName(String varName){
		if(Arrays.asList("field","param").contains(varName)){
			return varName + "_CLEANED";
		}
		return varName;
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
		MethodData methodData = null;
		if(context.methodName != null){
			methodData = (MethodData) classData.methodsTable.lookup(context.methodName);
		} 
		String varType;
		if(methodData == null || (varType = (String)methodData.localsTable.lookup(context.varName)) == null){
			//methodData == null if this is called from a classfield varDecl
			if(methodData == null || (varType = (String)methodData.paramsTable.lookup(context.varName)) == null){
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

	private String getMethodType(Context context){
		return getMethodData(context).returnType;
	}

	private List<String> getMethodParamTypes(Context context){
		return getMethodData(context).paramTypes;	
	}

	private MethodData getMethodData(Context context){
		ClassData classData = (ClassData) symbolTable.lookup(context.className);
		MethodData methodData = (MethodData) classData.methodsTable.lookup(context.methodName);	
		while(methodData == null && classData.hasSuperClass){
			//System.out.println("superclassname: " + classData.superClass); //TODO
			classData = (ClassData) symbolTable.lookup(classData.superClass);
			//System.out.println("superclassname2: " + classData.superClass); //TODO
			methodData = (MethodData) classData.methodsTable.lookup(context.methodName);	

		}
		return methodData;
	}


	public Object visit(ASTMethodBody node, Object data){
		Node varDecls = node.children[0];
		Node stmts = node.children[1];
		JVMContext context = (JVMContext) data;
		Set<Symbol> localNames = getLocalNames(context.className, context.methodName);
		for(Symbol name : localNames){
			context.addLocal(name.toString());
		}
		StringBuilder code = (StringBuilder) stmts.jjtAccept(this, context);
		return code;
	}


	public Object visit(ASTStmts node, Object data){
		StringBuilder code = new StringBuilder();
		for(int i = 0; i < node.jjtGetNumChildren(); i++){
			Node stmt = node.jjtGetChild(i);
			inheritLineNumber(stmt, stmt.jjtGetChild(0));
			code.append((StringBuilder) stmt.jjtGetChild(0).jjtAccept(this,data));
		}
		return code;
	}

	private void inheritLineNumber(Node parent, Node child){

		if(((SimpleNode)parent).value != null){
			// System.out.println(((Token)((SimpleNode)parent).value).beginLine);
			((Token)((SimpleNode)child).value).beginLine = ((Token)((SimpleNode)parent).value).beginLine;	
		}else{
			// System.out.println("No line number for parent");
		}
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

	public Object visit(ASTNewIntArray node, Object data){
		StringBuilder code = new StringBuilder();
		SimpleNode arrayLen = (SimpleNode) node.jjtGetChild(0);
		code.append((StringBuilder) arrayLen.jjtAccept(this, data));
		code.append("   newarray int\n");
		return code;
	}

	public Object visit(ASTArrayAssignment node, Object data){
		SimpleNode varNameId = (SimpleNode) node.jjtGetChild(0);
		SimpleNode index = (SimpleNode) node.jjtGetChild(1);
		SimpleNode value = (SimpleNode) node.jjtGetChild(2);

		StringBuilder code = new StringBuilder();
		code.append((StringBuilder)varNameId.jjtAccept(this,data));
		code.append((StringBuilder)index.jjtAccept(this,data));
		code.append((StringBuilder)value.jjtAccept(this,data));
		code.append("   iastore\n");
		return code;
	}

	public Object visit(ASTArrayAccess node, Object data){
		StringBuilder code = new StringBuilder();
		code.append((StringBuilder)node.jjtGetChild(0).jjtAccept(this,data));//arrayref
		code.append((StringBuilder)node.jjtGetChild(1).jjtAccept(this,data));//index
		code.append("   iaload\n");
		return code;		
	}

	public Object visit(ASTThis node, Object data){
		return new StringBuilder("   aload_0 ; put 'this' on stack\n"); //TODO 'this' is stored in var0, right?
	}

	public Object visit(ASTNegExpr node, Object data){
		SimpleNode boolExpr = (SimpleNode) node.jjtGetChild(0);
		StringBuilder code = new StringBuilder();
		code.append(boolExpr.jjtAccept(this, data));
		code.append("   iconst_m1 ; put -1 on stack\n");
		code.append("   imul\n");
		return code;
	}

	public Object visit(ASTArrayLength node, Object data){
		StringBuilder code = new StringBuilder();
		code.append((StringBuilder)node.jjtGetChild(0).jjtAccept(this,data)); //Expr
		code.append("arraylength\n");
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

	public Object visit(ASTAnd node, Object data){
		return visitAndOr(node, data, "iand");
	}

	public Object visit(ASTOr node, Object data){
		return visitAndOr(node, data, "ior");
	}

	private StringBuilder visitAndOr(SimpleNode node, Object data, String instruction){
		StringBuilder code = new StringBuilder();
		code.append(node.jjtGetChild(0).jjtAccept(this, data));
		code.append(node.jjtGetChild(1).jjtAccept(this, data));
		code.append("   " + instruction + "\n");
		return code;
	}

	public Object visit(ASTParenExp node, Object data){
		return node.jjtGetChild(0).jjtGetChild(0).jjtAccept(this, data); //parenExp -> Exp -> SpecificExp
	}

	public Object visit(ASTPlus node, Object data){
		return visitIntBinOp(node, data, "iadd");
	}

	public Object visit(ASTMinus node, Object data){
		return visitIntBinOp(node, data, "isub");
	}

	public Object visit(ASTMult node, Object data){
		return visitIntBinOp(node, data, "imul");	
	}

	private StringBuilder visitIntBinOp(SimpleNode node, Object data, String opInstruction){
		SimpleNode leftExp = (SimpleNode) node.jjtGetChild(0);
		SimpleNode rightExp = (SimpleNode) node.jjtGetChild(1);
		StringBuilder code = new StringBuilder();
		code.append((StringBuilder) leftExp.jjtAccept(this, data));
		code.append((StringBuilder) rightExp.jjtAccept(this, data));
		code.append("   " + opInstruction + "\n");
		return code;
	}

	public Object visit(ASTLessThan node, Object data){
		return visitComparison(node, data, "if_icmplt");
	}

	public Object visit(ASTLessEqual node, Object data){
		return visitComparison(node, data, "if_icmple");
	}

	public Object visit(ASTGreaterThan node, Object data){
		return visitComparison(node, data, "if_icmpgt");
	}

	public Object visit(ASTGreaterEqual node, Object data){
		return visitComparison(node, data, "if_icmpge");
	}

	public Object visit(ASTEqual node, Object data){
		return visitComparison(node, data, "if_icmpeq");
	}

	public Object visit(ASTNotEqual node, Object data){
		return visitComparison(node, data, "if_icmpne");
	}

	private StringBuilder visitComparison(SimpleNode node, Object data, String comparison){
		StringBuilder code = new StringBuilder();

		int branchNum = globalNumBranches;
		globalNumBranches ++;

		SimpleNode leftExp = (SimpleNode) node.jjtGetChild(0);
		SimpleNode rightExp = (SimpleNode) node.jjtGetChild(1);

		code.append((StringBuilder) leftExp.jjtAccept(this,data)); 
		code.append((StringBuilder) rightExp.jjtAccept(this,data));

		String lineStr = node.value != null ? "line " + ((Token)node.value).beginLine : "unknown line";
		code.append("   " + comparison + " trueCmp_" + branchNum + " ; " + lineStr  + "\n");
		code.append("   iconst_0\n");
		code.append("   goto doneCmp_" + branchNum + "\n");
		code.append("trueCmp_" + branchNum + ":\n");
		code.append("   iconst_1\n");
		code.append("doneCmp_" + branchNum + ":\n");
		return code;
	}

	public Object visit(ASTIfElse node, Object data){
		SimpleNode exp = (SimpleNode) node.jjtGetChild(0).jjtGetChild(0);

		inheritLineNumber(node, exp);

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

	public Object visit(ASTIf node, Object data){
		SimpleNode exp = (SimpleNode) node.jjtGetChild(0).jjtGetChild(0);
		inheritLineNumber(node, exp);
		SimpleNode ifStmt = (SimpleNode) node.jjtGetChild(1).jjtGetChild(0);
		StringBuilder expCode = (StringBuilder) exp.jjtAccept(this, data);
		int branchNum = globalNumBranches;
		globalNumBranches ++;
		StringBuilder code = new StringBuilder();
		code.append(expCode);
		code.append("   ldc 1 ; push 1 (true) on stack\n");
		code.append("   if_icmpne done_" + branchNum + "\n");
		StringBuilder ifCode = (StringBuilder) ifStmt.jjtAccept(this, data);
		code.append(ifCode);
		code.append("done_" + branchNum + ":\n");
		return code;
	}

	public Object visit(ASTWhile node, Object data){
		SimpleNode exp = (SimpleNode) node.jjtGetChild(0).jjtGetChild(0);
		inheritLineNumber(node, exp);
		SimpleNode whileStmt = (SimpleNode) node.jjtGetChild(1).jjtGetChild(0);
		int branchNum = globalNumBranches;
		globalNumBranches ++;

		StringBuilder expCode = (StringBuilder) exp.jjtAccept(this, data);
		StringBuilder code = new StringBuilder();
		code.append("while_" + branchNum + ":\n");
		code.append(expCode);
		code.append("   ldc 1 ; push 1 (true) on stack\n");
		code.append("   if_icmpne done_" + branchNum + "\n");
		StringBuilder whileCode = (StringBuilder) whileStmt.jjtAccept(this, data);
		code.append(whileCode);
		code.append("   goto while_" + branchNum + "\n");
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

	public Object visit(ASTExpr node, Object data){
		SimpleNode child = (SimpleNode) node.jjtGetChild(0);
		inheritLineNumber(node, child);
		return node.jjtGetChild(0).jjtAccept(this, data);
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
			code.append("   getfield " + context.className + "/" + cleanVarName(varName) + " ");
			code.append(typeToJasminType(varType));
			code.append("\n");
			return code;
		}else{
			int varNumber = context.locals.get(varName);
			if(varType.equals("int") || varType.equals("boolean")) {
				return new StringBuilder("   iload" + (varNumber < 4 ? "_" : " ") + varNumber + " ; " + cleanVarName(varName) + "\n");
			}else{
				return new StringBuilder("   aload" + (varNumber < 4 ? "_" : " ") + varNumber + " ; " + cleanVarName(varName) + "\n");
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
			code.append("   putfield " + context.className + "/" + cleanVarName(varName) + " ");
			code.append(typeToJasminType(varType));
			code.append("\n");
			return code ;
		}else{
			int varNumber = context.locals.get(varName);
			if(varType.equals("int") || varType.equals("boolean")) {
				return new StringBuilder("   istore" + (varNumber < 4 ? "_" : " ") + varNumber + " ; " + cleanVarName(varName) + "\n");
			}else{
				return new StringBuilder("   astore" + (varNumber < 4 ? "_" : " ") + varNumber + " ; " + cleanVarName(varName) + "\n");
			}
		}
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

}
