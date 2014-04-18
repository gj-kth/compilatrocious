package mjc.parser;

public class CyclicInheritance extends TypecheckError{
	public CyclicInheritance(String className, String superClassName){
		super(className + " extends " + superClassName);
	}
}
