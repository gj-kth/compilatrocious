package mjc.frame;

import mjc.temp.Label;
import mjc.temp.Temp;
import mjc.temp.TempList;
import mjc.temp.TempMap;
import mjc.temp.DefaultMap;
import mjc.tree.Exp;
import mjc.tree.ExpList;
import mjc.tree.Stm;

import mjc.parser.VisitorUtil.*;

import java.util.List;
import java.util.HashMap;


/**
   Interface for handling of frames on the runtime stack. The
   interface is independent of the target architecture, but it is
   designed for real processors. For virtual machines, use the @see
   VMFrame interface.
*/
public abstract class Frame
{
	private Label name;
	private int size;
	private int wordSize;

	private HashMap<String,Temp> tempMap;

	private Context context;
	private mjc.tree.Stm tree;

	abstract public void print();

	/*
 	 * Return Temp if s exists in tempMap, otherwise return new Temp
 	 */
	abstract public Temp getTemp(String s);

	abstract public Frame newFrame(Label name);

    /**
       The label corresponding to the assembler entry point
       for the function using the frame.
       @return A Label object that contains the label.
    */
    public Label name(){
		return name;
	}

    /**
       @return The size of the frame in bytes.
    */
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

    /**
       Returns a list of Access objects, one for each formal 
       parameter. The first Access object correspons to the
       first formal etc.

       @return A List of Access objects.
    */
    abstract public java.util.List<Access> formals();

    /**
       Allocates an Access object corresponding to a local
       variable in the function. The Access objects may
       be allocated in any order, but all formal variables
       must be allocated before any local variable is
       allocated.

       @param escape A boolean variable stating if the function
       parameter escapes outside the function. Typically, a
       non-escaping parameter may be put in a register

       @return An Access object that describes how the corresponding
       local variable is accessed from within the function.
    */
    abstract public Access allocLocal(boolean escape);

    /**
       Returns an Access object that can be used to access
       an outgoing parameter. Such an object comes in handy
       in code generation.

       @param index The index (starting from zero) of the parameter.
    */
    abstract public Access accessOutgoing(int index);

    /**
       Encapsulates naming conventions for external functions,
       calling conventions, etc.

       @param func The name of the external function as seen
       from within MiniJava.

       @param args The arguments to the function.

       @return Tree code that calls the external function.
    */
    abstract public Exp externalCall(String func, ExpList args);

    /**
       Appends tree code to move incoming arguments into the
       places where the function body is expecting them to be.

       @param body The function body, including code to move
       the return value into the RV register.

       @return Tree code that first moves incoming arguments
       to their correct places and then executes the function
       body.
    */
    abstract public Stm procEntryExit1(Stm body);

    /**
       Appends a "sink" Assem instruction that "uses" special registers
       i.e., framepointer, retur value register etc.

       @param inst The list of instructions to which the sink instruction
       should be appended.

       @return The modified List with the sink instruction.
    */
    abstract public List<mjc.assem.Instr> procEntryExit2(List<mjc.assem.Instr> inst);

    /**
       Produces first part of prologue:
       pseudo instructions to announce procedure start
       a label for the procedure name
       instruction to adjust stack pointer

       Also produces last part of epilogue:
       Instruction to reset stack pointer
       The return instruction
       pseudo instructions to indicate procedure end

       @param inst The list of instructions to which the sink instruction
       should be appended.

       @return a Proc object
    */
    abstract public mjc.frame.Proc procEntryExit3(List<mjc.assem.Instr> body);
    

    /**
       @return The return value register register.
    */
    abstract public Temp RV();

    /**
       @return The frame pointer register.
    */
    abstract public Temp FP();

    /**
       @return The machine's natural word size.
    */
    public int wordSize() {
		return wordSize;
	}

    /**
       @return The translation of a tree.Stm into a list of assem.Instr.
    */
    abstract public List<mjc.assem.Instr> codegen(mjc.tree.Stm stm);

    /**
       @return A TempMap mapping Temp:s corresponding to hardware 
       registers to their names, represented as String:s.
    */
    abstract public TempMap initial();
    
    /**
       @return A TempList corresponding to the registers of the 
       underlying hardware. Note that only registers that are to be
       used at register allocation are returned. This normally excludes 
       the frame pointer and the stack pointer, for instance.
    */
    abstract public TempList registers();
    
};
