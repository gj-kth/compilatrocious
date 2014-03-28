package mjc.parser;

import java.util.*;
import mjc.parser.VisitorUtil.Pair;
import mjc.parser.VisitorUtil.ClassData;
import mjc.parser.VisitorUtil.MethodData;
import mjc.parser.VisitorUtil.Scope;


public class ReferencedMissingType extends Error{
	public ReferencedMissingType(Scope scope, String typeName){
		super("Scope: " + scope + ", typeName: " + typeName);
	}
}