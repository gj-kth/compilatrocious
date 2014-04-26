// EXT:ISC
// EXT:ICG
// EXT:SPILL
// EXT:LONG
// EXT:IWE
// EXT:NBD
// EXT:ABC
// EXT:CLE
// EXT:CGT
// EXT:CGE
// EXT:CEQ
// EXT:CNE
// EXT:BDJ
// EXT:INT32

class Main {
	public static void main(String[] s) {
		System.out.println((new ICG()).test());
		System.out.println((new LONG()).test());
		System.out.println((new IWE()).test());
		System.out.println((new NBD()).test());
		System.out.println((new COND()).test());
	}
}

class BASE {
	
}

class SUPER extends BASE {
	int a;
}
class COND {
	public boolean test() {
		int MIN;
		int MAX;
		long MINL;
		long MAXL;
		BASE b1;
		BASE b2;
		int err;
		err = 0;
		
		MAXL = 9223372036854775807l;
		MAX = 2147483647;
		MINL = 0-MAXL-1;
		MIN = 0-MAX-1;

		if (MAX <= MIN)
			err = err + 1;
		if (MAX >= MIN) {} else
			err = err + 1;
		if (MAX < MIN)
			err = err + 1;
		if (MAX > MIN)  {} else
			err = err + 1;
		if (MIN < MAX) {} else
			err = err + 1;
		if (MIN > MAX)  
			err = err + 1;
		if (MAX != MIN-1)
			err = err + 1;
		if (MAX == MIN)
			err = err + 1;

		if (MAXL <= MINL)
			err = err + 1;
		if (MAXL >= MINL) {} else
			err = err + 1;
		if (MAXL < MINL)
			err = err + 1;
		if (MAXL > MINL)  {} else
			err = err + 1;
		if (MINL < MAXL) {} else
			err = err + 1;
		if (MINL > MAXL)  
			err = err + 1;
		if (MAXL != MINL-1)
			err = err + 1;
		if (MAXL == MINL)
			err = err + 1;

		b1 = new BASE();
		b2 = new SUPER();
		if (b1 == b2)
			err = err + 1;
		b1 = new SUPER();
		if (b1 == b2)
			err = err + 1;
		b2 = b1;
		if (b1 != b2)
			err = err + 1;
		if (this == new COND()) {
			// Not sure what this is allowed to do.
		}
		return err == 0;
	}
}

class NBD {
	int a;
	public int a() {
		return a;
	}
	public boolean test() {
		int a;
		int err;
		err = 0;
		a = 1;
		if (this.a() == 1)
			err = err + 1;
		if (a != 1)
			err = err + 1;
		{
			int b;
			b = 3;
			{
				boolean c;
				c = true;
				if (b != 3 || !c)
					err = err + 1;
			}
			{
				long c;
				c = 3l;
				if (b != c)
					err = err + 1;
			}
		}
		if (a == this.a()+1) {
			int b;
		}
		return err == 0;
	}
}

class IWE {
	public boolean test() {
		int err;
		err = 0;
		if (true)
			if (true) {
				if (true)
					if (false)
						if (true)
							if (false) {
								
							}
							else
								err = 1 + err; 
						else
							err = 1 + err;
					else {}
			}
			else err = 1 + err;
		else err = 1 + err;
		if (false)
			err = 1 + err;
		{
			err = 1 + err - 1;
		}
		return err == 0;
	}
	
}

class LONG {
	public boolean test() {
		long l1;
		long l2;
		long l3;
		long l4;
		
		int err;
		err = 0;
		
		l1 = 1l;
		l2 = 9223372036854775807l;
		l3 = 0-l2-l1;
		l4 = 0-2147483647-1;
		if (l1 + l2 != l3)
			err = 1 + err;
		if (2147483647+l1 == l4)
			err = 1 + err;
		if (2147483647+1 != l4)
			err = 1 + err;
		if ((2147483647+1)*l1 != l4)
			err = 1 + err;
		if (2147483647+2147483647+2*l1 != 0)
			err = 1 + err;
		if (2147483647+2*l1+2147483647 == 0)
			err = 1 + err;
		return err == 0;	
	}
}

