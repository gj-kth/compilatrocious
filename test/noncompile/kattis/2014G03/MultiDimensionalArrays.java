class Array {
    public static void main(String[] args) {
		int[] x;
		int y;

		x = new int[0];
		y = x[0];
		y = (new int[0])[0];
		y = new int[0][0]; // should fail
    }
}
