class Main {
	public static void main(String[] args) {
		Test f;
		int a;
		// f should not work now f = new Test();
		a = f.getNum(true, 3);
	}
}

class Test {
	public int getNum(boolean p, int q) {
		return 7;
	}
}
