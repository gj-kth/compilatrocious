// EXT:BDJ

class Main {
	public static void main(String[] args) {
		Or or;

		or = new Or();

		System.out.println(or.a() || or.b());
	}
}

class Or {
	public boolean a() {
		System.out.println(1);
		return false;
	}

	public boolean b() {
		System.out.println(2);
		return false;
	}
}
