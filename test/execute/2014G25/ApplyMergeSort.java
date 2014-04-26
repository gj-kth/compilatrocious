
/*
 * This test tests the base language.
 */
class ApplyMergeSort {

	public static void main(String[] args) {
		MergeSort ms;
		int[] list;
		int i;
		ms = new MergeSort();
		list = new int[15];
		list[0] = 7;
		list[1] = 6;
		list[2] = 5;
		list[3] = 4;
		list[4] = 8;
		list[5] = 9;
		list[6] = 10;
		list[7] = 15;
		list[8] = 14;
		list[9] = 2313;
		list[10] = 235423534;
		list[11] = 0-6;
		list[12] = 0-123123;
		list[13] = 0;
		list[14] = 7;
		
		list = ms.mergeSort(list, 0, 15);
		
		i = 0;
		while (i < 15) {
			System.out.println(list[i]);			
			i = i + 1;
		}

	}
}

class MergeSort {

	/**
	 * Sorts the numbers in list in increasing order. start and stop are indexes
	 * of first and last elements where stop is exclusive.
	 * 
	 * @param list: The list to be sorted.
	 * @param start: The index of the first element in the list to be sorted.
	 * @param stop: Stop - 1 is the index of the last element in the list to be sorted.
	 * 
	 * @return: A new list array containing the sorted numbers in list[start, stop).
	 * 
	 */
	public int[] mergeSort(int[] list, int start, int stop) {
		int[] result;			//Result array.
		int middle;				//Middle index.
		int[] firstHalf;		//First recursive result.
		int firstHalfLength;	//Length of the first recursive result.
		int[] secondHalf;		//Second recursive result.
		int secondHalfLength;	//Length of the second recursive result.
		int resultPointer;		//Pointer to result array for next item to insert.
		int firstHalfPointer;	//Pointer to first half array for next item to insert from it.
		int secondHalfPointer;	//Pointer to second half array for next item to insert from it.
		
		//Base cases when the list is of length 1 or 2.
		//!(start + 1 < stop) && !(stop < start + 1) <--> stop - start == 1

		//If the list has length 1.
		if (!(start + 1 < stop) && !(stop < start + 1)) {
			result = new int[1];
			result[0] = list[start];
		}
		//If the list has length at least 2.
		else {
			//If the list has length 2.
			if (!(start + 2 < stop) && !(stop < start + 2)) {
				result = new int[2];
				if (list[start] < list[stop-1]) {
					result[0] = list[start];
					result[1] = list[stop-1];
				} else {
					result[0] = list[stop-1];
					result[1] = list[start];				
				}
			}
			//If the list has length greater than 2.
			else {
				result = new int[stop - start];
				middle = start + this.divideBy2(stop - start);
				firstHalf = this.mergeSort(list, start, middle);
				firstHalfLength = middle - start;
				secondHalf = this.mergeSort(list, middle, stop);
				secondHalfLength = stop - middle;
				
				resultPointer = 0;
				firstHalfPointer = 0;
				secondHalfPointer = 0;
				
				//While both pointers to their respective sublists are within range.
				while (firstHalfPointer < firstHalfLength && secondHalfPointer < secondHalfLength) {
					if (firstHalf[firstHalfPointer] < secondHalf[secondHalfPointer]) {
						result[resultPointer] = firstHalf[firstHalfPointer];
						firstHalfPointer = firstHalfPointer + 1;
					} else {
						result[resultPointer] = secondHalf[secondHalfPointer];
						secondHalfPointer = secondHalfPointer + 1;
					}					
					resultPointer = resultPointer + 1;
				}
				
				//If the first recursive call is not completely inserted.
				while (firstHalfPointer < firstHalfLength) {
					result[resultPointer] = firstHalf[firstHalfPointer];
					firstHalfPointer = firstHalfPointer + 1;
					resultPointer = resultPointer + 1;
				}
				
				//If the second recursive call is not completely inserted.
				while (secondHalfPointer < secondHalfLength) {
					result[resultPointer] = secondHalf[secondHalfPointer];
					secondHalfPointer = secondHalfPointer + 1;
					resultPointer = resultPointer + 1;					
				}
			}
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param a must be a non-negative integer.
	 * @return a divided by 2 where integer division is performed. 
	 */
	public int divideBy2(int a) {
		int x;
		
		x = 0;		
		while (!this.isEqual(2*x, a) && !this.isEqual(2*x + 1, a))
			x = x + 1;
		
		return x;
	}
	
	public boolean isEqual(int x, int a) {
		return !(x < a) && !(a < x);
	}

}
