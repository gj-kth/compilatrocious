class FullTest {
	public static void main(String[] args) {
		int a;
		boolean b;
		boolean c;
		int[] arr;
		
		a = 3;
		b = true;
		a = 17 + a * a + 18 + a - (3 - 5 * 3);
		c = a < a;
		c = a < a && c && true && false;
		c = !!!(a < a && c && true && false);
		
		arr = new int[(((333)+3)) + new FT1().sak(c)];
		arr[new FT1().sak(c) - 9] = 3;
		
		a = 1;
		while(a < 5) {
			System.out.println(a + arr.length);
			a = a + 1;
		}
	}
}

class FT1 {
	int a;
	boolean b;
	int d;
	public int sak(boolean c) {
		int res;
		a = 1;
		b = true;
		d = 3;
		a = a;
		b = b;
		d = d;
		if (d < d && c)
			res = 17;
		else
			res = 18;
		return res;
	}
}
