class many_arguments {
    public static void main(String[] args) {
	System.out.println(new Thing().run2(1, 2, 3, 4, 5, 6, 7, 8));
    }
}

class Thing {
    public int run(int a, int c, int d, int e, int f, int g, int h) {
	return a + c + d + e + f + g + h;
    }
    public int run2(int a, int c, int d, int e, int f, int g, int h, int i) {
	int a1;
	int a2;
	int a3;
	int a4;
	//int a5;
	int a6;
	int a7;
	int a8;
	int a9;
	int res;
	a1 = 1;
	a2 = 2;
	a3 = 3;
	a4 = 4;
	//a5 = 5;
	a6 = 6;
	a7 = 7;
	a8 = 8;
	a9 = 9;
	res = this.run(a1, a2, a3, a4, f, a6, a7);
	return h + a1 + a2 + a3 + a4 + a6 + a9 + res;
    }
}
