/// Testing arrays and print
///
/// @version 25-02-2014
class ArrayPrint{

	public static void main(String[] args){
		Torbjoern t;
		int result;
		t = new Torbjoern();
		result = t.Torbjoern();
	}
}

class Torbjoern{

	public int Torbjoern(){
		int[] x;
		int y;
		boolean result;
		Printer p;
		p = new Printer();
		y = 15;
		x = this.getArray(15);
		result = this.setArray(x);
		
		result = p.printArray(x);
		
		return y;
	}
	
	public int[] getArray(int size){
		return new int[size];
	}
	
	public boolean setArray(int[] arr){
		int counter;
		counter = 0;
		
		while(counter < arr.length){
			arr[counter] = counter;
			counter = counter + 1;
		}
		
		return true;
	}
}

class Printer{

	public boolean printArray(int[] arr){
		int counter;
		counter = 0;
		while(counter < arr.length){		
			System.out.println(arr[counter]);
			counter = counter + 1;
		}
		return true;
	}
}
