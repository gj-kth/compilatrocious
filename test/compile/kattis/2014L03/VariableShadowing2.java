class Main
{
	public static void main(String[] argu) 
	{
		int a;
		boolean var;
		VariableShadowing b;
		
		var = true;
		b = new VariableShadowing();
		a = b.VariableShadowing();
		System.out.println(a);
	}
}

class VariableShadowing {
	
	VariableShadowing var;

	public int VariableShadowing()
	{
		int var;
		
		var = 123;
		return var;
	}
}

