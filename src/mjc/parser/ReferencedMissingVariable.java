package mjc.parser;

import java.util.*;
import mjc.parser.VisitorUtil.Pair;
import mjc.parser.VisitorUtil.ClassData;
import mjc.parser.VisitorUtil.MethodData;
import mjc.parser.VisitorUtil.Scope;


public class ReferencedMissingVariable extends Error{
	public ReferencedMissingVariable(Scope scope){
		super("Scope: " + scope);
	}
}