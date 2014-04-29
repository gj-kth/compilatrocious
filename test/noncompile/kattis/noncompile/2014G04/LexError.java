// EXT:CEQ

/// Tests that lexer is working fine
///
/// @version 24-04-2014
class LexError{

	public static void main(String[] args){
		Torbjoern t;
		int result;
		result = 0;
		t = new Torbjoern();
		result = t.torbjoern(result, false);
	}
}

class Torbjoern{
	
	public int torbjoern(int hej, boolean b){
		// Missing right paren
		if((((((b == true)))){
			hej = 1;
		}
		else{
			hej = 3;
		}
		return hej;
	}

}
