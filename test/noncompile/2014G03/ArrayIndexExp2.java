class Array {
    public static void main(String[] args) {
		int[] x;
		int y;

		x = new int[0];
		y = x[false]; // should fail
    }
}
