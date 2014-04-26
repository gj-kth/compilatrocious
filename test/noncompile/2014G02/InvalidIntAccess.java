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
		if (1 < param) {
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

	public int ItWasMeAllAlong(int a) {
		int gross;
		int[] graus;
		if (a < 0) {
			gross = 10000000; // super gross
		} else {
			gross = 1000; // not very gross
		}
		graus[0] = gross;
		gross[0] = a; // InvalidIntAccess.java:72: The type of the expression must be an int array type but it resolved to int
		return gross;
	}
}