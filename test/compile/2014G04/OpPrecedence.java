// EXT:CEQ
// EXT:BDJ

/// Testing precedence
///
/// @version 25-02-2014
class OpPrecedence{

	public static void main(String[] args){
		Torbjoern t;
		int result;
		t = new Torbjoern();
		result = t.torbjoern();
	}
}

class Torbjoern{
	
	public int torbjoern(){
		int x;
		int y;
		boolean rutger;
		
		x = 0;
		y = x;
		rutger = x == y;
		
		// Nasty operator precedence test
		if(y == x == true && false || rutger == false && 1 == 2+3*8){
			x = y + 2;
		}
		else{
			y = x + 1;
		}
		return x;
	}
}
