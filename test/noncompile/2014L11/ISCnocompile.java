// EXT:ISC
//
class Main {
	public static void main(String[] s) {
		
	}
}

class A {
	public B virt() {
		return new B();
	}
	public A virt2(A a) {
		return a;
	}
}

class B extends A {
	public A virt() {
		return new A();
	}
	public B virt2(B a) {
		return a;
	}
}

class C extends E {
	
}

class D extends C {
	
}
class E extends D {
	
}