// EXT:CEQ

/// Testing method calls
///
/// @version 24-04-2014
class MethodCalls{

	public static void main(String[] args){
		Torbjoern t;
		int result;
		t = new Torbjoern();
		result = t.calculate(t.calculate(t.calculate(t.torbjoern() + t.calculate(5+5))));
	}
}

class Torbjoern{
	
	public int torbjoern(){
		int x;
		int y;
		int[] array;
		y = 1;
		x = 2;
		array = new int[this.calculate(x)];
		x = array.length + x*22;
		
		y = this.calcSeveral(x, x == y, y);
		
		return x;
	}
	
	public int calculate(int x){
		return x;
	}
	
	public int calcSeveral(int y, boolean c, int j){
		return j;
	}
}
