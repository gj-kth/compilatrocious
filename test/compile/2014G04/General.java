/// Simple test for correct MiniJava grammar.
///
/// @version 16-02-2014
class General{

	public static void main(String[] args){
		Torbjoern t;
		int result;
		t = new Torbjoern();
		result = t.torbjoern();
	}
}

class Torbjoern{
	
	int nr_of_torbjoerns;

	public int torbjoern(){
		boolean torbjoern_is_here;
		{
			nr_of_torbjoerns = 1;
			{
				System.out.println(nr_of_torbjoerns);
			}
			torbjoern_is_here = false;	// We know that he is not here!
		}
		if(torbjoern_is_here){
			System.out.println(torbjoern_is_here);
		}
		else {
			System.out.println(torbjoern_is_here);
		}
		return nr_of_torbjoerns;
	}
}