package mjc.parser;

import java.util.*;
import mjc.parser.VisitorUtil.Pair;
import mjc.parser.VisitorUtil.ClassData;
import mjc.parser.VisitorUtil.MethodData;
import mjc.parser.VisitorUtil.Context;


public class ReferencedMissingMethod extends Error{
	public ReferencedMissingMethod(Context scope, String typeName){
		super("Context: " + scope + ", methodName: " + typeName);
	}
}