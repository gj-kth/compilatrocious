/// Test case for non-execute generating a stack overflow.
///
/// @version 31-03-2014
class StackOverflow{

	public static void main(String[] args){
		Torbjoern t;
		int result;
		Cookie c;
		c = new Cookie();
		t = new Torbjoern();
		result = t.torbjoern(c);
	}
}

class Torbjoern{
	
	public int torbjoern(Cookie c){
		boolean temp;
		temp = c.putDown(true);
		return 0;
	}
}

class Cookie {

	boolean down;

	// PUT THAT COOKIE DOWN!!!!
	public boolean putDown(boolean flip){
		boolean temp;
		if(flip){	
			down = true;
		}
		else{
			down = false;
		}
		temp = this.putDown(!flip);
		return down;
	}
}