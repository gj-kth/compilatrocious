// EXT:LONG
class Array {
    public static void main(String[] args) {
		int[] x;
		int y;

        x = new int[10];
        long i = 1; 
        x[1 + i] = 3; // should fail 
    }
}
