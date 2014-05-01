package mjc.parser;



public class JVMVisitor extends VisitorAdapter{
	
	private SymbolTable symbolTable;

	public JVMVisitor(SymbolTable symbolTable){
		this.symbolTable = symbolTable;	
	}

	public Object visit(ASTIdentifier node, Object data){
		return getVal(node);
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
		String className = (String) classNameId.jjtAccept(this, null);
		Node mainMethod = node.children[1];
		StringBuilder mainMethodCode = (StringBuilder) mainMethod.jjtAccept(this, null);
		StringBuilder code = new StringBuilder();
		code.append(".class " + className + "\n");
		code.append(".super java/lang/Object\n");
		code.append("\n; default constructor\n");
		code.append(".method public <init>()V\n");
		code.append("   .aload_0\n");
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
		int stackSize = 100000;
		code.append("   .limit stack " + stackSize + "\n");
		StringBuilder bodyCode = new StringBuilder();
		code.append(bodyCode);
		code.append("   return\n");
		code.append(".end method\n");
		return code;
	}
	
}
