// EXT:CLE
// EXT:CGT
// EXT:CGE
// EXT:CEQ
// EXT:CNE
// EXT:BDJ
// EXT:IWE
// EXT:ISC
// EXT:ICG

class Main {
	public static void main(String[] args){
		Judge charlie;
		Referee ref;
		int aliceShare;
		int bobShare;
		charlie = new Judge();
		ref = new Referee();
		aliceShare = 5;
		bobShare = 10;

		if(charlie.isFairSplit(aliceShare, bobShare) == true) { // boolean equal comparison
			System.out.println(11);
		} else {
			System.out.println(10);
		}

		aliceShare = 10;
		bobShare = 21;

		if(charlie.isFairSplit(aliceShare, bobShare) == charlie.isFairSplit(bobShare, aliceShare)) { // boolean equal comparison
			System.out.println(21);
		} else {
			System.out.println(20);
		}

		if(charlie.favoriteCologne() != charlie.favoriteNumber()){ // interger not equal comparison
			if(!(true == false)){
				System.out.println(31);
			}
		} else {
			System.out.println(30);
		}

		if(42 == charlie.justANumber()){ // interger not equal comparison
			System.out.println(41);
		} else {
			System.out.println(40);
		}

		if (ref.isUnfairSplit(3,4) == !charlie.isFairSplit(3,4)) {
			System.out.println(1 == 1);
			System.out.println(ref.favoriteNumber());
		}
		
	}
}

class Referee extends Judge {
	public boolean isUnfairSplit(int x, int y) {
		return !this.isFairSplit(x, y);
	}

	public int favoriteNumber() {
		return 9001;
	}
}

class Judge {
	public boolean isFairSplit(int x, int y) {
		boolean isFair;
		if(x < y && x + 10 >= y || x > y && x <= y + 10) {
			isFair = true;
		} else {
			isFair = false;
		}
		return isFair;
	}

	public int favoriteNumber(){
		int answer;
		if(true != false){ // boolean not equal comparison
			answer = 404;
		} else {
			answer = 4711;
		}
		return answer;
	}

	public int favoriteCologne(){
		return 4711;
	}

	public int justANumber(){
		return 15 + 38 - 11;
	}
}
