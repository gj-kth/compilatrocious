// EXT:ISC
class TestExtendsMethodScope {
	public static void main(String[] args) {
	}
}

class A extends B {
	public int a_method1(int x) {
		A a;
		int r1;
		int r2;
		a = new A();
		r1 = a.bmethod1(x);
		r2 = a.cmethod1(x);
		r2 = this.cmethod1(x);
		return a.bmethod1(x) + this.cmethod1(x);
	}
}

class B extends C {
	public int bmethod1(int a) {
		return this.cmethod1(a) + this.cmethod2(a);
	}
}

class C {
	public int cmethod1(int a) {
		return 0;
	}

	public int cmethod2(int a) {
		return 1;
	}
}