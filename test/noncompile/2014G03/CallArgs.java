class Call {
    public static void main(String[] args) {
		int x;
		Foo foo;

		x = foo.f(0, 0);
    }
}

class Foo {
    public int f(int a, boolean b) {
		return 0;
    }
}
