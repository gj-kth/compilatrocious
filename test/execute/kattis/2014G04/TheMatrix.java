// EXT:CGE

/// Test case for execute - prints a matrix (sadly with only one element per line...).
///
/// @version 31-03-2014

class TheMatrix{

	public static void main(String[] args){
		boolean temp;
		Matrix m;
		int[] arr;
		arr = new int[9];
		m = new Matrix();
		
		// 1 5 7
		// 5 2 1
		// 0 9 2
		arr[0] = 1;
		arr[1] = 5;
		arr[2] = 7;
		
		arr[3] = 5;
		arr[4] = 2;
		arr[5] = 1;
		
		arr[6] = 0;
		arr[7] = 9;
		arr[8] = 2;
		
		temp = m.init(arr, 3, 3);
		
		// Expected output
		// 1
		// 5
		// 7
		// 5
		// 2
		// 1
		// 0
		// 9
		// 2
		temp = m.printMatrix(); 
	}
}

class Matrix{
	int rLength;
	int cLength;
	int[] elements;
	
	/**
	 * Inits a matrix from concatenated rows.
	 * param 		arr: Concatenated rows
	 * param  rowLength: Nr of elements in one row
	 * param     nrRows: Nr of rows
	 */
	public boolean init(int[] arr, int rowLength, int nrRows){
		elements = arr;
		rLength = rowLength;
		cLength = nrRows;
		return true;
	}
	
	/**
	 * Prints the matrix, one element per row (I'm so sorry..).
	 */
	public boolean printMatrix(){
		int currR;
		int currC;
		boolean done;
		currR = 0;
		currC = 0;
		done = false;
		
		while(!done){
			System.out.println(elements[currC + currR*rLength]);
			
			currC = currC + 1;
			if(currC >= rLength){
				currC = 0;
				currR = currR + 1;
				if(currR >= cLength){
					done = true;
				}
				else{}
			}
			else{}
		}
		return true;
	}
}
