package mjc.parser;

import java.util.*;
import mjc.parser.VisitorUtil.Pair;
import mjc.parser.VisitorUtil.ClassData;
import mjc.parser.VisitorUtil.MethodData;
import mjc.parser.VisitorUtil.VariableScope;
import mjc.parser.VisitorUtil.VariableScopeWithName;


public class ReferencedMissingVariable extends Error{
	public ReferencedMissingVariable(VariableScopeWithName scope){
		super("Scope: " + scope);
	}
}