
class good3 {
    public static void main(String[] args){
    	FirstClass fc;
    	int a;
    	fc = new FirstClass();
    	a = fc.returning();
    	System.out.println(a);
    }
}
class FirstClass{
	public int returning(){
		SecondClass sc;
		int a;
		sc = new SecondClass();
		a = sc.returning();
		return a;
	}
}

class SecondClass{
	public int returning(){
		ThirdClass tc;
		int a;
		tc = new ThirdClass();
		a = tc.returning();
		return a;
	}
}

class ThirdClass{
	public int returning(){
		return 1337;
	}
}