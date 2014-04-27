package mjc.parser;

import java.util.*;
import mjc.parser.VisitorUtil.Pair;
import mjc.parser.VisitorUtil.ClassData;
import mjc.parser.VisitorUtil.MethodData;
import mjc.parser.VisitorUtil.Context;

public class TypeCheckVisitor extends VisitorAdapter{

	private SymbolTable symbolTable;

	//Before this class does its job, one should traverse the symboltable and make sure all references classes are declared (?)
	//Should not be done in this class (?)

	public TypeCheckVisitor(SymbolTable symbolTable){
		this.symbolTable = symbolTable;
	}




	public Object visit(ASTProgram node, Object data){
		return visitChildren(node, null);
	}
	
	public Object visit(ASTMainClass node, Object data){
		String className = getVal(node.jjtGetChild(0));
		Node mainMethod = node.jjtGetChild(1);
		return mainMethod.jjtAccept(this, new Context(className, "main"));
	}

	public Object visit(ASTMainMethod node, Object data){
		Node body = node.jjtGetChild(1);
		return body.jjtAccept(this, data);
	}

	public Object visit(ASTClassDecls node, Object data){
		return visitChildren(node, null);
	}

	public Object visit(ASTClassDecl node, Object data){
		Node classId = node.jjtGetChild(0);
		String className = getVal(classId);
		Node methodDecls = node.jjtGetChild(2);
		return methodDecls.jjtAccept(this, new Context(className));
	}

	public Object visit(ASTSubClassDecl node, Object data){
		Node classId = node.jjtGetChild(0);
		String className = getVal(classId);
		Node methodDecls = node.jjtGetChild(3); 
		return methodDecls.jjtAccept(this, new Context(className));
	}
	


	//data == class context
	public Object visit(ASTMethodDecls node, Object data){
		return visitChildren(node, data);
	}
	
	//data == class context
	public Object visit(ASTMethodDecl node, Object data){
		Node methodId = node.jjtGetChild(1);
		String methodName = getVal(methodId);
		Context context = new Context(data);
		context.methodName = methodName;
		Node methodBody = node.jjtGetChild(3);
		methodBody.jjtAccept(this, context);
		Node returnExp = node.jjtGetChild(4);
		String expectedReturnType = getFunctionReturnType(context);
		returnExp.jjtAccept(this, new ExprInput(context, expectedReturnType));
		return null;
	}
	
	//data == class.method() context
	public Object visit(ASTMethodBody node, Object data){
		Node stmts = node.jjtGetChild(1);
		return stmts.jjtAccept(this, data);
	}
	
	//data == class.method() context
	public Object visit(ASTStmts node, Object data){
		return visitChildren(node, data);
	}
	
	
	
	

	// If id is an <expression> : return TYPE of expression
	// Else                     : return id
	public Object visit(ASTIdentifier node, Object data){
		if(data instanceof ExprInput){ //This Identifier is an expression
			ExprInput input = (ExprInput)data;
			Context context = new Context(input.context);
			context.varName = getVal(node);
			String varType = getVarType(context, node);
			checkExpectedType(input, varType, node);
			return varType;
		}
		return getVal(node);
	}

	//--------------------------------------------
	//                 STATEMENTS
	//--------------------------------------------

	// Statements input: 
	// data == class.method() context

	// output:
	// null

	public Object visit(ASTStmt node, Object data){
		assertDataContext(data);
		return node.jjtGetChild(0).jjtAccept(this, data);
	}
	
	public Object visit(ASTBlock node, Object data){
		assertDataContext(data);
		return visitChildren(node, data);
	}
	
	public Object visit(ASTSingleAssignment node, Object data){
		Context context = new Context(assertDataContext(data));
		String varName = (String) node.jjtGetChild(0).jjtAccept(this, null);
		context.varName = varName;
		Node expr = node.jjtGetChild(1);
		String varType = getVarType(context, node);
		expr.jjtAccept(this, new ExprInput(context, varType));
		return null;	
	}
	
