// EXT:BDJ
class TestOrBinding {
	public static void main(String[] args) {
		boolean a;
		boolean b;
		boolean c;
		boolean d;
		TestLaziness lazy;
		a = true;
		b = false;
		c = true;
		d = false;
		lazy = new TestLaziness();

		if(a || b)
			System.out.println(true);

		if(a && b || c && d)
			System.out.println(false);

		if((a && b) || (c && d))
			System.out.println(false);

		if(a || b && c || d)
			System.out.println(true);

		if(a && b && a || c && c && d)
			System.out.println(false);

		if(a || lazy.testLazy())
			System.out.println(true);
	}
}

class TestLaziness {
	public boolean testLazy() {
		System.out.println(false);
		return false;
	}
}
