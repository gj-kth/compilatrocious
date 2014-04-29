// EXT:CNE
// EXT:CEQ

/// Test case for execute - will output the Fibonacci sequence for some integers (one element per line...).
///
/// @version 31-03-2014

class FibonacciParty{

	public static void main(String[] args){
		boolean temp;
		Fibonacci fib;
		fib = new Fibonacci();
		temp = fib.init(41);
		
		// Output (fast version)
		System.out.println(true);
		System.out.println(fib.fibonacci(0,true));  // 0
		System.out.println(fib.fibonacci(1,true));  // 1
		System.out.println(fib.fibonacci(2,true));  // 1
		System.out.println(fib.fibonacci(20,true)); // 6765
		//System.out.println(fib.fibonacci(40,true)); // 102334155
		
		// Output (slow version)
		System.out.println(false);
		System.out.println(fib.fibonacci(0,false));  // 0
		System.out.println(fib.fibonacci(1,false));  // 1
		System.out.println(fib.fibonacci(2,false));  // 1
		System.out.println(fib.fibonacci(20,false)); // 6765
		//System.out.println(fib.fibonacci(40,false)); // 102334155
	}
}

class Fibonacci{
	int[] arr;
	
	/**
	 * Initializes the array used for storing part-results when calculating Fibonacci sequence.
	 * This will yield a much better performance.
	 * @param max Nr of elements in the largest Fibonacci sequence.
	 */
	public boolean init(int max){
		int counter;
		counter = 0;
		arr = new int[max];
		// Init. elements with -1 (fib values are always positive)
		while(counter < max){
			arr[counter] = 0-1;
			counter = counter + 1;
		}
		return true;
	}
	
	/**
	 * Returns the Fibonacci value for a given number.
	 * @param num		Number to compute Fibonacci value for
	 * @param use_fast	Whether we should use pre-storing of values (yielding MUCH better performance)
	 */
	public int fibonacci(int num, boolean use_fast){
		int res;
		if(use_fast){
			res = this.fast_fibonacci(num);
		}
		else{
			res = this.slow_as_fibonacci(num);
		}
		return res;
	}
	
	/**
	 * Returns the Fibonacci integer for a given index.
	 * Fast version.
	 */
	public int fast_fibonacci(int n){
		int res;
		
		// Check if part-result has already been calculated
		if(arr[n] != (0-1)){
			res = arr[n];
		}
		else if(n == 0){
			res = 0;
			arr[n] = res;
		}
		else if(n == 1){
			res = 1;
			arr[n] = res;
		}
		else{
			res = this.fast_fibonacci(n-2) + this.fast_fibonacci(n-1);
			arr[n] = res;
		}

		return res;
	}
	
	/**
	 * Returns the Fibonacci integer for a given index.
	 * Slow version.
	 */
	public int slow_as_fibonacci(int n){
		int res;
		
		if(n == 0){
			res = 0;
		}
		else if(n == 1){
			res = 1;
		}
		else{
			res = this.slow_as_fibonacci(n-2) + this.slow_as_fibonacci(n-1);
		}

		return res;
	}
}
