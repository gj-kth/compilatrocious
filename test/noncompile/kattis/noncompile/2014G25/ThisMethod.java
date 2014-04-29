//EXT:CNE
//EXT:CEQ
//EXT:CGT

class ThisMethod {
	public static void main(String[] s) {
		int args;
		MainMethod a;
		MainMethod b;
		b = new ThisMethod();
		if(new ByPasser().manage() == this) System.out.println(!false);
		else if(new ByPasser().manage() == b) System.out.println(!!false);
		else System.out.println(1-2);
	}
}

class ByPasser{
	ThisMethod a;
	ThisMethod This;
	public ThisMethod manage() {
		int[] t;
		a = new ThisMethod();
		This = a;
		t = new int[10];
		if((a == This) != (t.length > 5)) {
			
		}
		else {
			System.out.println(a.main(t));
		}
		if(This != this) This = new ThisMethod();
		else This = a;
		return a;
	}
}
