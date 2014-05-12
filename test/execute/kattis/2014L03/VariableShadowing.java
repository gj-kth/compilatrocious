class Main
{
	public static void main(String[] argu) 
	{
		int a;
		boolean var;
		VariableShadowing b;
		
		var = true;
		b = new VariableShadowing();
		a = b.m1();
		System.out.println(a);
	}
}

class VariableShadowing {
	
	VariableShadowing var;

	public int m1()
	{
		int var;
		
		var = 123;
		return var;
	}
}

