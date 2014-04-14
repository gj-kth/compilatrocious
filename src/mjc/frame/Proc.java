package mjc.frame;

public class Proc 
{
    public String begin, end;
    public java.util.List<mjc.assem.Instr> body;
    public Proc(String bg, java.util.List<mjc.assem.Instr> bd, String ed)
    {
	begin = bg; end = ed; body = bd;	
    }
}
