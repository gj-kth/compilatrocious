package mjc.parser;

import java.util.*;
import mjc.parser.VisitorUtil.Pair;
import mjc.parser.VisitorUtil.ClassData;
import mjc.parser.VisitorUtil.MethodData;
import mjc.parser.VisitorUtil.Scope;


public class ReferencedMissingMethod extends Error{
	public ReferencedMissingMethod(Scope scope, String typeName){
		super("Scope: " + scope + ", methodName: " + typeName);
	}
}