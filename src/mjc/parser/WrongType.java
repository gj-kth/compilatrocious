package mjc.parser;

import java.util.*;
import mjc.parser.VisitorUtil.Pair;
import mjc.parser.VisitorUtil.ClassData;
import mjc.parser.VisitorUtil.MethodData;
import mjc.parser.VisitorUtil.VariableScope;
import mjc.parser.VisitorUtil.VariableScopeWithName;


public class WrongType extends Error{
	public WrongType(VariableScope scope, String expectedType, String foundType, SimpleNode node){
		super("Scope: " + scope + ", expected '" + expectedType + "', found '" + foundType + "' at line "
                        + ((Token)node.value).beginLine + " column " + ((Token)node.value).beginColumn);
	}
}
