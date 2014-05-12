
class Test2 {
	
	public static void main(String[] args){
		int a;
		int b;
		boolean c;
		Test2Additonal d;
		
		d = new Test2Additonal();
		a = 5;
		b = 7;
		c = d.sum(a,b);
		System.out.println(c);
	}
}

class Test2Additonal {
	
	public int sum(int a, int b){
		int c;
		c = a+b;
		return c;
	}
	
}

