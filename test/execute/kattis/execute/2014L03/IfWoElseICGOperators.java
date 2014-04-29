// EXT:IWE
// EXT:ISC
// EXT:ICG
// EXT:BDJ
// EXT:CLE
// EXT:CGT
// EXT:CGE
// EXT:CEQ
// EXT:CNE

class Main
{
	public static void main(String[] argu) 
	{
		int a;
		int b;
		int c;
		boolean t;
		boolean f;
		Sub sub;
		
		a = 1;
		b = 2;
		c = 0 - 1;
		
		t = true;
		f = false;
		sub = new Sub();
		
		if ((t && !f || !f && t) && (b >= a) && b > a || a <= b && a != 2 && a == 1) {
			if (a <= b) {
				if (b != a) {
					if ( a == a) {
						if (b >= 1) {
							if (t && !!!f) {
								c = sub.sub1();
							}
						}
					}
				}
			}
		}
		
		System.out.println(c);
	}
}

class Super {
	
	int super1;
	int super2;

	public int super1(int i)
	{
		int var;
		
		var = 123;
		return var;
	}
}

class Sub extends Super {
	
	int sub1;
	int sub2;

	public int sub1()
	{
		int var;
		
		super1 = 1;
		sub1 = 2;
		super2 = super1 + sub1;
		
		var = this.super1(super2);
		return var;
	}
}

