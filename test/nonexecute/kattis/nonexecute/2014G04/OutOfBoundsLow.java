// EXT:ABC

/// Test case for non-execute performing an array out of bounds operation.
///
/// @version 31-03-2014
class OutOfBoundsLow{

	public static void main(String[] args){
		Torbjoern t;
		int result;
		t = new Torbjoern();
		result = t.torbjoern(10);
	}
}

class Torbjoern{
	public int torbjoern(int len){
		int[] arr;
		int counter;
		arr = new int[len];
		
		counter = 0 - 1;
		while(counter < len){
			arr[counter] = len + counter;
			counter = counter + 1;
		}
		
		return counter;
	}
}