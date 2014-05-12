//EXT:CEQ
//EXT:CNE
//EXT:BDJ
class BooleanTrouble {

	public static void main(String[] s) {
		TroubleMaker args;
		args = new TroubleMaker();
		while(!(!(args.getStatus() > 0))) {
			System.out.println(!args.doubleEquality(args.getStatus() - 10));
		}
	}
}

class TroubleMaker {
	boolean a;
	boolean b;
	int counter;
	
	public int[] init(int s) {
		counter = s;
		return new int[10];
	}
	
	public int getStatus() {
		return counter;
	}
	
	public boolean doubleEquality(int b) {
		int[] numbaz;
		numbaz = new int[25*4-5 * b];
		while((counter < numbaz.length) && true) {
			if(counter == numbaz[counter] == true) {
				numbaz[counter] = numbaz.length-counter*counter;
			}
			else {
				while(b < 10 != a) {
					b  = b + 1;
					a = !(numbaz.length + b < 25 || !a);
				}
			}
		}
		counter = counter - 1;
		return(!(a || b) && (!a || !b));
	}
}
