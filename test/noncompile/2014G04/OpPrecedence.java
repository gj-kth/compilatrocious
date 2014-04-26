// EXT:CEQ

/// Testing operator precedence
///
/// @version 25-02-2014
class OpPrecedence{

	public static void main(String[] args){
		Torbjoern t;
		int result;
		boolean b;
		result = 0;
		b = true;
		t = new Torbjoern();
		result = t.torbjoern(result, b);
	}
}

class Torbjoern{
	
	public int torbjoern(int hej, boolean b){
		// This expression should not evaluate
		if(true == 5 == 9){
			hej = 3;
		}
		else{
			hej = 5 * 3 + 1;
		}
		
		// Not operator used on int
		while(!hej == b){
			hej = hej + 1;
		}
		
		return hej;
	}
}
