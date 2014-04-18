package mjc.parser;

public abstract class VisitorAdapter implements MiniJavaVisitor{

	
	public Object visit(SimpleNode node, Object data){
		return shouldNotBeVisited(node, null);
	}
	
	public Object visit(ASTProgram node, Object data){
		return shouldNotBeVisited(node, data);
	}
	
	public Object visit(ASTClassDecls node, Object data){
		return shouldNotBeVisited(node, data);
	}
	
	public Object visit(ASTMainClass node, Object data){
		return shouldNotBeVisited(node, data);
	}
	
	public Object visit(ASTVarDecls node, Object data){
		return shouldNotBeVisited(node, data);
	}
	
	public Object visit(ASTMethodDecls node, Object data){
		return shouldNotBeVisited(node, data);
	}
	
	public Object visit(ASTClassDecl node, Object data){
		return shouldNotBeVisited(node, data);
	}
	
	public Object visit(ASTMethodBody node, Object data){
		return shouldNotBeVisited(node, data);
	}
	
	public Object visit(ASTStmts node, Object data){
		return shouldNotBeVisited(node, data);
	}
	
	public Object visit(ASTMainMethod node, Object data){
		return shouldNotBeVisited(node, data);
	}
	
	public Object visit(ASTMethodDecl node, Object data){
		return shouldNotBeVisited(node, data);
	}
	
	public Object visit(ASTArgList node, Object data){
		return shouldNotBeVisited(node, data);
	}
	
	public Object visit(ASTArg node, Object data){
		return shouldNotBeVisited(node, data);
	}
	
	public Object visit(ASTVarDecl node, Object data){
		return shouldNotBeVisited(node, null);
	}
	
	public Object visit(ASTIdentifier node, Object data){
		return shouldNotBeVisited(node, data);
	}
	
	public Object visit(ASTInt node, Object data){
		return shouldNotBeVisited(node, data);
	}
	
	public Object visit(ASTIntLiteral node, Object data){
		return shouldNotBeVisited(node, data);
	}
	
	public Object visit(ASTIntArray node, Object data){
		return shouldNotBeVisited(node, data);
	}
	
	public Object visit(ASTBoolean node, Object data){
		return shouldNotBeVisited(node, data);
	}
	
	public Object visit(ASTBoolLiteral node, Object data){
		return shouldNotBeVisited(node, data);
	}
	
	public Object visit(ASTType node, Object data){
		return shouldNotBeVisited(node, data);
	}
	
	public Object visit(ASTStmt node, Object data){
		return shouldNotBeVisited(node, data);
	}
	
	public Object visit(ASTBlock node, Object data){
		return shouldNotBeVisited(node, data);
	}
	
	public Object visit(ASTSingleAssignment node, Object data){
		return shouldNotBeVisited(node, data);
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
	
	public Object visit(ASTExpr node, Object data){
		return shouldNotBeVisited(node, data);
	}
	
	public Object visit(ASTLessThan node, Object data){
		return shouldNotBeVisited(node, data);
	}

	public Object visit(ASTLessEqual node, Object data){
		return shouldNotBeVisited(node, data);
	}

	public Object visit(ASTGreaterThan node, Object data){
		return shouldNotBeVisited(node, data);
	}

	public Object visit(ASTGreaterEqual node, Object data){
		return shouldNotBeVisited(node, data);
	}

	public Object visit(ASTEqual node, Object data){
		return shouldNotBeVisited(node, data);
	}

	public Object visit(ASTNotEqual node, Object data){
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

	public Object visit(ASTParenExp node, Object data){
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

	protected String getVal(Node node){
		Object val = ((SimpleNode)node).value;
		return val.toString();
	}

	protected Object shouldNotBeVisited(SimpleNode node, Object data){
		new Exception("visit should not be called with this class(?)  node: " + node.getClass() + ", data: " + data).printStackTrace();
		System.exit(0);
		return null;
	}

	protected Object visitChildren(Node parent, Object data){
		for(int i = 0; i < parent.jjtGetNumChildren(); i ++){
			parent.jjtGetChild(i).jjtAccept(this, data);
		}
		return null;
	}
}
