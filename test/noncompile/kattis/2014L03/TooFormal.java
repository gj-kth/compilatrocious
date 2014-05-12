class Main
{
	public static void main(String[] argu) 
	{
		int a;
		int[] b;
		Param c;
		
		a = 1;
		b = new int[3];
		c = new Param();
		
		a = c.param(a, a, 2);
		
		System.out.println(a);
	}
}

class Param {
	public int param(int a, int b) {
		return a + b;
	}
}

