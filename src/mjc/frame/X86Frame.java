package mjc.frame;

import mjc.temp.Label;
import mjc.temp.Temp;
import mjc.temp.TempList;
import mjc.temp.TempMap;
import mjc.tree.Exp;
import mjc.tree.ExpList;
import mjc.tree.Stm;

import java.util.List;
import java.util.HashMap;

public class X86Frame extends Frame {
	private Label name;
	private int size;
	private static final int wordSize = 64;

	private HashMap<String,Temp> tempMap;

	public Label name(){
		return name;
	}
	public int size(){
		return size;
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
		return null;
	}

    public Temp FP() {
		return null;
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
