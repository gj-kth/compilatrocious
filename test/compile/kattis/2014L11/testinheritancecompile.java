// EXT:ISC
// EXT:LONG
// EXT:SPILL
// EXT:CLE
// EXT:CGT
// EXT:CGE
// EXT:CEQ
// EXT:CNE
class Main {
	public static void main(String[] s) {
		B b1;
		B b2;
		B b3;
		E e1;
		E e2;
		F f1;
		long l;
		b1 = new B();
		b2 = new E();
		b3 = new F();
		e1 = new E();
		e2 = new F();
		f1 = new F();
		l = b1.A();
		System.out.println(b1.A()*1);
		System.out.println(b1.B()+2);
		System.out.println(b1.C()-3);

		System.out.println(b2.A());
		System.out.println(b2.B());
		System.out.println(b2.C());
		
		System.out.println(b3.A());
		System.out.println(b3.B());
		System.out.println(b3.C());

		System.out.println(e1.A());
		System.out.println(e1.B());
		System.out.println(e1.C());
		System.out.println(e1.D());

		System.out.println(e2.A());
		System.out.println(e2.B());
		System.out.println(e2.C());
		System.out.println(e2.D());

		System.out.println(f1.A());
		System.out.println(f1.B());
		System.out.println(f1.C());
		System.out.println(f1.D());
		
		{
			long l1;
			long l2;
			l1 = 2l*3l+5l-6l*23*(4l-6);
			l2 = 0;
			if (l1 < l2 ) {} else {}
			if (l1 <= l2 ) {} else {}
			if (l1 > l2 ) {} else {}
			if (l1 >= l2 ) {} else {}
			if (l1 == l2 ) {} else {}
			if (l1 != l2 ) {} else {}
		}
	}
}
class B {
	public long A() {
		return 2l*2l;
	} // Virtual
	public long B() {
		return 1l*2;
	} // 
	public int C() {
		return 0;
	} // Virtual
}
class E extends B {
	public long A() {
		return 3l+2l;
	} // Virtual Override
	public int D() {
		return 1;
	} // Virtual
}
class F extends E {
	public long A() {
		return 4l-5l;
	} // Override
	public int C() {
		return 3;
	} // Override
	public int D() {
		return 3;
	} // Override
}
