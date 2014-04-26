// EXT:ISC
class TestExtendsParamAssign {
	public static void main(String[] args) {
	}
}

class A extends B {
	public C test(A a) {
		C c1;
		C c2;
		c1 = new B();
		c2 = this.testparam1(c1, new A(), a);
		c2 = this.testparam2(new B());

		return c2;
	}

	public A testparam1(C c, B b, A a) {
		return a;
	}

	public C testparam2(B b) {
		return b.testparam1(new A());
	}
}

class B extends C {
	public C testparam1(B a) {
		B b;
		b = new A();
		return b;
	}
}

class C {
	int c;
}