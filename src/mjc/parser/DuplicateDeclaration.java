package mjc.parser;

public class DuplicateDeclaration extends TypecheckError{
	public DuplicateDeclaration(Symbol s, Object o, Object prev){
		super("Already defined symbol: '" + s + "' was declared as " + o + ", but was previously mapped to " + prev.toString().replace("\n",""));
	}
}
