package mjc.parser;

public class DuplicateDeclaration extends TypecheckError{
	public DuplicateDeclaration(Symbol s, Object o){
		super("Already defined symbol: '" + s + "', object: " + o);
	}
}