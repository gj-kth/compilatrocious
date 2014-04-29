class PrimeSieve {
        public static void main(String[] args) {
		int t;
		Prime sieve;
		sieve = new Prime();
		t = sieve.sieve(9973);
		System.out.println(t);
		if(sieve.isPrime(99) < 1) {
			System.out.println(sieve.getFactor(99));
		} else {
			System.out.println(0-1);
		}
		System.out.println(sieve.isPrime(2));
		System.out.println(sieve.isPrime(3));
		System.out.println(sieve.isPrime(4));
		System.out.println(sieve.isPrime(9972));
		System.out.println(sieve.isPrime(9973));
        }
}

class Prime {
	int[] arr;

	public int sieve(int n)  {
		int jump;
		int val;
		int counter;

		arr = new int[n+1];
		jump = 0;
		counter = 0;
		while(jump < n+1) {
			arr[jump] = 0;
			jump = jump + 1;
		}
		jump = 2;
		while(jump < n+1) {
			val = jump;
			if(0 < arr[jump]) {	
				jump = jump + 1;
			} else {
				counter = counter + 1;
				arr[val] = 0-1;
				val = val + jump;
				while(val < n+1) {
					arr[val] = jump;
					val = val + jump;
				}
				jump = jump + 1;
			}
		}
		return counter;
        }

	public int isPrime(int n) {
		int ret;
		if(arr[n] < 0) {
			ret = 1;
		} else {
			ret = 0;
		}
		return ret;
	}

	public int getFactor(int n) {
		return arr[n];
	}
}
