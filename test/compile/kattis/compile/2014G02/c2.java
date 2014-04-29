// EXT:CLE
// EXT:CGT
// EXT:CGE
// EXT:CEQ
// EXT:CNE
// EXT:BDJ
// EXT:IWE
// EXT:ISC
// EXT:ICG

class c2 { 
	public static void main(String[] args) {
		int a;
		int b;
		cmp2 c;

		c = new cmp2();
		a = 1;
		b = 2;

		if(c.lt(a,b)) {
			System.out.println(!c.lt(a,b));
		}

		if (c.gte(b,a)) {
			if (c.gte2(b,a)) {
				System.out.println(c.eq(a,b));
			}
		} else {
			System.out.println(c.lte2(a,b));
		}

	}
}

class cmp2 extends cmp {
	public boolean lt2(int a, int b) {
		return !this.gt(a,b) && !this.eq(a,b);
	}

	public boolean gt2(int a, int b) {
		return !this.lt(a,b) && !this.eq(a,b);
	}

	public boolean lte2(int a, int b) {
		return this.lt(a,b) || this.eq(a,b);
	}

	public boolean gte2(int a, int b) {
		return this.gt(a,b) || this.eq(a,b);
	}
}

class cmp {
	public boolean lt(int a, int b) {
		return a < b;
	}

	public boolean gt(int a, int b) {
		return a > b;
	}
	
	public boolean lte(int a, int b) {
		return a <= b;
	}

	public boolean gte(int a, int b) {
		return a >= b;
	}

	public boolean eq(int a, int b) {
		return a == b;
	}

	public boolean neq(int a, int b) {
		return a != b;
	}
}