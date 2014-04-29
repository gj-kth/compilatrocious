class ArrayLength {
	public static void main(String[] args){
		class1 c1;
		int i;
		c1 = new class1();
		i = c1.arrayAlloc(new int[56]).length;
	}
}

class class1 {
	int i;

	public int[] arrayAlloc(int[] i) {
		return i;
	}
}
