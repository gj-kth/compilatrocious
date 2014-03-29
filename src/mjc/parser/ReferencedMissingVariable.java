package mjc.parser;

import java.util.*;
import mjc.parser.VisitorUtil.Pair;
import mjc.parser.VisitorUtil.ClassData;
import mjc.parser.VisitorUtil.MethodData;
import mjc.parser.VisitorUtil.Context;


public class ReferencedMissingVariable extends Error{

	public ReferencedMissingVariable(Context scope, SimpleNode node){
		super(str(scope, node));
	}

	private static String str(Context scope, SimpleNode node){
		if(node.value != null){
			return "Context: " + scope + " at line "
                        + ((Token)node.value).beginLine + " column " + ((Token)node.value).beginColumn;
		}
		return "Context: " + scope;
	}
}
