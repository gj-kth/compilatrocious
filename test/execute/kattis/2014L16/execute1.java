class execute1 {

	public static void main(String[] args) {

		Execute s;
		int asdasdadasd;
		int loop;
		int[] k;
		int test;		
		k = new int[2];

		asdasdadasd=2;
		
		s = new Execute();
		
		test = s.seta( asdasdadasd );


		test = s.geta();
		System.out.println( test );
		

		
		loop = 10;
		while( test < loop )
		{
			if(test < asdasdadasd){
				loop = s.seta( loop );
				test = s.getb();
				System.out.println(test);
			}else{
				loop = s.seta( 66 );
				test = s.getb();
				System.out.println(2);
			}
		}
		System.out.println( loop );
		
	}

}

class woop{
	int c;
	int d;
	
	public int setd(int s){
		d=8;
		return s;
	}
	public int getd(){
		return d;
	}
}

class Execute{
	int a;
	woop w;
	public int getb(){
		w = new woop();
		a = w.setd( a );
		a = w.getd();
		return a;
	}
	public int seta(int b){
		a=b;
		return 0;
	}
	public int geta(){
		return a;
	}
}