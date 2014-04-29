class Array {
    public static void main(String[] args) {
		int[] x;
		int y;

		x = new int[false]; // should fail
		y = x[0];
    }
}
