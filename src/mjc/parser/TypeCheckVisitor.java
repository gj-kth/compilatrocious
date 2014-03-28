package mjc.parser;

import java.util.*;
import mjc.parser.VisitorUtil.Pair;
import mjc.parser.VisitorUtil.ClassData;
import mjc.parser.VisitorUtil.MethodData;
import mjc.parser.VisitorUtil.Scope;

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
		return mainMethod.jjtAccept(this, new Scope(className, "main"));
	}

	public Object visit(ASTMainMethod node, Object data){
		Node body = node.jjtGetChild(1);
		return body.jjtAccept(this, data);
	}

	public Object visit(ASTClassDecls node, Object data){
		return visitChildren(node, null);
	}

	public Object visit(ASTClassDecl node, Object data){
		Node methodDecls = node.jjtGetChild(2);
		return methodDecls.jjtAccept(this,null);
	}

	public Object visit(ASTMethodDecls node, Object data){
		return visitChildren(node, data);
	}
	
	public Object visit(ASTMethodDecl node, Object data){
		Node methodId = node.jjtGetChild(1);
		String methodName = getVal(methodId);
		Node methodBody = node.jjtGetChild(3);
		methodBody.jjtAccept(this, data);
		Node returnExp = node.jjtGetChild(4);
		//ExprInput exprInput = new ExprInput(
		//returnStmt.jjtAccept(this, returnType); //Check that returnstatement evaluates to correct type!

		//TODO

		//TODO
		return null;

	}
	
	public Object visit(ASTMethodBody node, Object data){
		Node stmts = node.jjtGetChild(1);
		return stmts.jjtAccept(this, data);
	}
	
	public Object visit(ASTStmts node, Object data){
		return visitChildren(node, data);
	}
	
	
	
	

	
	public Object visit(ASTIdentifier node, Object data){
		
		if(data instanceof ExprInput){ //This Identifier is an expression
			



			//TODO

			//Kolla att variablen med denna id finns, och att den är av rätt typ!



			//((ExprInput)data).
			//Scope scope;
			//String expectedType;
		}

		return getVal(node);
	}
	
	






	//--------------------------------------------
	//                 STATEMENTS
	//--------------------------------------------

	public Object visit(ASTStmt node, Object data){
		return node.jjtGetChild(0).jjtAccept(this, data);
	}
	
	public Object visit(ASTBlock node, Object data){
		return visitChildren(node, data);
	}
	
	//data == scope
	public Object visit(ASTSingleAssignment node, Object data){
		if(data == null){ //TODO
			throw new NullPointerException("DATA == NULL IN VISIT(ASTSINGLEASSIGN .....    ");
		}
		Scope scope = (Scope) data;
		String varName = (String) node.jjtGetChild(0).jjtAccept(this, null);
		Node expr = node.jjtGetChild(1);
		scope.varName = varName;
		String varType = getVarType(scope, node);
		expr.jjtAccept(this, new ExprInput(scope, varType));
		return null;	
	}
	
	public Object visit(ASTArrayAssignment node, Object data){
		Scope scope = (Scope) data;
		String varName = (String) node.jjtGetChild(0).jjtAccept(this, null);
		Node expr = node.jjtGetChild(2); //2, not 1
		scope.varName = varName;
		String varType = getVarType(scope, node);
		if(!(varType.equals("int[]"))){
			throw new WrongType(scope, "int[]", varType, node);
		}
		expr.jjtAccept(this, new ExprInput(scope, varType));
		return null;
	}

	public Object visit(ASTIfElse node, Object data){
		return visitCondStmts(node, data, 2);
	}
	
	public Object visit(ASTIf node, Object data){
		return visitCondStmts(node, data, 1);
	}

	public Object visit(ASTWhile node, Object data){
		return visitCondStmts(node, data, 1);
	}

	private Object visitCondStmts(Node node, Object data, int numStmts){
		Node booleanExpr = node.jjtGetChild(0);
		booleanExpr.jjtAccept(this, new ExprInput(null, "boolean"));
		for(int i = 0; i < numStmts; i++){
			Node stmt = node.jjtGetChild(1 + i);
			stmt.jjtAccept(this, data);
		}
		return null;
	}
	
	public Object visit(ASTPrint node, Object data){
		Scope scope = (Scope) data;
		return node.jjtGetChild(0).jjtAccept(this, new ExprInput(scope, null));
	}
	
	
	








	//--------------------------------------------
	//                 EXPRESSIONS
	//--------------------------------------------

	/**
	*	Expression-visit-methods take ExprInput as data
	*	and should return the type of the evaluated expression
	*/


	//data == ExprInput
	public Object visit(ASTExpr node, Object data){
		return node.jjtGetChild(0).jjtAccept(this, data);
	}

	public Object visit(ASTNegExpr node, Object data){
		ExprInput input = (ExprInput) data;
		input.expectedType = "boolean";
		return node.jjtGetChild(0).jjtAccept(this, input);
	}
	
	//data == ExprInput
	public Object visit(ASTIntLiteral node, Object data){
		return visitLiteral(node, data, "int");
	}

	//data == ExprInput
	public Object visit(ASTBoolLiteral node, Object data){
		return visitLiteral(node, data, "boolean");
	}

	private Object visitLiteral(SimpleNode node, Object data, String type){
		ExprInput input = (ExprInput) data;
		if(input.expectedType != null && !(input.expectedType.equals(type))){
			throw new WrongType(input.scope, input.expectedType, type, node);
		}
		// if(input.scope != null){
		// 	checkVarType(input.scope, type, node);
		// }
		return ((Token)((SimpleNode)node).value).image;
	}

	public Object visit(ASTLessThan node, Object data){
		ExprInput input = (ExprInput) data;
		if(input.expectedType != null && !(input.expectedType.equals("boolean"))){
			throw new WrongType(input.scope, input.expectedType, "boolean", node);
		}
		Node expr1 = node.jjtGetChild(0);
		Node expr2 = node.jjtGetChild(1);
		input.expectedType = "int";
		expr1.jjtAccept(this, input);
		expr2.jjtAccept(this, input);
		return "boolean";
	}
	
	public Object visit(ASTAnd node, Object data){
		ExprInput input = (ExprInput) data;
		if(input.expectedType != null && !(input.expectedType.equals("boolean"))){
			throw new WrongType(input.scope, input.expectedType, "boolean", node);
		}
		Node expr1 = node.jjtGetChild(0);
		Node expr2 = node.jjtGetChild(1);
		expr1.jjtAccept(this, input);
		expr2.jjtAccept(this, input);
		return "boolean";
	}
	
	public Object visit(ASTPlus node, Object data){
		return visitIntBinop(node, data);
	}
	
	public Object visit(ASTMinus node, Object data){
		return visitIntBinop(node, data);
	}
	
	public Object visit(ASTMult node, Object data){
		return visitIntBinop(node, data);
	}

	public Object visitIntBinop(SimpleNode node, Object data){
		ExprInput input = (ExprInput) data;
		if(input.expectedType != null && !(input.expectedType.equals("int"))){
			throw new WrongType(input.scope, input.expectedType, "int", node);
		}
		Node expr1 = node.jjtGetChild(0);
		Node expr2 = node.jjtGetChild(1);
		expr1.jjtAccept(this, input);
		expr2.jjtAccept(this, input);
		return "int";
	}
	
	public Object visit(ASTArrayAccess node, Object data){
		return "int";
	}
	
	public Object visit(ASTCall node, Object data){
		ExprInput input = (ExprInput) data;
		Node objExp = node.jjtGetChild(0);
		Node methodId = node.jjtGetChild(1);
		Node expList = node.jjtGetChild(2);
		String methodName = getVal(methodId);
		String objType = (String) objExp.jjtAccept(this, input);
		String returnType = checkCall(objType, methodName, input.expectedType, (SimpleNode)methodId);
		return returnType;
	}

	public Object visit(ASTExprList node, Object data){
		return visitChildren(node, data);
	}

	//Returns return type of method
	private String checkCall(String className, String methodName, String returnType, SimpleNode node){
		ClassData classData = (ClassData) symbolTable.lookup(className);
		if(classData == null){
			throw new ReferencedMissingType(null, className, node);
		}
		MethodData methodData = (MethodData) classData.methodsTable.lookup(methodName);
		if(methodData == null){
			throw new ReferencedMissingMethod(null, methodName);
		}
		if(!(methodData.returnType.equals(returnType))){
			throw new WrongType(new Scope(className, methodName), methodData.returnType, returnType, node);
		}
		return methodData.returnType;
	}
	
	public Object visit(ASTArrayLength node, Object data){
		ExprInput input = (ExprInput) data;
		input.expectedType = "int";
		return node.jjtGetChild(0).jjtAccept(this, input);
	}
	
	public Object visit(ASTThis node, Object data){
		return null;
	}
	
	public Object visit(ASTNewIntArray node, Object data){
		ExprInput input = (ExprInput) data;
		input.expectedType = "int";
		return node.jjtGetChild(0).jjtAccept(this, input);
	}
	
	public Object visit(ASTNewObject node, Object data){
		ExprInput input = (ExprInput) data;
		String className = getVal(node.jjtGetChild(0));
		if(symbolTable.lookup(className) == null){
			throw new ReferencedMissingType(input.scope, className, node);
		}
		return className;
	}






	


	//--------------------------------------------
	//                 HELPERS
	//--------------------------------------------

	private void checkVarType(Scope scope, String expectedType, SimpleNode node){
		String varType = getVarType(scope, node);
		if(!(varType.equals(expectedType))){
			throw new WrongType(scope, expectedType, varType, node);
		}
	}

	private String getVarType(Scope scope, SimpleNode node){
		if(scope == null || scope.className == null || scope.methodName == null || scope.varName == null){
			throw new NullPointerException("scope == " + scope);
		}
		ClassData classData = (ClassData) symbolTable.lookup(scope.className);
		MethodData methodData = (MethodData) classData.methodsTable.lookup(scope.methodName);
		String varType;
		if((varType = (String)methodData.localsTable.lookup(scope.varName)) == null){
			if((varType = (String)methodData.paramsTable.lookup(scope.varName)) == null){
				if((varType = (String)classData.fieldsTable.lookup(scope.varName)) == null){
					throw new ReferencedMissingVariable(scope, node);
				}
			}
		}
		return varType;
	}



	private class ExprInput{
		Scope scope;
		String expectedType;
		ExprInput(Scope s, String t){
			scope = s;
			expectedType = t;
		}
	}

	private Set<String> getChildrenTypeValues(Node parent){
		Set<String> types = new HashSet<String>();
		for(int i = 0; i < parent.jjtGetNumChildren(); i++){
			Node child = parent.jjtGetChild(i);
			String type = (String) child.jjtAccept(this, null);
			types.add(type);
		}
		return types;
	}

}
