package mjc.parser;
import mjc.tree.*;
import mjc.temp.*;
import mjc.frame.*;

public class IRVisitor extends VisitorAdapter{

	Print p;
	Frame ft;

	public IRVisitor(Frame frametype) {
		ft = frametype;
	}

	public Object visit(ASTProgram node, Object data){
		p = new Print(System.out);
		visitChildren(node, null);
		return null;
	}

	public Object visit(ASTMainClass node, Object data) {
		Node method = node.jjtGetChild(1); // Ignore child 0???
		mjc.tree.Stm tree = (mjc.tree.Stm) method.jjtAccept(this, data);
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

		// Create a new frame for the method
		Frame frame = ft.newFrame(new Label("main$main"));
		System.out.println(frame.name());

		// Generate the IR tree representing the method body
		mjc.tree.Stm tree = (mjc.tree.Stm) body.jjtAccept(this, frame);

		// Print the tree
		p.prStm(tree);

		return tree;
	}

	public Object visit(ASTMethodBody node, Object data){
		Node decls = node.jjtGetChild(0);
		Node stmts = node.jjtGetChild(1);

		Frame frame = (Frame) data;
		Expr tree = (Expr) stmts.jjtAccept(this, data);
		return tree.unNx();
	}
	
	public Object visit(ASTStmts node, Object data){
		// Generate a dummy stmt as last statement, change.
		mjc.tree.Stm stmts = (new Ex(new CONST(0))).unNx();

		// Chain stmts using SEQs beginning from the innermost stmt
		for(int i = node.jjtGetNumChildren()-1; i >= 0; i--){
			Expr stm = (Expr) node.jjtGetChild(i).jjtAccept(this, data);
			stmts = new SEQ(stm.unNx(), stmts);
		}

		return new Nx(stmts);
	}
	
	public Object visit(ASTStmt node, Object data){
		Node child = node.jjtGetChild(0);

		// Generate subtree and convert to a statement
		Expr stmt = (Expr) child.jjtAccept(this,data);
		return new Nx(stmt.unNx());
	}

	public Object visit(ASTBlock node, Object data){
		// COPY OF STMTS NOW, MAYBE MERGE CODE!??
		// Generate a dummy stmt as last statement, change.
		mjc.tree.Stm stmts = (new Ex(new CONST(0))).unNx();

		// Chain stmts using SEQs beginning from the innermost stmt
		for(int i = node.jjtGetNumChildren()-1; i >= 0; i--){
			Expr stm = (Expr) node.jjtGetChild(i).jjtAccept(this, data);
			stmts = new SEQ(stm.unNx(), stmts);
		}

		return new Nx(stmts);
	}
	
	public Object visit(ASTSingleAssignment node, Object data){
		Node ident = node.jjtGetChild(0);
		Node value = node.jjtGetChild(1); //Expr
		Expr access = (Expr) ident.jjtAccept(this,data);
		Expr expr = (Expr) value.jjtAccept(this,data);

		// This is a statement which does not return a value
		return new Nx(new MOVE(access.unEx(), expr.unEx()));
	}

	public Object visit(ASTExpr node, Object data){
		return node.jjtGetChild(0).jjtAccept(this, data);
	}
	
	public Object visit(ASTLessThan node, Object data){
		Node left = node.jjtGetChild(0);
		Node right = node.jjtGetChild(1);
		Expr expr1 = (Expr) left.jjtAccept(this,data);
		Expr expr2 = (Expr) right.jjtAccept(this,data);
		return new Cx(CJUMP.LT, expr1.unEx(), expr2.unEx());
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
		return new Ex(new CONST(Integer.parseInt(((Token)node.value).image)));
	}

	public Object visit(ASTIdentifier node, Object data){
		// Access the temp variable within the frame
		String id = ((Token)node.value).image;
		Frame frame = (Frame) data;
		return new Ex(new TEMP(frame.getTemp(id)));
	}

	public Object visit(ASTBoolLiteral node, Object data){
		String lit = ((Token) node.value).image;
		if(lit.equals("true")) {
			return new Ex(new CONST(1));
		}else{
			return new Ex(new CONST(0));
		}
	}

	public Object visit(ASTIfElse node, Object data){
		Node bn = node.jjtGetChild(0);
		Node in = node.jjtGetChild(1);
		Node en = node.jjtGetChild(2);

		Label t = new Label();
		Label f = new Label();

		Expr boolExp = (Expr) bn.jjtAccept(this,data);
		Expr ifstmt = (Expr) in.jjtAccept(this,data);
		Expr elstmt = (Expr) en.jjtAccept(this,data);

		return new Nx(new SEQ(boolExp.unCx(t,f),
				new SEQ(new LABEL(t),
					new SEQ(ifstmt.unNx(),
						new SEQ(new LABEL(f), elstmt.unNx())))));
	}
	
	public Object visit(ASTIf node, Object data){
		Node boolExp = node.jjtGetChild(0);
		Node ifstmt = node.jjtGetChild(1);
		return null;
	}

	public Ex visitBinop(int op, SimpleNode node, Object data) {
		Node left = node.jjtGetChild(0);
		Node right = node.jjtGetChild(1);
		Expr expr1 = (Expr) left.jjtAccept(this, data);
		Expr expr2 = (Expr) right.jjtAccept(this, data);
		return new Ex(new BINOP(op, expr1.unEx(), expr2.unEx()));
	}

	/*
 	 * INTERNAL EXPR CLASSES
 	 *
 	 * Used to convert different kinds of statements and expressions to
 	 * the kind that is wanted in a parent expression
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
		mjc.tree.Exp exp1;
		mjc.tree.Exp exp2;
		int type;

		Cx(int type, mjc.tree.Exp exp1, mjc.tree.Exp exp2) {
			this.type = type;
			this.exp1 = exp1;
			this.exp2 = exp2;
		}

		public mjc.tree.Exp unEx() {
			return null; //TODO
		}

		public mjc.tree.Stm unNx() {
			return null; //TODO
		}

		public mjc.tree.Stm unCx(mjc.temp.Label t, mjc.temp.Label f) {

			// if cjump is true, jump to t, else to f
			return new CJUMP(type, exp1, exp2, t, f);
		}
	}
}
