class ArgumentNameReuse {
	public static void main(String[] args) {
	}
}

class ANR {
	public int thing(int a, int b) {
		int a;
		int b;
		return 0;
	}
}
