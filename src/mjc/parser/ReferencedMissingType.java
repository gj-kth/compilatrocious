package mjc.parser;

import java.util.*;
import mjc.parser.VisitorUtil.Pair;
import mjc.parser.VisitorUtil.ClassData;
import mjc.parser.VisitorUtil.MethodData;
import mjc.parser.VisitorUtil.Scope;


public class ReferencedMissingType extends Error{

	public ReferencedMissingType(Scope scope, String typeName, SimpleNode node){
		super("Scope: " + scope + ", typeName: " + typeName + " at line "
                        + ((Token)node.value).beginLine + " column " + ((Token)node.value).beginColumn);
	}
}
