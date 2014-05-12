class Call {
    public static void main(String[] args) {
		int x;
		Foo foo;

		x = foo.f(0, true, new int[true]);
    }
}

class Foo {
    public int f(int a, boolean b, int[] c) {
		return 0;
    }
}