	public Object visit(ASTArrayAssignment node, Object data){
		Context context = new Context(assertDataContext(data));
		String varName = (String) node.jjtGetChild(0).jjtAccept(this, null);
		//No bound checking takes place. This could be done using child1
		Node index = node.jjtGetChild(1);
		index.jjtAccept(this, new ExprInput(context, "int"));
		Node expr = node.jjtGetChild(2); //2, not 1
		context.varName = varName;
		String varType = getVarType(context, node);
		if(!(varType.equals("new int[]")) && !(varType.equals("int[]"))){
			throw new WrongType(context, "int[]", varType, node);
		}
		expr.jjtAccept(this, new ExprInput(context, "int"));
		return null;
	}

	public Object visit(ASTIfElse node, Object data){
		assertDataContext(data);
		return visitCondStmts(node, data, 2);
	}
	
	public Object visit(ASTIf node, Object data){
		assertDataContext(data);
		return visitCondStmts(node, data, 1);
	}

	public Object visit(ASTWhile node, Object data){
		assertDataContext(data);
		return visitCondStmts(node, data, 1);
	}

	//General visit for "if", "ifelse" and "while"
	// node consists of '1' expression and 'numStmts' statements
	private Object visitCondStmts(Node node, Object data, int numStmts){
		Context context = assertDataContext(data);
		Node booleanExpr = node.jjtGetChild(0);
		booleanExpr.jjtAccept(this, new ExprInput(context, "boolean"));
		for(int i = 0; i < numStmts; i++){
			Node stmt = node.jjtGetChild(1 + i);
			stmt.jjtAccept(this, data);
		}
		return null;
	}
	
	public Object visit(ASTPrint node, Object data){
		Context context = assertDataContext(data);
		return node.jjtGetChild(0).jjtAccept(this, new ExprInput(context, "int"));
	}
	
	
	








	//--------------------------------------------
	//                 EXPRESSIONS
	//--------------------------------------------

	// Statements input: 
	// data == ExprInput, consisting of context and possibly expectedType
	// 		context tells where the expression occurs.
	//		expectedType tells what type the expression is expected to evaluate to.

	// output:
	// String: type of evaluated expression (with a few exceptions)


	public Object visit(ASTExpr node, Object data){
		assertExprInput(data);
		return node.jjtGetChild(0).jjtAccept(this, data);
	}

	public Object visit(ASTNegExpr node, Object data){
		ExprInput input = new ExprInput(assertExprInput(data));
		input.expectedType = "boolean";
		return node.jjtGetChild(0).jjtAccept(this, input);
	}
	
	public Object visit(ASTIntLiteral node, Object data){
		ExprInput input = assertExprInput(data);
		checkExpectedType(input, "int", node);
		try{
			int value = new Integer(((Token)node.value).image);
		}catch(NumberFormatException e){
			throw new WrongType(null, "int", "int_lit not valid int", node);
		}

		return "int";
	}

	public Object visit(ASTBoolLiteral node, Object data){
		ExprInput input = assertExprInput(data);
		checkExpectedType(input, "boolean", node);
		return "boolean";
	}


	public Object visit(ASTLessThan node, Object data){
		return visitComparisonNode(node, data);
	}

	public Object visit(ASTLessEqual node, Object data){
		return visitComparisonNode(node, data);
	}

	public Object visit(ASTGreaterThan node, Object data){
		return visitComparisonNode(node, data);
	}

	public Object visit(ASTGreaterEqual node, Object data){
		return visitComparisonNode(node, data);
	}

	public Object visit(ASTEqual node, Object data){
		return visitComparisonNode(node, data);
	}

	public Object visit(ASTNotEqual node, Object data){
		return visitComparisonNode(node, data);
	}

	public String visitComparisonNode(SimpleNode node, Object data){
		ExprInput input = new ExprInput(assertExprInput(data));
		checkExpectedType(input, "boolean", node);
		Node expr1 = node.jjtGetChild(0);
		Node expr2 = node.jjtGetChild(1);

		boolean intCompare = !(node instanceof ASTEqual) && !(node instanceof ASTNotEqual);
		input.expectedType = intCompare? "int" : null;
		
		String type1 = (String) expr1.jjtAccept(this, input);
		String type2 = (String) expr2.jjtAccept(this, input);
		if(!intCompare && !typesAreCompatible(type1, true, type2, true, input.context, node)){
			throw new WrongType(input.context, "", type1 + " and " + type2, node);
		}
		return "boolean";
	}

