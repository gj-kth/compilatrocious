package mjc.frame;

import mjc.temp.Label;
import mjc.temp.Temp;
import mjc.temp.TempList;
import mjc.temp.TempMap;
import mjc.tree.Exp;
import mjc.tree.ExpList;
import mjc.tree.Stm;
import mjc.tree.Print;

import mjc.parser.VisitorUtil.*;

import java.util.List;
import java.util.HashMap;

public class X86Frame extends Frame {
	private Label name;
	private int size;
	private Temp retReg;
	private Temp fpReg;
	private static final int wordSize = 64;

	private HashMap<String,Temp> tempMap;

	private Context context;
	private mjc.tree.Stm tree;

	public void print() {
		System.out.println(name + " RV: " + retReg + " FP: " + fpReg);
		Print p = new Print(System.out);
		if(tree!=null)
			p.prStm(tree);
		else
			System.out.println("No tree");
	}

	public Label name(){
		return name;
	}
	public int size(){
		return size;
	}

	public mjc.tree.Stm getTree() {
		return tree;
	}
	public void setTree(mjc.tree.Stm tree) {
		this.tree = tree;
	}

	public void setContext(Context c) {
		this.context = c;
	}

	public Temp getTemp(String s) {
		if(tempMap.containsKey(s)) {
			return tempMap.get(s);
		}else{
			Temp t = new Temp();
			tempMap.put(s,t);
			return t;
		}
	}

	public X86Frame newFrame(Label name) {
		X86Frame frame = new X86Frame();
		frame.name = name;
		frame.tempMap = new HashMap<String,Temp>();
		frame.retReg = new Temp();
		frame.fpReg = new Temp();
		return frame;
	}

    public java.util.List<Access> formals() {
		return null;
	}

    public Access allocLocal(boolean escape) {
		return null;
	}

    public Access accessOutgoing(int index) {
		return null;
	}

    public Exp externalCall(String func, ExpList args) {
		return null;
	}

    public Stm procEntryExit1(Stm body) {
		return null;
	}

    public List<mjc.assem.Instr> procEntryExit2(List<mjc.assem.Instr> inst) {
		return null;
	}

    public mjc.frame.Proc procEntryExit3(List<mjc.assem.Instr> body) {
		return null;
	}

    public Temp RV() {
		return retReg;
	}

    public Temp FP() {
		return fpReg;
	}

    public int wordSize() {
		return wordSize;
	}

    public List<mjc.assem.Instr> codegen(mjc.tree.Stm stm) {
		return null;
	}

    public TempMap initial() {
		return null;
	}
    
    public TempList registers() {
		return null;
	}
}
