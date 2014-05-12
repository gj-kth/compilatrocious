/// Tests that method parameters are checked
///
/// @version 25-02-2014
class ParameterCheck{

	public static void main(String[] args){
		Torbjoern t;
		int result;
		result = 1;
		t = new Torbjoern();
		// Too few parameters
		result = t.torbjoern(result);
	}
}

class Torbjoern{
	
	public int torbjoern(int hej, boolean b){
		hej = 5 + 20 * 44;
		// Too many parameters
		b = this.isTrue(b, b && false);
		return hej;
	}
	
	public boolean isHackingTime(){
		int t;
		// Incorrect type for second parameter
		t = this.torbjoern(t, this.torbjoern(t, true));
		// No parameters to a method that needs parameters
		return this.isTrue();
	}
	
	/* MAGICAL FUNCTION!!! */
	public boolean isTrue(boolean check){
		boolean result;
		if(check){
			result = true;
		}
		else{
			result = false;
		}
		return result;
	}
}
