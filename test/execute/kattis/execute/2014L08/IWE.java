// EXT:IWE

class IWE {
	public static void main(String[] args) {
		int a;
		int b;
		int c;
		a = 1;
		b = 2;
		c = 3;
		if (a < b)
			if (b < c)
				System.out.println(1); // Prints 1
			else
				System.out.println(2);
		else
			System.out.println(3);
		if (a < b)
			if (b < c)
				System.out.println(4); // Prints 4
		if (b < a)
			if (b < c)
				System.out.println(5);
			else
				System.out.println(6);
		if (a < b)
			if (c < b)
				System.out.println(7);
			else
				System.out.println(8); // Prints 8
		System.out.println(9); // Prints 9
	}
}