	//No superclasses are checked
	private boolean typesAreCompatible(String type1, String type2, Context context, SimpleNode node){
		return typesAreCompatible(type1, false, type2, false, context, node);
	}

	//super1 == true means that if a superclass of type1 equals type2, true will be returned.
	//If both super == false, types must be equal for true to be returned.
	private boolean typesAreCompatible(String type1, boolean super1, String type2, boolean super2, Context context, SimpleNode node){
		if(type1.equals(type2)){
			return true;
		}

		List<String> nonClass = Arrays.asList(new String[]{"int", "boolean", "int[]", "new int[]"});
		if(nonClass.contains(type1) || nonClass.contains(type2)){
			return false;
		}

		//Compare type1's superclasses with type2 f
		if(super1){
			String type = type1;
			ClassData className	 = getClass(type, context, node);
			while(className.hasSuperClass){
				type = className.superClass;
				className = getClass(type, context, node);
				if(type.equals(type2)){
					return true;
				}
			}	
		}

		//Compare type2's superclasses with type1 
		if(super2){
			String type = type2;
			ClassData className = getClass(type, context, node);
			while(className.hasSuperClass){
				type = className.superClass;
				className = getClass(type, context, node);
				if(type.equals(type1)){
					return true;
				}
			}
		}

		return false;
	}

	private ClassData getClass(String type, Context context, SimpleNode node){
		ClassData className = (ClassData) symbolTable.lookup(type);
		if(className == null){
			throw new ReferencedMissingType(context, type, node);
		}
		return className;
	}
	
	public Object visit(ASTAnd node, Object data){
		return visitAndOr(node, data);
	}

	public Object visit(ASTOr node, Object data){
		return visitAndOr(node, data);
	}

	public String visitAndOr(SimpleNode node, Object data){
		ExprInput input = new ExprInput(assertExprInput(data));
		checkExpectedType(input, "boolean", node);
		input.expectedType = "boolean";
		Node expr1 = node.jjtGetChild(0);
		Node expr2 = node.jjtGetChild(1);
		expr1.jjtAccept(this, input);
		expr2.jjtAccept(this, input);
		return "boolean";
	}


	
	public Object visit(ASTPlus node, Object data){
		ExprInput input = assertExprInput(data);
		return visitIntBinop(node, data);
	}
	
	public Object visit(ASTMinus node, Object data){
		assertExprInput(data);
		return visitIntBinop(node, data);
	}
	
	public Object visit(ASTMult node, Object data){
		assertExprInput(data);
		return visitIntBinop(node, data);
	}

	public Object visitIntBinop(SimpleNode node, Object data){
		ExprInput input = new ExprInput(assertExprInput(data));
		checkExpectedType(input, "int", node);
		input.expectedType = "int";
		Node expr1 = node.jjtGetChild(0);
		Node expr2 = node.jjtGetChild(1);
		expr1.jjtAccept(this, input);
		expr2.jjtAccept(this, input);
		return "int";
	}
	
	public Object visit(ASTArrayAccess node, Object data){
		ExprInput input = new ExprInput(assertExprInput(data));
		checkExpectedType(input, "int", node);
		Node arrayObj = node.jjtGetChild(0);
		Node indexExpr = node.jjtGetChild(1);
		input.expectedType = "int";
		indexExpr.jjtAccept(this, input);
		input.expectedType = "int[]";
		String type = (String) arrayObj.jjtAccept(this, input);
		if(type.equals("new int[]")) {
			throw new WrongType(input.context, "int[]", "new int[]", node, WrongType.Kind.UNKNOWN);
		}
		return "int";
	}

	/*
 	 * Special case for parenthesized new int[]
 	 * a = new int[5][0]; is not valid
 	 * a = (new int[5])[0]; is valid
 	 */
	public Object visit(ASTParenExp node, Object data){
		Node childExpr = node.jjtGetChild(0);
		String type = (String) childExpr.jjtAccept(this, data);
		if(type.equals("new int[]"))
			type = "int[]";
		return type;
	}
	