class ICG {
	public boolean test() {
		A a1;
		A a2;
		A a3;
		A a4;

		B b1;
		B b2;
		B b3;
		B b4;

		C c1;
		C c2;
		C c3;
		C c4;

		D d1;
		D d2;
		D d3;
		D d4;
		
		int err;
		err = 0;
		
		a1 = (new A()).init(1);
		a2 = (new B()).init(2);
		a3 = (new C()).init(3);
		a4 = (new D()).init(4);

		if (a1.a() != 1)
			err = err + 1;
		if (a1.b() != 0-1)
			err = err + 1;
		if (a1.Aa() != 1)
			err = err + 1;

		if (a2.a() != 0)
			err = err + 1;
		if (a2.b() != 2)
			err = err + 1;
		if (a2.Aa() != 0)
			err = err + 1;

		if (a3.a() != 3)
			err = err + 1;
		if (a3.b() != 0-1)
			err = err + 1;
		if (a3.Aa() != 0)
			err = err + 1;
		
		if (a4.a() != 0)
			err = err + 1;
		if (a4.b() != 4)
			err = err + 1;
		if (a4.Aa() != 0)
			err = err + 1;
		

		b1 = a1.B();
		b2 = a2.B();
		b3 = a3.B();
		b4 = a4.B();

		if (b1.a() != 0)
			err = err + 1;
		if (b1.b() != 0)
			err = err + 1;
		if (b1.Aa() != 0)
			err = err + 1;
		if (b1.Bb() != 0)
			err = err + 1;
		
		if (b2.a() != 0)
			err = err + 1;
		if (b2.b() != 2)
			err = err + 1;
		if (b2.Aa() != 0)
			err = err + 1;
		if (b2.Bb() != 2)
			err = err + 1;
		
		if (b3.a() != 0)
			err = err + 1;
		if (b3.b() != 0)
			err = err + 1;
		if (b3.Aa() != 0)
			err = err + 1;
		if (b3.Bb() != 0)
			err = err + 1;
		
		if (b4.a() != 0)
			err = err + 1;
		if (b4.b() != 4)
			err = err + 1;
		if (b4.Aa() != 0)
			err = err + 1;
		if (b4.Bb() != 0)
			err = err + 1;

		
		c1 = a1.C();
		c2 = a2.C();
		c3 = a3.C();
		c4 = a4.C();

		if (c1.a() != 0)
			err = err + 1;
		if (c1.b() != 0-1)
			err = err + 1;
		if (c1.Aa() != 0)
			err = err + 1;
		if (c1.Ca() != 0)
			err = err + 1;
		
		if (c2.a() != 0)
			err = err + 1;
		if (c2.b() != 0-1)
			err = err + 1;
		if (c2.Aa() != 0)
			err = err + 1;
		if (c2.Ca() != 0)
			err = err + 1;
		
		if (c3.a() != 3)
			err = err + 1;
		if (c3.b() != 0-1)
			err = err + 1;
		if (c3.Aa() != 0)
			err = err + 1;
		if (c3.Ca() != 3)
			err = err + 1;
		
		if (c4.a() != 0)
			err = err + 1;
		if (c4.b() != 0-1)
			err = err + 1;
		if (c4.Aa() != 0)
			err = err + 1;
		if (c4.Ca() != 0)
			err = err + 1;

		
		d1 = a1.D();
		d2 = a2.D();
		d3 = a3.D();
		d4 = a4.D();

		if (d1.a() != 0)
			err = err + 1;
		if (d1.b() != 0)
			err = err + 1;
		if (d1.Aa() != 0)
			err = err + 1;
		if (d1.Bb() != 0)
			err = err + 1;
		if (d1.Db() != 0)
			err = err + 1;
		
		if (d2.a() != 0)
			err = err + 1;
		if (d2.b() != 0)
			err = err + 1;
		if (d2.Aa() != 0)
			err = err + 1;
		if (d2.Bb() != 0)
			err = err + 1;
		if (d2.Db() != 0)
			err = err + 1;
		
		if (d3.a() != 0)
			err = err + 1;
		if (d3.b() != 0)
			err = err + 1;
		if (d3.Aa() != 0)
			err = err + 1;
		if (d3.Bb() != 0)
			err = err + 1;
		if (d3.Db() != 0)
			err = err + 1;
		
		if (d4.a() != 0)
			err = err + 1;
		if (d4.b() != 4)
			err = err + 1;
		if (d4.Aa() != 0)
			err = err + 1;
		if (d4.Bb() != 0)
			err = err + 1;
		if (d4.Db() != 4)
			err = err + 1;
		
		
		return err == 0;
	}
}

class A {
	int a;
	public int Aa() {
		return a;
	}
	public int a() {
		return a;
	}
	public int b() {
		return 0-1;
	}
	public A init(int n) {
		a = n;
		return this;
	}
	public A A() {
		return this;
	}
	public B B() {
		return new B();
	}
	public C C() {
		return new C();
	}
	public D D() {
		return new D();
	}
}
class B extends A {
	int b;
	public int Bb() {
		return b;
	}
	public int a() {
		return a;
	}
	public int b() {
		return b;
	}
	public B init(int n) {
		b = n;
		return this;
	}
	public A A() {
		return this;
	}
	public B B() {
		return this;
	}
	public C C() {
		return new C();
	}
	public D D() {
		return new D();
	}
}
class C extends A {
	int a;
	public int Ca() {
		return a;
	}
	public int a() {
		return a;
	}
	public int b() {
		return 0-1;
	}
	public C init(int n) {
		a = n;
		return this;
	}
	public A A() {
		return this;
	}
	public B B() {
		return new B();
	}
	public C C() {
		return this;
	}
	public D D() {
		return new D();
	}
}
class D extends B {
	int b;
	public int Db() {
		return b;
	}
	public int a() {
		return a;
	}
	public int b() {
		return b;
	}
	public D init(int n) {
		b = n;
		return this;
	}
	public A A() {
		return this;
	}
	public B B() {
		return this;
	}
	public C C() {
		return new C();
	}
	public D D() {
		return this;
	}
}