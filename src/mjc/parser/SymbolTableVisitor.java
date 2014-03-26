package mjc.parser;

public class SymbolTableVisitor implements MiniJavaVisitor{

	private SymbolTable symbolTable;

	public SymbolTableVisitor(){
		symbolTable = new SymbolTable();
	}
	
	public Object visit(SimpleNode node, Object data){
		return shouldNotBeVisited(node, data);
	}
	
	public Object visit(ASTProgram node, Object data){
		visitChildren(node, data);
		System.out.println(symbolTable.toString(""));
		return null;
	}
	
	public Object visit(ASTClassDecls node, Object data){
		return visitChildren(node, data);
	}
	
	public Object visit(ASTMainClass node, Object data){
		Node classNameId = node.children[0];
		Node mainMethod = node.children[1];
		MethodData mainMethodData = (MethodData) mainMethod.jjtAccept(this, data);
		String className = (String) classNameId.jjtAccept(this, data);
		SymbolTable methodsTable = new SymbolTable();
		methodsTable.insert("main", mainMethodData);
		symbolTable.insert(className, new ClassData(new SymbolTable(), methodsTable));
		return null;
	}
	
	public Object visit(ASTVarDecls node, Object data){
		SymbolTable varsTable = new SymbolTable();
		for(int i = 0; i < node.jjtGetNumChildren(); i ++){
			Node varDecl = node.jjtGetChild(i);
			Node type = varDecl.jjtGetChild(0);
			String typeString = (String) type.jjtAccept(this, data);
			String id = getVal(varDecl.jjtGetChild(1));
			varsTable.insert(id, typeString);
		}
		return varsTable;
	}
	
	public Object visit(ASTMethodDecls node, Object data){
		SymbolTable methodsTable = new SymbolTable();
		for(int i = 0; i < node.jjtGetNumChildren(); i++){
			Node methodDecl = node.jjtGetChild(i);
			methodDecl.jjtAccept(this, methodsTable); //TODO Sending symTable to child, and letting it modify it.  INCONSISTENT WITH SOME OTHER METHODS
		}
		return methodsTable;
	}
	
	public Object visit(ASTClassDecl node, Object data){
		Node classNameId = node.children[0];
		Node varDecls = node.children[1];
		Node methodDecls = node.children[2];
		SymbolTable fieldsTable = (SymbolTable) varDecls.jjtAccept(this, data);
		SymbolTable methodsTable = (SymbolTable) methodDecls.jjtAccept(this, data);
		String className = (String) classNameId.jjtAccept(this, data);
		symbolTable.insert(className, new ClassData(fieldsTable, methodsTable));
		return null;
	}
	
	public Object visit(ASTMethodBody node, Object data){
		Node varDecls = node.jjtGetChild(0);
		return varDecls.jjtAccept(this,data);
	}
	
	public Object visit(ASTStmts node, Object data){
		return visitChildren(node, data);
	}
	
	public Object visit(ASTMainMethod node, Object data){
		Node argNameId = node.jjtGetChild(0);
		Node methodBody = node.jjtGetChild(1);
		SymbolTable paramsTable = new SymbolTable();
		String argNameString = getVal(argNameId);
		paramsTable.insert(argNameString, "String");
		SymbolTable localsTable = (SymbolTable)methodBody.jjtAccept(this,data);
		return new MethodData("void", paramsTable, localsTable);
	}
	
	public Object visit(ASTMethodDecl node, Object data){
		SymbolTable methodsTable = (SymbolTable) data;
		Node returnType = node.jjtGetChild(0);
		Node methodNameId = node.jjtGetChild(1);
		Node argList = node.jjtGetChild(2);
		Node methodBody = node.jjtGetChild(3);
		SymbolTable paramsTable = (SymbolTable) argList.jjtAccept(this,data);
		SymbolTable localsTable = (SymbolTable) methodBody.jjtAccept(this, data);
		String returnTypeString = (String) returnType.jjtAccept(this, data);
		String methodNameString = (String) methodNameId.jjtAccept(this, data);
		methodsTable.insert(methodNameString, new MethodData(returnTypeString, paramsTable, localsTable));
		return null;
	}
	
	public Object visit(ASTArgList node, Object data){
		SymbolTable paramsTable = new SymbolTable();
		for(int i = 0; i < node.jjtGetNumChildren(); i++){
			Node arg = node.jjtGetChild(i);
			String type = (String) arg.jjtGetChild(0).jjtAccept(this, data);
			String identifier = (String) arg.jjtGetChild(1).jjtAccept(this, data);
			paramsTable.insert(identifier, type);
		}
		return paramsTable;
	}
	
	public Object visit(ASTArg node, Object data){
		return visitChildren(node, data);
	}
	