	public Object visit(ASTCall node, Object data){ 
		ExprInput input = assertExprInput(data);
		ExprInput childInput = new ExprInput(input);
		childInput.expectedType = null;

		Node objExp = node.jjtGetChild(0);
		Node methodId = node.jjtGetChild(1);
		Node expList = node.jjtGetChild(2);

		List<String> paramTypes = (List<String>) expList.jjtAccept(this, childInput);

		String methodName = getVal(methodId);
		
		String objType = (String) objExp.jjtAccept(this, childInput);
		String returnType = checkCall(objType, methodName, input.expectedType, paramTypes, (SimpleNode)methodId);
		checkExpectedType(input, returnType, node);
		return returnType;
	}

	// NOTE:
	//This does not return a string denoting the type
	//Instead returns a LIST of strings with the values of the sub-expressions
	public Object visit(ASTExprList node, Object data){
		ExprInput input = new ExprInput(assertExprInput(data));
		input.expectedType = null; //No typechecking of parameters at this level, instead, they are sent upwards.
		List<String> types = new ArrayList<String>();
		for(int i = 0; i < node.jjtGetNumChildren(); i++){
			Node child = node.jjtGetChild(i);
			String type = (String)child.jjtAccept(this, data);
			types.add(type);
		}
		return types;
	}

	//Returns return type of method
	private String checkCall(String className, String methodName, String expectedReturnType, 
			List<String> foundParamTypes, SimpleNode node){
		ClassData classData = (ClassData) symbolTable.lookup(className);
		if(classData == null){
			throw new ReferencedMissingType(null, className, node);
		}
		MethodData methodData = (MethodData) classData.methodsTable.lookup(methodName);
		if(methodData == null){
			if(classData.hasSuperClass){
				return checkCall(classData.superClass, methodName, expectedReturnType, foundParamTypes, node);
			}else{
				throw new ReferencedMissingMethod(null, methodName);	
			}
		}

		String returnType = methodData.returnType;
		if(expectedReturnType != null && !typesAreCompatible(expectedReturnType, false, returnType, true, new Context(className, methodName), node)){
			throw new WrongType(new Context(className, methodName), expectedReturnType, returnType, node, WrongType.Kind.RETURN);
		}
		checkCallArgs(className, methodName, foundParamTypes, methodData, node);
		return methodData.returnType;
	}

	private void checkCallArgs(String className, String methodName, List<String> foundParamTypes, MethodData methodData, SimpleNode node){
		if(foundParamTypes.size() != methodData.paramTypes.size()){
			throw new WrongNumberArgs(new Context(className, methodName), methodData.paramTypes.size(), foundParamTypes.size(), node);
		}
		for(int paramNum = 0; paramNum < foundParamTypes.size(); paramNum ++){
			
			String correctType = methodData.paramTypes.get(paramNum);
			String foundType = foundParamTypes.get(paramNum);
			if(foundType.equals("new int[]")) // Special case!!!
				foundType = "int[]";

			if(!typesAreCompatible(correctType, false, foundType, true, new Context(className, methodName), node)){
				throw new WrongType(new Context(className, methodName), correctType, foundType, node, WrongType.Kind.ARG);
			}	
		}
	}
	
	public Object visit(ASTArrayLength node, Object data){
		ExprInput input = new ExprInput(assertExprInput(data));
		checkExpectedType(input, "int", node);
		input.expectedType = "int[]";
		node.jjtGetChild(0).jjtAccept(this, input);
		return "int";
	}
	
	public Object visit(ASTThis node, Object data){
		ExprInput input = assertExprInput(data);
		String currentClass = input.context.className;
		checkExpectedType(input, currentClass, node);
		return currentClass;
	}
	
	public Object visit(ASTNewIntArray node, Object data){
		ExprInput input = new ExprInput(assertExprInput(data));
		checkExpectedType(input, "int[]", node);
		input.expectedType = "int";
		node.jjtGetChild(0).jjtAccept(this, input);
		return "new int[]";
	}
	
	public Object visit(ASTNewObject node, Object data){
		ExprInput input = assertExprInput(data);
		String className = getVal(node.jjtGetChild(0));
		if(symbolTable.lookup(className) == null){
			throw new ReferencedMissingType(input.context, className, node);
		}
		checkExpectedType(input, className, node);
		return className;
	}






	


	//--------------------------------------------
	//                 HELPERS
	//--------------------------------------------

