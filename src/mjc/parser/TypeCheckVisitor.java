package mjc.parser;

import java.util.*;
import mjc.parser.VisitorUtil.Pair;
import mjc.parser.VisitorUtil.ClassData;
import mjc.parser.VisitorUtil.MethodData;
import mjc.parser.VisitorUtil.VariableScope;
import mjc.parser.VisitorUtil.VariableScopeWithName;

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
	
	public Object visit(ASTClassDecls node, Object data){
		return visitChildren(node, null);
	}
	
	public Object visit(ASTMainClass node, Object data){
		String className = getVal(node.jjtGetChild(0));
		Node mainMethod = node.jjtGetChild(1);
		return mainMethod.jjtAccept(this, new VariableScope(className, "main"));
	}

	public Object visit(ASTMainMethod node, Object data){
		Node body = node.jjtGetChild(1);
		return body.jjtAccept(this, data);
	}
	
	public Object visit(ASTMethodDecls node, Object data){
		return visitChildren(node, data);
	}
	
	public Object visit(ASTClassDecl node, Object data){
		Node methodDecls = node.jjtGetChild(2);
		return methodDecls.jjtAccept(this,null);
	}
	
	public Object visit(ASTMethodBody node, Object data){
		Node stmts = node.jjtGetChild(1);
		return stmts.jjtAccept(this, data);
	}
	
	public Object visit(ASTStmts node, Object data){
		return visitChildren(node, data);
	}
	
	
	
	public Object visit(ASTMethodDecl node, Object data){
		Node methodBody = node.jjtGetChild(3);
		Node returnStmt = node.jjtGetChild(4);
		//returnStmt.jjtAccept(this, returnType); //Check that returnstatement evaluates to correct type!

		//TODO

		//TODO

		return null;

	}

	
	public Object visit(ASTIdentifier node, Object data){
		return getVal(node);
	}
	
	

	public Object visit(ASTStmt node, Object data){
		return node.jjtGetChild(0).jjtAccept(this, data);
	}
	
	public Object visit(ASTBlock node, Object data){
		return visitChildren(node, data);
	}
	
	//data == scope
	public Object visit(ASTSingleAssignment node, Object data){
		if(data == null){
			new Exception().printStackTrace();
			System.exit(1);
		}
		VariableScope scope = (VariableScope) data;
		String varName = (String) node.jjtGetChild(0).jjtAccept(this, null);
		Node expr = node.jjtGetChild(1);
		checkValidReference(scope, varName);
		expr.jjtAccept(this, new ExprInput(new VariableScopeWithName(scope.className, scope.method, varName), null));
		return null;	
	}
	
	public Object visit(ASTArrayAssignment node, Object data){
		VariableScope scope = (VariableScope) data;
		String varName = (String) node.jjtGetChild(0).jjtAccept(this, null);
		Node expr = node.jjtGetChild(2); //2, not 1
		checkValidReference(scope, varName);
		expr.jjtAccept(this, new VariableScopeWithName(scope.className, scope.method, varName));
		return null;
	}

	public Object visit(ASTIfElse node, Object data){
		Node booleanExpr = node.jjtGetChild(0);
		Node stmt1 = node.jjtGetChild(1);
		Node stmt2 = node.jjtGetChild(2);
		booleanExpr.jjtAccept(this, new ExprInput(null, "boolean"));
		stmt1.jjtAccept(this, data);
		stmt2.jjtAccept(this, data);
		return null;
	}
	
	public Object visit(ASTIf node, Object data){
		return null;
	}
	
	public Object visit(ASTPrint node, Object data){
		return null;
	}
	
	public Object visit(ASTWhile node, Object data){
		return null;
	}
	

	//data == ExprInput
	public Object visit(ASTExpr node, Object data){
		return node.jjtGetChild(0).jjtAccept(this, data);
	}
	
	//data == ExprInput
	public Object visit(ASTIntLiteral node, Object data){
		return visitLiteral(node, data, "int");
	}

	//data == ExprInput
	public Object visit(ASTBoolLiteral node, Object data){
		return visitLiteral(node, data, "boolean");
	}

	private Object visitLiteral(Node node, Object data, String type){
		ExprInput input = (ExprInput) data;
		if(input.expectedType != null && !(input.expectedType.equals(type))){
			throw new WrongType(input.varScope, input.expectedType, type);
		}
		if(input.varScope != null){
			checkVariableType(input.varScope, type);	
		}
		return ((Token)((SimpleNode)node).value).image;
	}

	public Object visit(ASTLessThan node, Object data){
		ExprInput input = (ExprInput) data;
		if(input.expectedType != null && !(input.expectedType.equals("boolean"))){
			throw new WrongType(input.varScope, input.expectedType, "boolean");
		}
		Node expr1 = node.jjtGetChild(0);
		Node expr2 = node.jjtGetChild(1);
		input.expectedType = "int";
		expr1.jjtAccept(this, input);
		expr2.jjtAccept(this, input);
		return null;
	}
	
	public Object visit(ASTAnd node, Object data){
		ExprInput input = (ExprInput) data;
		if(input.expectedType != null && !(input.expectedType.equals("boolean"))){
			throw new WrongType(input.varScope, input.expectedType, "boolean");
		}
		Node expr1 = node.jjtGetChild(0);
		Node expr2 = node.jjtGetChild(1);
		expr1.jjtAccept(this, input);
		expr2.jjtAccept(this, input);
		return null;
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

	public Object visitIntBinop(Node node, Object data){
		ExprInput input = (ExprInput) data;
		if(input.expectedType != null && !(input.expectedType.equals("int"))){
			throw new WrongType(input.varScope, input.expectedType, "int");
		}
		Node expr1 = node.jjtGetChild(0);
		Node expr2 = node.jjtGetChild(1);
		expr1.jjtAccept(this, input);
		expr2.jjtAccept(this, input);
		return null;
	}
	
	public Object visit(ASTArrayAccess node, Object data){
		return null;
	}
	
	public Object visit(ASTCall node, Object data){
		return null;
	}
	
	public Object visit(ASTArrayLength node, Object data){
		return null;
	}
	
	public Object visit(ASTThis node, Object data){
		return null;
	}
	
	public Object visit(ASTNegExpr node, Object data){
		return null;
	}
	
	public Object visit(ASTNewIntArray node, Object data){
		return null;
	}
	
	public Object visit(ASTNewObject node, Object data){
		return null;
	}
	
	public Object visit(ASTExprList node, Object data){
		return null;
	}

	private void checkValidReference(VariableScope scope, String varName){

		//TODO

		//TODO
	}

	private void checkVariableType(VariableScopeWithName scope, String expectedType){
		ClassData classData = (ClassData) symbolTable.lookup(scope.className);
		MethodData methodData = (MethodData) classData.methodsTable.lookup(scope.method);
		String varType;
		if((varType = (String)methodData.localsTable.lookup(scope.varName)) == null){
			if((varType = (String)methodData.paramsTable.lookup(scope.varName)) == null){
				if((varType = (String)classData.fieldsTable.lookup(scope.varName)) == null){
					throw new ReferencedMissingVariable(scope);
				}
			}
		}
		if(!(varType.equals(expectedType))){
			throw new WrongType(scope, expectedType, varType);
		}
	}

	

	private class ExprInput{
		VariableScopeWithName varScope;
		String expectedType;
		ExprInput(VariableScopeWithName s, String t){
			varScope = s;
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