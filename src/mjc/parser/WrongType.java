package mjc.parser;

import java.util.*;
import mjc.parser.VisitorUtil.Pair;
import mjc.parser.VisitorUtil.ClassData;
import mjc.parser.VisitorUtil.MethodData;
import mjc.parser.VisitorUtil.Context;


public class WrongType extends TypecheckError{

	public WrongType(Context scope, String expectedType, String foundType, SimpleNode node){
		super(str(scope, expectedType, foundType, node));
	}

	private static String str(Context scope, String expectedType, String foundType, SimpleNode node){
		if(node.value != null){
			return "Context: " + scope + ", expected '" + expectedType + "', found '" + foundType + "' at line "
                        + ((Token)node.value).beginLine + " column " + ((Token)node.value).beginColumn;
		}
		return "Context: " + scope + ", expected '" + expectedType + "', found '" + foundType + "' at unknown line";
	}
}
