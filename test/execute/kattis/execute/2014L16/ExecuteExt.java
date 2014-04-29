// EXT:CEQ
// EXT:CNE
// EXT:CLE
// EXT:CGT
// EXT:CGE
// EXT:IWE
// EXT:ICG
// EXT:ISC


class ExecuteExt {
	public static void main(String[] args) {
		Smaller s;
		Bigger b;
		int scrap;
		int ref;
		boolean t;
		ref=42;
		t=!false;
		scrap=0;
		s = new Smaller();
		
		b = new Bigger();

		System.out.println(b.getL());
		System.out.println(s.getL());
		t= b.mix(ref, t);
		System.out.println(s.getL());
		
		t=s.getFalse(scrap, ref);
		if(!t){
			System.out.println(t);
			if( scrap >= ref){
				System.out.println(t);
			}else{
				System.out.println(ref);
			}
		}
		
		if( t == true){
			if(t== !true ){
				System.out.println(true);
			}
		}
		
		if(!false && ref > scrap){
			if(ref<scrap ){
				System.out.println(t);
			}
			System.out.println(!t);
		}else{
			System.out.println(ref);
		}
		
		
	}
}

class Smaller{
	int l;
	public int getL(){
		return 2;
	}
	
	public boolean setL(int in){
		l=in;
		return true;
	}
	
	public boolean getFalse(int a, int b){
		if( a <= b ){
			System.out.println(a);
		}
		return false;
	}
}

class Bigger extends Smaller{
	int j;
	public int getJ(){
		return j;
	}
	public boolean setJ(int in){
		j=in;
		return true;
	}	
	
	public boolean mix(int ja, boolean b){
		l=ja;
		return b;
	}
}