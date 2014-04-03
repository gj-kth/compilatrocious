package mjc.parser;
import mjc.parser.VisitorUtil.Context;

public class WrongNumberArgs extends TypecheckError{
	public WrongNumberArgs(Context scope, int expectedNum, int foundNum, SimpleNode node){
		super(str(scope, expectedNum, foundNum, node));
	}

	private static String str(Context scope, int expectedNum, int foundNum, SimpleNode node){
		if(node.value != null){
			return "Context: " + scope + ", expected '" + expectedNum + "', found '" + foundNum + "' at line "
                        + ((Token)node.value).beginLine + " column " + ((Token)node.value).beginColumn;
		}
		return "Context: " + scope + ", expected '" + expectedNum + "', found '" + foundNum + "' at unknown line";
	}
}