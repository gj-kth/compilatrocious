// EXT:CLE
// EXT:CEQ

/// Test case for execute - testing if number is prime.
///
/// @version 31-03-2014

// "I am Optimus Prime and I approve of this method."
class OptimusPrimeChecker{

	public static void main(String[] args){
		PrimeChecker c;
		int nr;
		c = new PrimeChecker();
		
		// Should be true (prime)
		nr = 13;
		System.out.println(nr);
		System.out.println(c.isPrime(nr));
		
		// Should be false (not prime)
		nr = 50505602;
		System.out.println(nr);
		System.out.println(c.isPrime(nr));
		
		// Should be true (prime)
		nr = 9973;
		System.out.println(nr);
		System.out.println(c.isPrime(nr));
		
		// Should be false (not prime prime)
		nr = 78337868;
		System.out.println(nr);
		System.out.println(c.isPrime(nr));
	}
}

class PrimeChecker{
	/**
	 *  Checks if input integer is prime.
	 *  param i: integer to check
	 */
	public boolean isPrime(int i){
		boolean done;
		boolean res;
		int localTemp;
		int currFac;
		
		currFac = 2;
		done = false;
		res = true;
		
		// Go through all possible integer factors
		while(!done && currFac < i){
			localTemp = currFac;
			// Try if we can reach the input with currFac strides
			// (i.e. input contains factor currFac)			
			while(!done && localTemp <= i){
				if(localTemp == i){
					done = true;
					res = false;
				}
				else{}
				localTemp = localTemp+currFac;				
			}
			currFac = currFac + 1;
		}
		return res;
	}
}
