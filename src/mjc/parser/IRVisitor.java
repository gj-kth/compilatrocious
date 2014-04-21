package mjc.parser;
import mjc.tree.*;
import mjc.temp.*;
import mjc.frame.*;

public class IRVisitor extends VisitorAdapter{

	Print p;

	public Object visit(ASTProgram node, Object data){
		p = new Print(System.out);
		visitChildren(node, null);
		return null;
	}

	public Object visit(ASTMainClass node, Object data) {
		Node method = node.jjtGetChild(1); // Ignore child 0???
		mjc.tree.Exp exp = (mjc.tree.Exp) method.jjtAccept(this, data);
		return null;
	}

	public Object visit(ASTClassDecls node, Object data) {
		visitChildren(node, null);
		return null;
	}

	public Object visit(ASTClassDecl node, Object data) {
		visitChildren(node, null);
		return null;
	}

	public Object visit(ASTMainMethod node, Object data) {
		Node body = node.jjtGetChild(1); // Ignore child 0???
		mjc.tree.Exp exp = (mjc.tree.Exp) body.jjtAccept(this, data);
		return exp;
	}

	public Object visit(ASTMethodBody node, Object data){
		Node stmts = node.jjtGetChild(1); // Ignore child 0???
		mjc.tree.Exp exp = (mjc.tree.Exp) stmts.jjtAccept(this, data);
		return exp;
	}
	
	public Object visit(ASTStmts node, Object data){
		visitChildren(node, data); //Chain together SEQs
		return null;
	}
	
	public Object visit(ASTStmt node, Object data){
		Node child = node.jjtGetChild(0);
		mjc.tree.Stm stmt = (mjc.tree.Stm) child.jjtAccept(this,data);
		p.prStm(stmt);
		return stmt;
	}
	
	public Object visit(ASTSingleAssignment node, Object data){
		Node ident = node.jjtGetChild(0);
		Node value = node.jjtGetChild(1); //Expr
		mjc.tree.Exp access = (mjc.tree.Exp) ident.jjtAccept(this,data);
		mjc.tree.Exp expr = (mjc.tree.Exp) value.jjtAccept(this,data);
		return new MOVE(access, expr);
	}

	public Object visit(ASTExpr node, Object data){
		return node.jjtGetChild(0).jjtAccept(this, data);
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

	public Object visit(ASTOr node, Object data){
		return shouldNotBeVisited(node, data);
	}
	
	public Object visit(ASTPlus node, Object data){
		return visitBinop(BINOP.PLUS, node, data);
	}
	
	public Object visit(ASTMinus node, Object data){
		return visitBinop(BINOP.MINUS, node, data);
	}
	
	public Object visit(ASTMult node, Object data){
		return visitBinop(BINOP.MUL, node, data);
	}

	public Object visit(ASTIntLiteral node, Object data){
		return new CONST(Integer.parseInt(((Token)node.value).image));
	}

	public Object visit(ASTIdentifier node, Object data){
		// Temp access
		String id = ((Token)node.value).image;
		return new TEMP(new Temp());
	}

	public Object visit(ASTBoolLiteral node, Object data){
		String lit = ((Token) node.value).image;
		if(lit.equals("true")) {
			return new CONST(1);
		}else{
			return new CONST(0);
		}
	}

	public mjc.tree.Exp visitBinop(int op, SimpleNode node, Object data) {
		Node left = node.jjtGetChild(0);
		Node right = node.jjtGetChild(1);
		mjc.tree.Exp expr1 = (mjc.tree.Exp) left.jjtAccept(this, data);
		mjc.tree.Exp expr2 = (mjc.tree.Exp) right.jjtAccept(this, data);
		return new BINOP(op, expr1, expr2);
	}

	/*
 	 * INTERNAL EXPR CLASSES
 	 *
 	 */
	public abstract class Expr {

		abstract public mjc.tree.Exp unEx();

		abstract public mjc.tree.Stm unNx();

		abstract public mjc.tree.Stm unCx(mjc.temp.Label t, mjc.temp.Label f);
	}

	public class Ex extends Expr {
		mjc.tree.Exp exp;

		Ex(mjc.tree.Exp e) {
			exp = e;
		}

		public mjc.tree.Exp unEx() {
			return exp;
		}

		public mjc.tree.Stm unNx() {
			return new mjc.tree.EXP(exp); //Converts exp to a Stm
		}

		public mjc.tree.Stm unCx(mjc.temp.Label t, mjc.temp.Label f) {
			return null; //TODO
		}
	}

	public class Nx extends Expr {
		mjc.tree.Stm stm;

		Nx(mjc.tree.Stm s) {
			stm = s;
		}

		public mjc.tree.Exp unEx() {
			return new ESEQ(stm, new CONST(0));
		}

		public mjc.tree.Stm unNx() {
			return stm;
		}

		public mjc.tree.Stm unCx(mjc.temp.Label t, mjc.temp.Label f) {
			return null; //TODO
		}
	}

	public class Cx extends Expr {
		mjc.tree.Stm stm;

		Cx(mjc.tree.Stm s) {
			stm = s;
		}

		public mjc.tree.Exp unEx() {
			return null; //TODO
		}

		public mjc.tree.Stm unNx() {
			return stm;
		}

		public mjc.tree.Stm unCx(mjc.temp.Label t, mjc.temp.Label f) {
			return null; //TODO
		}
	}
}