	//Makes sure input is valid, and casts it.
	//Purely for programmer's sake, error is unrecoverable
	private Context assertDataContext(Object data){
		if(data == null){
			throw new IllegalStateException("data == " + data);
		}
		if(!(data instanceof Context)){
			throw new IllegalStateException("data == " + data);
		}
		return (Context) data;
	}

	//Makes sure input is valid, and casts it.
	//Purely for programmer's sake, error is unrecoverable
	private ExprInput assertExprInput(Object data){
		if(data == null){
			throw new IllegalStateException("data == " + data);
		}
		if(!(data instanceof ExprInput)){
			throw new IllegalStateException("data == " + data);
		}
		return (ExprInput) data;
	}

	//If input specifies which type an expression is expected to evaluate to,
	//then compare it to the actual type, and throw exception if they don't match
	private void checkExpectedType(ExprInput input, String actualExprType, SimpleNode node){
		if(input.expectedType != null && !typesAreCompatible(input.expectedType, false, actualExprType, true, input.context, node)){
			throw new WrongType(input.context, input.expectedType, actualExprType, node);	
		}
	}

	//Lets you specify that actual type is one of the two given.
	private void checkExpectedType(ExprInput input, String actualExprType1, String actualExprType2, SimpleNode node){
		if(input.expectedType != null && !(input.expectedType.equals(actualExprType1)) && !(input.expectedType.equals(actualExprType2))) {
			throw new WrongType(input.context, input.expectedType, actualExprType1 + "||" + actualExprType2, node);
		}
	}

	//Get the type of the variable specified by context, 
	//compare it to the expected type.
	//If they don't match, throw exception
	private void checkVarType(Context context, String expectedType, SimpleNode node){
		String varType = getVarType(context, node);
		if(!typesAreCompatible(expectedType, false, varType, true, context, node)){
			throw new WrongType(context, expectedType, varType, node);
		}
	}

	//Get the type of the variable specified by context
	//Throw exception if variable is not found
	private String getVarType(Context context, SimpleNode node){
		if(context == null || context.className == null || context.methodName == null || context.varName == null){
			throw new NullPointerException("context == " + context);
		}
		ClassData classData = (ClassData) symbolTable.lookup(context.className);
		MethodData methodData = (MethodData) classData.methodsTable.lookup(context.methodName);
		String varType;
		if((varType = (String)methodData.localsTable.lookup(context.varName)) == null){
			if((varType = (String)methodData.paramsTable.lookup(context.varName)) == null){
				if((varType = (String)classData.fieldsTable.lookup(context.varName)) == null){
					if(classData.hasSuperClass){
						Context superClassContext = new Context(classData.superClass, "", context.varName);
						return getClassFieldVarType(superClassContext, node);
					}else{
						throw new ReferencedMissingVariable(context, node);							
					}
				}
			}
		}
		return varType;
	}

	//unlike in getVarType() this method checks only instance-variables (not parameters or locals)
	private String getClassFieldVarType(Context context, SimpleNode node){
		if(context == null || context.className == null || context.varName == null){
			throw new NullPointerException("context == " + context);
		}
		ClassData classData = (ClassData) symbolTable.lookup(context.className);
		String varType;
		if((varType = (String)classData.fieldsTable.lookup(context.varName)) == null){
			if(classData.hasSuperClass){
				Context superClassContext = new Context(classData.superClass, "", context.varName);
				return getClassFieldVarType(superClassContext, node);
			}else{
				throw new ReferencedMissingVariable(context, node);							
			}
		}
		return varType;
	}

	private String getFunctionReturnType(Context context){
		if(context == null || context.className == null || context.methodName == null || context.varName != null){
			throw new NullPointerException("context == " + context);
		}
		ClassData classData = (ClassData) symbolTable.lookup(context.className);

		MethodData methodData = (MethodData) classData.methodsTable.lookup(context.methodName);
		return methodData.returnType;
	}

	//Given as input to all expression-visit-methods.
	//Context denotes the current class.method() context, 
	//and possibly even the "current variable context"
	//Example variable context:
	//a = 1;
	//When evaluating expression "1", the context will be "class.method().a"
	private class ExprInput{
		Context context;
		String expectedType;
		ExprInput(Context s, String t){
			context = s;
			expectedType = t;
		}
		ExprInput(Object other){
			this(((ExprInput)other).context, ((ExprInput)other).expectedType);
		}
	}

}
