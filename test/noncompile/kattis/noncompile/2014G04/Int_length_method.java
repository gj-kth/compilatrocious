/// Tests that integers do not have length fields or methods.
///
/// @version 25-02-2014
class Int_length_method{

	public static void main(String[] args){
		Torbjoern t;
		int result;
		t = new Torbjoern();
		result = t.torbjoern();
	}
}

class Torbjoern{
	
	int hej;
	
	public int torbjoern(){
		hej = hej - hej * hej.length;
		hej = hej.hej();
		return hej;
	}
}