	public Object visit(ASTVarDecl node, Object data){
		return shouldNotBeVisited(node, data);
	}
	
	public Object visit(ASTIdentifier node, Object data){
		return getVal(node);
	}
	
	public Object visit(ASTInt node, Object data){
		return visitChildren(node, data);
	}
	
	public Object visit(ASTIntLiteral node, Object data){
		return visitChildren(node, data);
	}
	
	public Object visit(ASTIntArray node, Object data){
		return "int[]";
	}
	
	public Object visit(ASTBoolean node, Object data){
		return "boolean";
	}
	
	public Object visit(ASTBoolLiteral node, Object data){
		return visitChildren(node, data);
	}
	
	public Object visit(ASTType node, Object data){
		return node.jjtGetChild(0).jjtAccept(this,data);
	}
	
	public Object visit(ASTStmt node, Object data){
		return visitChildren(node, data);
	}
	
	public Object visit(ASTBlock node, Object data){
		return visitChildren(node, data);
	}
	
	public Object visit(ASTSingleAssignment node, Object data){
		return visitChildren(node, data);
	}
	
	public Object visit(ASTArrayAssignment node, Object data){
		return visitChildren(node, data);
	}
	
	public Object visit(ASTIfElse node, Object data){
		return visitChildren(node, data);
	}
	
	public Object visit(ASTIf node, Object data){
		return visitChildren(node, data);
	}
	
	public Object visit(ASTPrint node, Object data){
		return visitChildren(node, data);
	}
	
	public Object visit(ASTWhile node, Object data){
		return visitChildren(node, data);
	}
	
	public Object visit(ASTExpr node, Object data){
		return visitChildren(node, data);
	}
	
	public Object visit(ASTLessThan node, Object data){
		return visitChildren(node, data);
	}
	
	public Object visit(ASTAnd node, Object data){
		return visitChildren(node, data);
	}
	
	public Object visit(ASTPlus node, Object data){
		return visitChildren(node, data);
	}
	
	public Object visit(ASTMinus node, Object data){
		return visitChildren(node, data);
	}
	
	public Object visit(ASTMult node, Object data){
		return visitChildren(node, data);
	}
	
	public Object visit(ASTArrayAccess node, Object data){
		return visitChildren(node, data);
	}
	
	public Object visit(ASTCall node, Object data){
		return visitChildren(node, data);
	}
	
	public Object visit(ASTArrayLength node, Object data){
		return visitChildren(node, data);
	}
	
	public Object visit(ASTThis node, Object data){
		return visitChildren(node, data);
	}
	
	public Object visit(ASTNegExpr node, Object data){
		return visitChildren(node, data);
	}
	
	public Object visit(ASTNewIntArray node, Object data){
		return visitChildren(node, data);
	}
	
	public Object visit(ASTNewObject node, Object data){
		return visitChildren(node, data);
	}
	
	public Object visit(ASTExprList node, Object data){
		return visitChildren(node, data);
	}

	private Object visitChildren(Node parent, Object data){
		for(int i = 0; i < parent.jjtGetNumChildren(); i ++){
			parent.jjtGetChild(i).jjtAccept(this, data);
		}
		return null;
	}

	private String getVal(Node node){
		Object val = ((SimpleNode)node).value;
		return val.toString();
	}

	private class ClassData implements HasPrefixedToString{
		SymbolTable fieldsTable;
		SymbolTable methodsTable;
		public ClassData(SymbolTable f, SymbolTable m){
			fieldsTable = f;
			methodsTable = m;
		}

		public String toString(String prefix){
			return prefix + "fields:\n" + fieldsTable.toString(prefix + "\t") + "\n" + prefix + "methods:\n" + methodsTable.toString(prefix + "\t");
		}
	}

	private class MethodData implements HasPrefixedToString{
		String returnType;
		SymbolTable paramsTable;
		SymbolTable localsTable;
		public MethodData(String r, SymbolTable p, SymbolTable l){
			returnType = r;
			paramsTable = p;
			localsTable = l;
			if(paramsTable == null){
				throw new IllegalArgumentException("paramsTable is null");
			}
			if(localsTable == null){
				throw new IllegalArgumentException("localsTable is null");
			}
		}

		public String toString(String prefix){
			return prefix + "params:\n" + paramsTable.toString(prefix + "\t") + "\n" + prefix + "locals:\n" + localsTable.toString(prefix + "\t");
		}
	}

	private Object shouldNotBeVisited(SimpleNode node, Object data){
		new Exception("visit should not be called with this class(?)  node: " + node.getClass() + ", data: " + data).printStackTrace();
		System.exit(0);
		return null;
	}
}