// EXT:LONG
class testdupfielddef {
	public static void main(String[] args) {
	}
}
class Test1 {
	int c;
	int b;
	public int test1(int a) {
		return a;
	}
	public long test2(long a) {
		return a;
	}
}
class Test2 {
	int a;
	int a;
	public int test1(int a) {
		return a;
	}
	public long test2(long a) {
		return a;
	}
}
