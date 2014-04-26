class good5 {
    public static void main(String[] args){
    	Fib fc;
    	int a;
    	int max;
    	max = 10;
    	fc = new Fib();
    	a = fc.run(max);
    	System.out.println(a);
    }
}

class Fib{
	public int run(int max){
		int a;
		a=this.fibb(max);
		return a;
	}
	public int fibb(int max){
		int currentfibb;
		int lastfibb;
		int newfibb;
		lastfibb = 1;
		currentfibb = 1;
		System.out.println(currentfibb);
		while(currentfibb< max){
			System.out.println(currentfibb);
			newfibb = this.nextfibb(currentfibb, lastfibb);
			lastfibb = currentfibb;
			currentfibb = newfibb;
		}
		return currentfibb;
	}
	public int nextfibb(int currentfibb, int lastfibb){
		int nextfibb;
		nextfibb = currentfibb + lastfibb;
		return nextfibb;
	}
}
