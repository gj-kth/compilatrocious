package mjc.parser;
import mjc.tree.*;
import mjc.temp.*;
import mjc.frame.*;

import mjc.parser.VisitorUtil.*;
import mjc.parser.Symbol;

import java.util.ArrayList;
import java.util.Set;
import java.util.Map;
import java.util.Map.Entry;

public class IRVisitor extends VisitorAdapter{

	Print p;
	Frame ft;
	SymbolTable st;

	public IRVisitor(Frame frametype, SymbolTable symbols) {
		ft = frametype;
		st = symbols;
		p = new Print(System.out);
	}

	public Object visit(ASTProgram node, Object data){
		ArrayList<Frame> program = new ArrayList<Frame>();
		ArrayList<Frame> classMethods;
		Frame mainMethod;

		mainMethod = (Frame)node.jjtGetChild(0).jjtAccept(this,data);
		program.add(mainMethod);

		classMethods = (ArrayList<Frame>)node.jjtGetChild(1).jjtAccept(this,data);
		if(classMethods!=null) {
			program.addAll(classMethods);
		}

		return program;
	}

	public Object visit(ASTMainClass node, Object data) {
		Frame mainMethod;

		// Extract classname
		String name = ((Token)((SimpleNode)node.jjtGetChild(0)).jjtGetValue()).image;

		Node method = node.jjtGetChild(1); // Ignore child 0???
		mainMethod = (Frame) method.jjtAccept(this, name);
		return mainMethod;
	}

	public Object visit(ASTClassDecls node, Object data) {
		ArrayList<Frame> frames = new ArrayList<Frame>();
		for(int i = 0; i < node.jjtGetNumChildren(); i++) {
			frames.addAll((ArrayList<Frame>)node.jjtGetChild(i).jjtAccept(this,data));
		}
		return frames;
	}

	public Object visit(ASTClassDecl node, Object data) {
		ArrayList<Frame> frames;

		// Extract classname
		String name = ((Token)((SimpleNode)node.jjtGetChild(0)).jjtGetValue()).image;

		Node methods = node.jjtGetChild(2);
		frames = (ArrayList<Frame>) methods.jjtAccept(this, name);

		return frames;
	}

	/*
 	 * Recieves (String)classname as data
 	 */
	public Object visit(ASTMethodDecls node, Object data){
		ArrayList<Frame> frames = new ArrayList<Frame>();

		for(int i = 0; i < node.jjtGetNumChildren(); i++) {
			frames.add((Frame)node.jjtGetChild(i).jjtAccept(this,data));
		}

		return frames;
	}

	/*
 	 * Recieves (String)classname as data
 	 */
	public Object visit(ASTMethodDecl node, Object data){

		String name = ((Token)((SimpleNode)node.jjtGetChild(1)).jjtGetValue()).image;
		Node body = node.jjtGetChild(3);
		Node ret = node.jjtGetChild(4);

		// Create a new frame for the method
		Frame frame = ft.newFrame(new Label((String) data + "$" + name));

		// Generate the IR tree representing the method body
		Expr retexpr = (Expr) ret.jjtAccept(this, frame);
		mjc.tree.Stm tree = (mjc.tree.Stm) body.jjtAccept(this, frame);

		mjc.tree.Stm retstmt = new MOVE(new TEMP(frame.RV()),retexpr.unEx());
		frame.setTree(new SEQ(tree,retstmt));

		// Set the context of the frame
		frame.setContext(new Context((String) data, name));

		return frame;
	}

	/*
 	 * Recieves (String)classname as data
 	 */
	public Object visit(ASTMainMethod node, Object data) {
		Node body = node.jjtGetChild(1);

		// Create a new frame for the method
		Frame frame = ft.newFrame(new Label((String) data + "$main"));

		// Generate the IR tree representing the method body
		mjc.tree.Stm tree = (mjc.tree.Stm) body.jjtAccept(this, frame);
		mjc.tree.Stm retstmt = new MOVE(new TEMP(frame.RV()),new CONST(0));
		frame.setTree(new SEQ(tree,retstmt));

		// Set the context of the frame
		frame.setContext(new Context((String) data, "main"));

		return frame;
	}

	public Object visit(ASTMethodBody node, Object data){
		Node stmts = node.jjtGetChild(1);

		Expr tree = (Expr) stmts.jjtAccept(this, data);

		return tree.unNx();
	}
	
