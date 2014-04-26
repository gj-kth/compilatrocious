/*

Test where an argument/local variable shadows a field

*/

class NameShadowing1 {
	public static void main(String[] args) {
		Prog p;
		int dummy;
		p = new Prog();
		dummy = p.setArgs(17);
		dummy = p.run1(18);
		dummy = p.run2(true);
		dummy = p.run3(19);
	}
}

class Prog {
	int args;
	public int setArgs(int v) {
		args = v;
		return v;
	}
	
	public int run1(int args) {
		System.out.println(args); // 18
		return 0;
	}
	
	public int run2(boolean a) {
		System.out.println(args); // 17
		return 0;
	}
	
	public int run3(int a) {
		int args;
		args = a;
		System.out.println(args); // 19;
		return 0;
	}
}