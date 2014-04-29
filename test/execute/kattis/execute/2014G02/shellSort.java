class shellSort {
	public static void main(String[] args) {
		// Test shellSort here

		int[] testArray1;
		int[] out;
		int size1;
		int i;

		Sorter sorter;

		size1 = 10;

		testArray1 = new int[size1];

		testArray1[0] = 23;
		testArray1[1] = 10;
		testArray1[2] = 5;
		testArray1[3] = 25;
		testArray1[4] = 50;
		testArray1[5] = 709;
		testArray1[6] = 60;
		testArray1[7] = 5;
		testArray1[8] = 1;
		testArray1[9] = 222;

		sorter = new Sorter();

		out = sorter.sort(testArray1);
		i = 0;
		while (i < out.length) {
			System.out.println(out[i]);
			i = i + 1;
		}
	}
}

class Sorter {

	/**
	 * This sort method is an implementation of the shell sort algorithm A not so
	 * efficient sort algorithm for large list of numbers but still efficient
	 * enough for somewhat medium sized lists (note that it all depends on the
	 * choice of the gap)
	 * 
	 * See {@link http://en.wikipedia.org/wiki/Shellsort}.
	 * 
	 * @param list
	 * @return
	 */
	public int[] sort(int[] list) {
		int step;
		int gap;
		int i;
		int j;
		int tmp;
		int k;
		
		step = 3;
		gap = 1;
		while (gap < list.length) {
			gap = step * gap + 1;
		}

		while (0 < gap) {

			i = 0;
			while (i < gap) {
				j = gap + i;
				while(j < list.length) {
					tmp = list[j];
					k = j - gap;
					
					while (0 < (k + 1) && tmp < list[k]) {
						list[k + gap] = list[k];
						k = k - gap;
					}
					
					list[k + gap] = tmp;
					j = j + gap;
				}
				i = i + 1;
			}

			gap = new Div().divide(gap - 1, step);
		}
		return list;
	}
}

class Div {
	public int divide(int a, int b) {
		int div;
		div = 0;
		
		while (b < a+1) {
			a = a - b;
			div = div + 1;
		
		}
		return div;
	}
}