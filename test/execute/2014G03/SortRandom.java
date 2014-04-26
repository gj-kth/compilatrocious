// EXT:IWE
// EXT:NBD
// EXT:CLE
// EXT:CGT
// EXT:CGE
// EXT:CEQ
// EXT:CNE
// EXT:BDJ

class SortRandom {

	public static void main(String[] args) {
		int z;
		int[] list;
		MergeSort sorter;

		int SEED;				// initial seed value
		int INCR;				// increment
		int MOD;				// modulus
		int MULT;

		PseudoRandom x;

		Misc misc;

		list = new int[1000];//{14, 32, 67, 76, 23, 41, 58, 85};
		list[0] = 14;
		list[1] = 32;
		list[2] = 67;
		list[3] = 76;
		list[4] = 23;
		list[5] = 41;
		list[6] = 58;
		list[7] = 85;

		SEED = 1;					// initial seed value
		INCR = 3641;				// increment
		MOD = 8729;					// modulus
		MULT = 40;

		misc = new Misc().foo();
		x = new PseudoRandom();
		z = x.init(SEED, MULT, INCR, MOD);

		z = 8;
		while (z < list.length) {
			list[z] = x.next();
			z = z + 1;
		}

		//System.out.println("before: " + Arrays.toString(list));
		z = 0;
		z = misc.print(list, 100);

		System.out.println(0 - 1); // separator

		sorter = new MergeSort();
		z = sorter.init();
		z = sorter.mergeSort(list);

		//System.out.println("after:  " + Arrays.toString(list));
		z = misc.print(list, 10);

		System.out.println(0 - 1); // separator

		z = (misc.foo()).bar();
		System.out.println(z);
	}
}

/**
 Translated from:
 www.buildingjavaprograms.com/code-files/2ed/ch13/MergeSort.java
 **/
class MergeSort {

	Division divider;

	public int init() {
		divider = new Division();
		return 0;
	}

	// Places the elements of the given array into sorted order
	// using the merge sort algorithm.
	// post: array is in sorted (nondecreasing) order
	public int mergeSort(int[] array) {
		int z;
		if (array.length > 1) {
			// split array into two halves
			int[] left;
			int[] right;
			left = this.leftHalf(array);
			right = this.rightHalf(array);

			// recursively sort the two halves
			z = this.mergeSort(left);
			z = this.mergeSort(right);

			// merge the sorted halves into a sorted whole
			z = this.merge(array, left, right);
		}
		return 0;
	}

	// Returns the first half of the given array.
	public int[] leftHalf(int[] array) {
		int size1;
		int[] left;
		int i;
		size1 = divider.divide(array.length, 2);
		left = new int[size1];
		i = 0;
		while (i < size1) {
			left[i] = array[i];
			i = i + 1;
		}
		return left;
	}

	// Returns the second half of the given array.
	public int[] rightHalf(int[] array) {
		int size1;
		int size2;
		int[] right;
		int i;
		size1 = divider.divide(array.length, 2);
		size2 = array.length - size1;
		right = new int[size2];
		i = 0;
		while (i < size2) {
			right[i] = array[i + size1];
			i = i + 1;
		}
		return right;
	}

	// Merges the given left and right arrays into the given 
	// result array.  Second, working version.
	// pre : result is empty; left/right are sorted
	// post: result contains result of merging sorted lists;
	public int merge(int[] result,
			int[] left, int[] right) {
		int i1;   // index into left array
		int i2;   // index into right array
		int i;
		i1 = 0;
		i2 = 0;

		i = 0;

		while (i < result.length) {
			if (i2 >= right.length || (i1 < left.length
					&& left[ i1] <= right[i2])) {
				result[i] = left[i1];    // take from left
				i1 = i1 + 1;
			} else {
				result[i] = right[i2];   // take from right
				i2 = i2 + 1;
			}
			i = i + 1;
		}
		return 0;
	}
}

class Division {

	public int divide(int numerator, int denominator) {
		int i;
		i = 1;
		if (denominator != 0) {
			while (denominator * i <= numerator) {
				i = i + 1;
			}
		}
		return i - 1;
	}
}

/**
 Translated from:
 http://www.bryansimonson.com/2010/01/20/pseudorandom-number-generation-in-java/
 **/
class PseudoRandom {

	int seed;
	int multiplier;
	int increment;
	int modulus;
	Division divider;

	/**
	 * Constructor for PseudoRandom object.
	 * @param firstseed - The original seed value
	 * @param mult - Multiplier
	 * @param incr - Increment
	 * @param mod - Modulus value
	 */
	public int init(int firstseed, int mult, int incr, int mod) {
		divider = new Division();

		multiplier = mult;
		increment = incr;
		modulus = mod;
		seed = (mult * firstseed + incr) - mod * divider.divide((mult * firstseed + incr), mod);
		return 0;
	}

	/**
	 * Access the current seed value
	 * @return
	 * 	The current seed value
	 */
	public int current() {
		return seed;
	}

	/**
	 * Generates the next pseudorandom number iteration
	 * @return
	 * 	The next pseudorandom number iteration
	 * @postcondition
	 * 	The current seed value set to the same value that is returned 
	 */
	public int next() {
		seed = (multiplier * seed + increment) - modulus * divider.divide(multiplier * seed + increment, modulus);
		return seed;
	}

	/**
	 * Change the current seed value
	 * @param newseed
	 * @postcondition
	 * 	The current seed value is replaced by <code>newseed</code>
	 */
	public int changeSeed(int newseed) {
		seed = newseed;
		return 0;
	}
}

class Misc {

	int counter;
	Division divider;

	public Misc foo() {
		divider = new Division();
		counter = 0;
		return this;
	}

	public int bar() {
		boolean fail;
		boolean b;
		int i;

		fail = false;
		i = 0;

		b = this.astore() < 5 && false || this.astore() == 0 || this.astore() != 7;
		if (!(counter == 2)) {
			fail = true;
		}

		if (fail) {
			i = 0 - 5 - 13;
		}
		return i;
	}

	public int astore() {
		counter = counter - (0 - 1);
		return 0;
	}

	public int print(int[] list, int step) {
		int z;
		z = 0;
		while (z < list.length) {
			if (z - step * divider.divide(z, step) == 0) {
				System.out.println(list[z]);
			}
			z = z + 1;
		}
		return 0;
	}
}
