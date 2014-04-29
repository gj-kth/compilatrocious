// EXT:ISC
class Main {
	
	public static void main(String[] args){
		Test test;
		Base base;
		boolean result;
		test = new Test();
		base = new Extends(); // ok
		result = test.testParameters(base); // not ok
	}

}

class Test {

	public boolean testParameters(Extends e){
		return true;
	}

}


class Base {



}

class Extends extends Base {

}



