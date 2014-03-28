package mjc.parser;

import java.util.*;
import mjc.parser.VisitorUtil.Pair;
import mjc.parser.VisitorUtil.ClassData;
import mjc.parser.VisitorUtil.MethodData;

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
		Node mainMethod = node.jjtGetChild(1);
		return mainMethod.jjtAccept(this, null);
	}
	
	//Returns a set of id-types (classes) that were declared
	public Object visit(ASTVarDecls node, Object data){
		return shouldNotBeVisited(node, data);
	}

	public Object visit(ASTVarDecl node, Object data){
		return shouldNotBeVisited(node, data);
	}
	
	public Object visit(ASTMethodDecls node, Object data){
		return getChildrenTypeValues(node);
	}
	
	public Object visit(ASTClassDecl node, Object data){
		Node methodDecls = node.jjtGetChild(2);
		return methodDecls.jjtAccept(this,null);
	}
	
	public Object visit(ASTMethodBody node, Object data){
		Node stmts = node.jjtGetChild(1);
		return stmts.jjtAccept(this, null);
	}
	
	public Object visit(ASTStmts node, Object data){
		return shouldNotBeVisited(node, data);

		//TODO

		//TODO

	}
	
	public Object visit(ASTMainMethod node, Object data){
		Node body = node.jjtGetChild(1);
		return body.jjtAccept(this, null);
	}
	
	public Object visit(ASTMethodDecl node, Object data){
		String returnType = (String) node.jjtGetChild(0).jjtAccept(this, null);
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

	
	public Object visit(ASTIntLiteral node, Object data) throws IllegalArgumentException{
		VariableScopeWithName scope = (VariableScopeWithName) data;
		ClassData classData = (ClassData) symbolTable.lookup(scope.className);
		MethodData methodData = (MethodData) classData.methodsTable.lookup(scope.method);
		if(methodData.localsTable.lookup(scope.varName) == null){
			if(methodData.paramsTable.lookup(scope.varName) == null){
				if(classData.fieldsTable.lookup(scope.varName) == null){
					throw new IllegalArgumentException("Invalid reference to " + scope.varName + " in scope " + scope);
				}
			}
		}
		return ((Token)((SimpleNode)node).value).image;
	}

	public Object visit(ASTBoolLiteral node, Object data){
		return ((Token)((SimpleNode)node).value).image;
	}

	public Object visit(ASTStmt node, Object data){
		return node.jjtGetChild(0).jjtAccept(this, null);
	}
	
	public Object visit(ASTBlock node, Object data){
		return visitChildren(node, null);
	}
	
	//data == scope
	public Object visit(ASTSingleAssignment node, Object data){
		VariableScope scope = (VariableScope) data;
		String varName = (String) node.jjtGetChild(0).jjtAccept(this, null);
		Node expr = node.jjtGetChild(1);
		checkValidReference(scope, varName);
		String exprType = (String) expr.jjtAccept(this, new VariableScopeWithName(scope.className, scope.method, varName));
		return null;
	}
	
	public Object visit(ASTArrayAssignment node, Object data){
		return shouldNotBeVisited(node, data);
	}

	public Object visit(ASTIfElse node, Object data){
		return shouldNotBeVisited(node, data);
	}
	
	public Object visit(ASTIf node, Object data){
		return shouldNotBeVisited(node, data);
	}
	
	public Object visit(ASTPrint node, Object data){
		return shouldNotBeVisited(node, data);
	}
	
	public Object visit(ASTWhile node, Object data){
		return shouldNotBeVisited(node, data);
	}
	

	//data == scope
	public Object visit(ASTExpr node, Object data){
		return node.jjtGetChild(0).jjtAccept(this, data);
	}
	
	public Object visit(ASTLessThan node, Object data){
		return shouldNotBeVisited(node, data);
	}
	
	public Object visit(ASTAnd node, Object data){
		return shouldNotBeVisited(node, data);
	}
	
	public Object visit(ASTPlus node, Object data){
		return shouldNotBeVisited(node, data);
	}
	
	public Object visit(ASTMinus node, Object data){
		return shouldNotBeVisited(node, data);
	}
	
	public Object visit(ASTMult node, Object data){
		return shouldNotBeVisited(node, data);
	}
	
	public Object visit(ASTArrayAccess node, Object data){
		return shouldNotBeVisited(node, data);
	}
	
	public Object visit(ASTCall node, Object data){
		return shouldNotBeVisited(node, data);
	}
	
	public Object visit(ASTArrayLength node, Object data){
		return shouldNotBeVisited(node, data);
	}
	
	public Object visit(ASTThis node, Object data){
		return shouldNotBeVisited(node, data);
	}
	
	public Object visit(ASTNegExpr node, Object data){
		return shouldNotBeVisited(node, data);
	}
	
	public Object visit(ASTNewIntArray node, Object data){
		return shouldNotBeVisited(node, data);
	}
	
	public Object visit(ASTNewObject node, Object data){
		return shouldNotBeVisited(node, data);
	}
	
	public Object visit(ASTExprList node, Object data){
		return shouldNotBeVisited(node, data);
	}

	private void checkValidReference(VariableScope scope, String varName){

		//TODO

		//TODO
	}

	private class VariableScope{
		String className;
		String method;
		VariableScope(String c, String m){
			className = c;
			method = m;
		}

		public String toString(){
			return className + "." + method + "()";
		}
	}

	private class VariableScopeWithName extends VariableScope{
		String varName;
		VariableScopeWithName(String c, String m, String n){
			super(c,m);
			varName = n;
		}

		public String toString(){
			return super.toString() + "." + varName;
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