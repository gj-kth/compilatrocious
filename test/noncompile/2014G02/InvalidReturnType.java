class IAmNotABogusClass {
	public static void main(String[] args) {
		int a;
		int b;
		int c;

		a = 4711;
		b = 2;
		c = 1 + b;

		if (c < 1010) {
			b = b + 1000;
		} else {
			b = a + c + b - 1000;
			c = 10;
		}

		System.out.println(b + c);
	}
}

class NeitherAmI {
	public int printInteger(int param) {
		int ret;
		
		if (0 < param && param < 1000) {
			System.out.println(param);
			ret = param + 1;
		} else {
			System.out.println(param);
			ret = param - 1;
		}

		return ret;
	}

	public int weirdFunction(int param) {
		int ret;
		if (0 < param) {
			ret = this.weirdFunction(0);
		} else {
			ret = 47;
			System.out.println(11);
		}
		return ret;
	}
}

/**
 * Hint: This class contains the bogus stuff
 */
class IAmTheBogusClass {
	
	public boolean NotMe(boolean b1, boolean b2) {
		if (b1 && b2) {
			System.out.println(1);
		} else {
			System.out.println(2);
		}
		return true;
	}

	public boolean ItWasMeAllAlong(int a) {
		int gross;
		if (a < 0) {
			gross = 10000000; // super gross
		} else {
			gross = 1000; // not very gross
		}
		return gross; // InvalidReturnType.java:70: Incompatible types: int and boolean
	}
}