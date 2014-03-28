package mjc.parser;

import java.util.*;
import mjc.parser.VisitorUtil.Pair;
import mjc.parser.VisitorUtil.ClassData;
import mjc.parser.VisitorUtil.MethodData;
import mjc.parser.VisitorUtil.Scope;


public class WrongType extends Error{
	public WrongType(Scope scope, String expectedType, String foundType){
		super("Scope: " + scope + ", expected '" + expectedType + "', found '" + foundType + "'");
	}
}