	public Object visit(ASTStmts node, Object data){
		// Get last stmt

		// If body is empty, return nop
		if(node.jjtGetNumChildren() == 0)
			return new Nx(new EXP(new CONST(0)));

		mjc.tree.Stm stmts = ((Expr) node.jjtGetChild(
					node.jjtGetNumChildren()-1).jjtAccept(this,data)).unNx();

		// Chain stmts using SEQs beginning from the innermost stmt
		for(int i = node.jjtGetNumChildren()-2; i >= 0; i--){
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
		return visitBoolBinop(CJUMP.LT, node, data);
	}

	public Object visit(ASTLessEqual node, Object data){
		return visitBoolBinop(CJUMP.LE, node, data);
	}

	public Object visit(ASTGreaterThan node, Object data){
		return visitBoolBinop(CJUMP.GT, node, data);
	}

	public Object visit(ASTGreaterEqual node, Object data){
		return visitBoolBinop(CJUMP.GE, node, data);
	}

	public Object visit(ASTEqual node, Object data){
		return visitBoolBinop(CJUMP.EQ, node, data);
	}

	public Object visit(ASTNotEqual node, Object data){
		return visitBoolBinop(CJUMP.NE, node, data);
	}
	
	public Object visit(ASTAnd node, Object data){
		return visitIntBinop(BINOP.AND, node, data);
	}

	public Object visit(ASTOr node, Object data){
		return visitIntBinop(BINOP.OR, node, data);
	}
	
	public Object visit(ASTPlus node, Object data){
		return visitIntBinop(BINOP.PLUS, node, data);
	}
	
	public Object visit(ASTMinus node, Object data){
		return visitIntBinop(BINOP.MINUS, node, data);
	}
	
	public Object visit(ASTMult node, Object data){
		return visitIntBinop(BINOP.MUL, node, data);
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

	public Object visit(ASTNewObject node, Object data){
		String className = ((Token)((SimpleNode)node.jjtGetChild(0)).jjtGetValue()).image;

		ClassData classData = (ClassData) st.lookup(className);
		Set<Map.Entry<Symbol,Object>> set = classData.fieldsTable.getHashMap().entrySet();

		for(Map.Entry<Symbol,Object> entry : set) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}

		return new Nx(new EXP(new CONST(0)));
	}
	public Object visit(ASTNewIntArray node, Object data){
		Node s = node.jjtGetChild(0);

		Expr size = (Expr) s.jjtAccept(this,data);

		Temp tmp = new Temp();
		// LABEL WHICH LEADS TO WHERE?!??!?!
		Label initArray = new Label("initArray");

		return new Ex(new ESEQ(
				new MOVE(new TEMP(tmp),
					size.unEx()),
				new CALL(new NAME(initArray),new ExpList(
					new BINOP(BINOP.MUL,new TEMP(tmp),
						new CONST(ft.wordSize())), new TEMP(tmp)))));
	}

	public Object visit(ASTIfElse node, Object data){
		Node bn = node.jjtGetChild(0);
		Node in = node.jjtGetChild(1);
		Node en = node.jjtGetChild(2);

		Expr boolExp = (Expr) bn.jjtAccept(this,data);
		Expr ifstmt = (Expr) in.jjtAccept(this,data);
		Expr elstmt = (Expr) en.jjtAccept(this,data);

		return createIfElse(boolExp, ifstmt, elstmt);
	}

	private Nx createIfElse(Expr boolExp, Expr ifstmt, Expr elstmt){
		Label t = new Label();
		Label f = new Label();
		Label afterFalse = new Label();

		return new Nx(new SEQ(boolExp.unCx(t,f),
				new SEQ(new LABEL(t),
					new SEQ(ifstmt.unNx(),
						new SEQ(new JUMP(afterFalse),
							new SEQ(new LABEL(f), 
								new SEQ(elstmt.unNx(),
									new LABEL(afterFalse))))))));	
	}
	
	public Object visit(ASTIf node, Object data){
		Node bn = node.jjtGetChild(0);
		Node in = node.jjtGetChild(1);

		Label t = new Label();
		Label f = new Label();

		Expr boolExp = (Expr) bn.jjtAccept(this,data);
		Expr ifstmt = (Expr) in.jjtAccept(this,data);

		return new Nx(new SEQ(boolExp.unCx(t,f),
				new SEQ(new LABEL(t),
					new SEQ(ifstmt.unNx(), new LABEL(f)))));
	}

	public Object visit(ASTWhile node, Object data){
		Node bn = node.jjtGetChild(0);
		Node lb = node.jjtGetChild(1);

		Label t = new Label();
		Label f = new Label();

		Expr boolExp = (Expr) bn.jjtAccept(this,data);
		Expr loopBody = (Expr) lb.jjtAccept(this,data);

		return new Nx(new SEQ(boolExp.unCx(t,f),
				new SEQ(new LABEL(t),
					new SEQ(loopBody.unNx(),
						new SEQ(boolExp.unCx(t,f),new LABEL(f))))));
	}

	public Ex visitIntBinop(int op, SimpleNode node, Object data) {
		Node left = node.jjtGetChild(0);
		Node right = node.jjtGetChild(1);
		Expr expr1 = (Expr) left.jjtAccept(this, data);
		Expr expr2 = (Expr) right.jjtAccept(this, data);
		return new Ex(new BINOP(op, expr1.unEx(), expr2.unEx()));
	}

	public Cx visitBoolBinop(int op, SimpleNode node, Object data) {
		Node left = node.jjtGetChild(0);
		Node right = node.jjtGetChild(1);
		Expr expr1 = (Expr) left.jjtAccept(this,data);
		Expr expr2 = (Expr) right.jjtAccept(this,data);
		return new Cx(op, expr1.unEx(), expr2.unEx());
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
			return new CJUMP(CJUMP.NE,exp,new CONST(0), t, f); //TODO
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
		int type; // one of: EQ, NE, LT, GT, LE, GE, ULT, ULE, UGT, UGE, (NOT AND, OR, ...)

		Cx(int type, mjc.tree.Exp exp1, mjc.tree.Exp exp2) {
			this.type = type;
			this.exp1 = exp1;
			this.exp2 = exp2;
		}

		public mjc.tree.Exp unEx() {
			Expr boolExp = new Cx(type, exp1, exp2);
			Temp temp = new Temp();
			Expr ifStmt = new Nx(new MOVE(new TEMP(temp), new CONST(1)));
			Expr elStmt = new Nx(new MOVE(new TEMP(temp), new CONST(0)));
			Stm storeInTemp = createIfElse(boolExp, ifStmt, elStmt).unNx();
			return new ESEQ(storeInTemp, new TEMP(temp));
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
