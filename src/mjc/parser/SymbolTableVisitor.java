package mjc.parser;
import mjc.parser.VisitorUtil.Pair;
import mjc.parser.VisitorUtil.ClassData;
import mjc.parser.VisitorUtil.MethodData;
import java.util.*;

public class SymbolTableVisitor extends VisitorAdapter{

	private SymbolTable symbolTable;

	public SymbolTableVisitor(){
		symbolTable = new SymbolTable();
	}

	public Object visit(ASTProgram node, Object data){
		visitChildren(node, null);
		return symbolTable;
	}
	
	public Object visit(ASTClassDecls node, Object data){
		for(int i = 0; i < node.jjtGetNumChildren(); i++){
		    Node classDecl = node.jjtGetChild(i);
			Pair<String, ClassData> mapping = (Pair<String,ClassData>) classDecl.jjtAccept(this, null);
			symbolTable.insert(mapping.first, mapping.second);
		}
		return null;
	}
	
	public Object visit(ASTMainClass node, Object data){
		Node classNameId = node.children[0];
		Node mainMethod = node.children[1];
		MethodData mainMethodData = (MethodData) mainMethod.jjtAccept(this, null);
		String className = (String) classNameId.jjtAccept(this, null);
		SymbolTable methodsTable = new SymbolTable();
		methodsTable.insert("main", mainMethodData);
		symbolTable.insert(className, new ClassData(new SymbolTable(), methodsTable));
		return null;
	}
	
	public Object visit(ASTVarDecls node, Object data){
		SymbolTable varsTable = new SymbolTable();
		HashMap<String,String> paramsTable = null;
		if(data != null){ // when called from methodBody, compare to params, make sure no duplicate decls.
			paramsTable = (HashMap<String,String>) data;
		}
		for(int i = 0; i < node.jjtGetNumChildren(); i ++){
			Node varDecl = node.jjtGetChild(i);
			Node type = varDecl.jjtGetChild(0);
			String typeString = (String) type.jjtAccept(this, null);
			String id = getVal(varDecl.jjtGetChild(1));

			if(paramsTable != null && paramsTable.containsKey(id)){
				throw new DuplicateDeclaration(Symbol.symbol(id), typeString, paramsTable.get(id));
			}
			varsTable.insert(id, typeString);
		}
		return varsTable;
	}
	
	public Object visit(ASTMethodDecls node, Object data){
		SymbolTable methodsTable = new SymbolTable();
		for(int i = 0; i < node.jjtGetNumChildren(); i++){
			Node methodDecl = node.jjtGetChild(i);
			Pair<String, MethodData> mapping = (Pair<String, MethodData>) methodDecl.jjtAccept(this, null);
			methodsTable.insert(mapping.first, mapping.second);
		}
		return methodsTable;
	}
	
	public Object visit(ASTClassDecl node, Object data){
		Node classNameId = node.children[0];
		Node varDecls = node.children[1];
		Node methodDecls = node.children[2];
		SymbolTable fieldsTable = (SymbolTable) varDecls.jjtAccept(this, null);
		SymbolTable methodsTable = (SymbolTable) methodDecls.jjtAccept(this, null);
		String className = (String) classNameId.jjtAccept(this, null);
		return new Pair<String, ClassData>(className, new ClassData(fieldsTable, methodsTable));
	}

	public Object visit(ASTSubClassDecl node, Object data){
		Node classNameId = node.children[0];
		Node superClassNameId = node.children[1];
		Node varDecls = node.children[2];
		Node methodDecls = node.children[3];
		SymbolTable fieldsTable = (SymbolTable) varDecls.jjtAccept(this, null);
		SymbolTable methodsTable = (SymbolTable) methodDecls.jjtAccept(this, null);
		String className = (String) classNameId.jjtAccept(this, null);
		String superClassName = (String) superClassNameId.jjtAccept(this,null);
		return new Pair<String, ClassData>(className, new ClassData(superClassName, fieldsTable, methodsTable));
	}
	
	public Object visit(ASTMethodBody node, Object data){
		Node varDecls = node.jjtGetChild(0);
		return varDecls.jjtAccept(this,data);
	}
	
	public Object visit(ASTMainMethod node, Object data){
		Node argNameId = node.jjtGetChild(0);
		Node methodBody = node.jjtGetChild(1);
		SymbolTable paramsTable = new SymbolTable();
		String argNameString = getVal(argNameId);
		paramsTable.insert(argNameString, "String[]");
		SymbolTable localsTable = (SymbolTable)methodBody.jjtAccept(this,paramsTable.getHashMap());
		List<String> paramTypes = new ArrayList<String>();
		paramTypes.add("String");
		return new MethodData("void", paramsTable, paramTypes, localsTable);
	}
	
	public Object visit(ASTMethodDecl node, Object data){
		Node returnType = node.jjtGetChild(0);
		Node methodNameId = node.jjtGetChild(1);
		Node argList = node.jjtGetChild(2);
		Node methodBody = node.jjtGetChild(3);
		HashMap<String,String> paramsTable = (HashMap<String,String>) argList.jjtAccept(this,data);
		SymbolTable localsTable = (SymbolTable) methodBody.jjtAccept(this, paramsTable);
		String returnTypeString = (String) returnType.jjtAccept(this, null);
		String methodNameString = (String) methodNameId.jjtAccept(this, null);
		List<String> paramTypes = new ArrayList(paramsTable.values());

		return new Pair<String, MethodData>(methodNameString, new MethodData(returnTypeString, new SymbolTable(paramsTable), paramTypes, localsTable));
	}
	

	public Object visit(ASTArgList node, Object data){
		//LinkedHashMap ensures that arguments are returned in correct order.
		LinkedHashMap<String,String> paramsTable = new LinkedHashMap<String,String>(); 
		for(int i = 0; i < node.jjtGetNumChildren(); i++){
			Node arg = node.jjtGetChild(i);
			Pair<String,String> mapping = (Pair<String,String>) arg.jjtAccept(this, null);
			if(paramsTable.containsKey(mapping.second)){
				throw new DuplicateDeclaration(Symbol.symbol(mapping.second), mapping.first, paramsTable.get(mapping.second));
			}
			paramsTable.put(mapping.second, mapping.first);
		}
		return paramsTable;
	}
	
	public Object visit(ASTArg node, Object data){
		String type = (String) node.jjtGetChild(0).jjtAccept(this, null);
		String identifier = (String) node.jjtGetChild(1).jjtAccept(this, null);
		return new Pair<String, String>(type, identifier);
	}
	
	public Object visit(ASTIdentifier node, Object data){
		return getVal(node);
	}
	
	public Object visit(ASTInt node, Object data){
		return "int";
	}

	public Object visit(ASTIntArray node, Object data){
		return "int[]";
	}
	
	public Object visit(ASTBoolean node, Object data){
		return "boolean";
	}
	
	public Object visit(ASTType node, Object data){
		return node.jjtGetChild(0).jjtAccept(this,data);
	}

}
