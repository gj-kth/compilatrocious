// EXT:ISC

class Main
{
	public static void main(String[] s) 
	{
		SomeClass a;
		a = new SomeClass();
	}
}

class SomeClass extends YetSomeOtherClass {
	public int someMethod() {
		int abc;
		abc = 1;
		return abc;
	}
}

class SomeOtherClass extends SomeClass {
	public int someOtherMethod() {
		int abc;
		abc = 1;
		return abc;
	}
}

class YetSomeOtherClass extends SomeOtherClass {
	public int yetSomeOtherMethod() {
		int abc;
		abc = 1;
		return abc;
	}
}

