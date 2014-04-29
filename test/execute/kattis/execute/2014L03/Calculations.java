class Main
{
	public static void main(String[] s) 
	{
		Factorial a;
		Sum c;
		Divide d;
		int b;
		
		a = new Factorial();
		b = a.compute(5);
		
		c = new Sum();
		b = c.compute(4);
		
		d = new Divide();
		b = d.divide(1353, 11);
		
		System.out.println(b);
	}
}

class Factorial {
	public int compute(int i) {
		int fac;
		fac = 1;
		while (0 < i) {
			fac = fac * i;
			i = i - 1;
		}
		return fac;
	}
}

class Sum {
	public int compute(int i) {
		int a;
		a = 0;
		
		while (0 < i) {
			a = a + i;
			i = i - 1;
		}
		return a;
	}
}

// www growingwiththeweb com 2013 06 algorithm integer division without html

class Divide {
	public int divide(int a, int b) {
		
		int sign;
		int result;
		
		sign = 1;
		
		if (a < 0) {
			a = 0 - a;
			sign = 0 - sign;
		} else {
			a = a + 0;
		}
		
		if (b < 0) {
			b = 0 - b;
			sign = 0 - sign;
		} else {
			b = b + 0;
		}

		result = 0;
		while (0 < (a + 1)) {
			a = a - b;
			result = result + 1;
		}
		return (result - 1) * sign;
	}
}

