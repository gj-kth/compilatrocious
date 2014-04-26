//EXT:LONG
//EXT:CEQ
//EXT:CNE
//EXT:CLE
//EXT:CGT
//EXT:CGE
//EXT:BDJ
//EXT:INT32

public class TestB {
	
	public static void main(String[] args){
		
		int[] iarr;
		long[] larr;
		int a;
		int b;
		long c;
		long d;
		Sum s;
		
		
		iarr = new int[2];
		larr = new long[2];
		s = new Sum();
		
		iarr[0] = 3;
		larr[0] = 3;
		iarr[1] = 7;
		larr[1] = 7;
		
		a = iarr[0];
		b = a+iarr[1];
		c = larr[0];
		d = s.sum(a, iarr[1]);
		
		if(true && false || true){
			System.out.println(1);
		}
		else{
			System.out.println(0);
		}
		if(true || false && true || false){
			System.out.println(1);
		}
		else{
			System.out.println(0);
		}
		
		if(a == c && b == d){
			System.out.println(1);
		}
		else{
			System.out.println(0);
		}
		
		if(false != true){
			if(false && false != true){
				System.out.println(0);
			}
			else{
				System.out.println(1);
			}
		}else{
			System.out.println(0);
		}
		
		if(a == c && a <= c && a >= c){
			System.out.println(1);
		}
		else{
			System.out.println(0);
		}
		
		if(5 > 3){
			System.out.println(1);
		}
		else{
			System.out.println(0);
		}
		
		a = 2147483647;
		b = a +1;
		if(b == -2147483648){
			System.out.println(1);
		}
		else{
			System.out.println(0);
		}
		
		c = a;
		d = c+1;
		if(d == 2147483648l){
			System.out.println(1);
		}
		else{
			System.out.println(0);
		}
		
	}
}

class Sum {
	
	public Sum(){
	}
	
	public long sum(long a, long b){
		long c;
		c = a+b;
		return c;
	}
	
}

