//EXT:!CNE

class Matrix {
	public static void main(String[] args) {
		System.out.println(new int[10][5]);
		System.out.println(new Array().Array().array()[5] != new Array().Array().array()[5]);
		
	}

}

class Array {
	int[] array;
	public Array Array() {
		return new (((Array()).Array()).Array()).Array().Array().Array();
	}
	
	public int[] array() {
		array = new int[10];
		return array;
	}
}
