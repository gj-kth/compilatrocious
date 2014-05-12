// EXT:BDJ
// EXT:CEQ
// EXT:CNE

class Main {
	public static void main(String[] s) {
		System.out.println((new Pass0()).test());
		System.out.println((new Pass1()).test());
		System.out.println((new Pass2()).test());
	}
}
class Other {
	int b;
	public int b() {
		return b;
	}
	public boolean Pure() {
		return true;
	}
	public boolean NonPure() {
		b = b + 1;
		return false;
	}
}
class Pass0 {
	int a;
	Other o;
	public boolean test() {
		int i1;
		int i2;
		int i3;
		int i4;
		int err;
		err = 0;
		
		o = new Other();
		i1 = 0;
		i2 = 1;
		i3 = 1*2*3*4*5*6*7*8*9*10*11*12*13*14*15*16*17*18*19*20;
		i4 = 1+2+3+4+5+6+7+8+9+10+11+12+13+14+15+16+17+18+19+20;
		
		if (true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true || false  && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true) {} else
			err = err + 1;
		if (i1 < i2) {
			i3 = i3 - 4;
		} else {
			err = err + 1;
		}
		if (false && o.NonPure())
			err = err + 1;
		else {}
		if (o.b() != 0)
			err = err + 1;
		else {}
		if (o.NonPure() && false)
			err = err + 1;
		else {}
		if (o.b() != 1)
			err = err + 1;
		else {}
		if (true || o.NonPure()) {} else
			err = err + 1;
		if (o.Pure() || true) {} else 
			err = err + 1;
		if (!o.Pure() || o.NonPure())
			err = err + 1;
		else {}
		if (o.b() != 2)
			err = err + 1;
		else {}
			
		
		return err == 0;
	}
}
class Pass1 {
	int a;
	Other o;
	public boolean test() {
		int i1;
		int i2;
		int i3;
		int i4;
		int err;
		err = 0;
		
		o = new Other();
		i1 = 0;
		i2 = 1;
		i3 = 0-2102132736;
		i4 = 210;
		
		if (true) {} else
			err = err + 1;
		if (i1 < i2) {
			i3 = i3 - 4;
		} else {
			err = err + 1;
		}
		if (false)
			err = err + 1;
		else {}
		if (o.b() != 0)
			err = err + 1;
		else {}
		if (o.NonPure() && false)
			err = err + 1;
		else {}
		if (o.b() != 1)
			err = err + 1;
		else {}
		if (true) {} else
			err = err + 1;
		if (true) {} else
			err = err + 1;
		if (!o.Pure() || o.NonPure())
			err = err + 1;
		else {}
		if (o.b() != 2)
			err = err + 1;
		else {}
			
		
		return err == 0;
	}
}

class Pass2 {
	int a;
	Other o;
	public boolean test() {
		int i3;
		boolean DUMMY;
		int err;
		err = 0;
		
		o = new Other();
		i3 = 0-2102132736;
		
		{}
		i3 = 0-2102132740;
		{}
		if (o.b() != 0)
			err = 1;
		else {}
		{DUMMY = o.NonPure();}
		if (o.b() != 1)
			err = err + 1;
		else {}
		{}
		{}
		{DUMMY = o.NonPure();}
		if (o.b() != 2)
			err = err + 1;
		else {}
			
		return err == 0;
	}
}
