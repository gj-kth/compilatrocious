package mjc.parser;
import java.util.*;

public abstract class TypecheckError extends Error{
	public TypecheckError(String s){
		super(s);
	}
}