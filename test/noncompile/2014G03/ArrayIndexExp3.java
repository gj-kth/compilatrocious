class Array {
    public static void main(String[] args) {
		int[] x;
		int y;

		x = new int[10];
		y = x[false+1]; // should fail
    }
}
