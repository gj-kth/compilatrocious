/**
 * This is valid Java, but not valid minijava.
 */
class S1 {
	public static void main(String[] argv){
		Foo f;
		f = new Foo();
		System.out.println(f.bar(2,3) + f.bar(2,3,4));
	}
}

class Foo {
	public int bar(int a, int b){
		return a+b;
	}
	public int bar(int a, int b, int c){
		return a*b-c;
	}
}

