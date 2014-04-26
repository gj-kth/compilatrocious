// EXT:ISC
class TestExtendsVarAssign {
	public static void main(String[] args) {
	}
}

class A extends B {
	public int test(int x) {
		A a;
		B b;
		C c;
		a = new A();

		b = new B();
		b = new A();

		c = new C();
		c = new B();
		c = new A();

		return 0;
	}
}

class B extends C {
	int b;
}

class C {
	int c;
}