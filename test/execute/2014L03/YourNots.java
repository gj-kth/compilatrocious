class Main
{
	public static void main(String[] s) 
	{
		boolean a;
		int b;
		
		a = false;
		a = !a;
		a = !!a;
		a = !!!a;
		a = !!!!a;
		b = 123;
		
		if (!a) {
			System.out.println(b);
		}
	}
}

