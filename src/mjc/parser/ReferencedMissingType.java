package mjc.parser;

import java.util.*;
import mjc.parser.VisitorUtil.Pair;
import mjc.parser.VisitorUtil.ClassData;
import mjc.parser.VisitorUtil.MethodData;
import mjc.parser.VisitorUtil.Context;


public class ReferencedMissingType extends TypecheckError{

	public ReferencedMissingType(Context scope, String typeName, SimpleNode node){
		super("Context: " + scope + ", typeName: " + typeName + " at line "
                        + ((Token)node.value).beginLine + " column " + ((Token)node.value).beginColumn);
	}

	private static String str(Context scope, String typeName, SimpleNode node){
		if(node.value != null){
			return "Context: " + scope + ", typeName: " + typeName + " at line "
                        + ((Token)node.value).beginLine + " column " + ((Token)node.value).beginColumn;
		}
		return "Context: " + scope + ", typeName: " + typeName + "' at unknown line";
	}
}
