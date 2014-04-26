/// Testing scope
///
/// @version 25-02-2014
class Scope{

	public static void main(String[] args){
		Torbjoern t;
		int result;
		t = new Torbjoern();
		result = t.Torbjoern();
	}
}

class Torbjoern{
	
	int x;
	int y;
	
	public int Torbjoern(){
		int x;
		x = 33;
		x = this.calculate(x, y+1);
		return x;
	}
	
	public int calculate(int x, int y){
		boolean m;
		m = false;
		y = this.calcSeveral(x, m,y);
		return x;
	}
	
	public int calcSeveral(int y, boolean c, int j){
		return j;
	}
}
