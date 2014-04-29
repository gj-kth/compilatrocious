/*

Test that re-uses argument names

*/

class Arg1 {
	public static void main(String[] args) {
		int i;
		Prog p;
		p = new Prog();
		i = p.run1(1, 2, 3);
		i = p.run2(true, true, false);
	}
}

class Prog {
	public int run1(int args, int a2, int a3) {
		System.out.println(args);
		System.out.println(a2);
		System.out.println(a3);
		return 0;
	}
	
	public int run2(boolean args, boolean a2, boolean a3) {
		System.out.println(args);
		System.out.println(a2);
		System.out.println(a3);
		return 0;
	}
}
