package mjc.parser;
import mjc.tree.*;
import mjc.temp.*;
import mjc.frame.*;

public class IRVisitor extends VisitorAdapter{

	public Object visit(ASTProgram node, Object data){
		visitChildren(node, null);
		return null;
	}

}
