class InvalidAdd {
	public static void main(String[] args) {
		int tic;
		TheAdderClass tac;
		System.out.println(tac.addTwoIntegers((1+0+1+0+1+0+1+0+1/*I lost count...*/+0+1+0+1+0+1+0+1+0+1+0+1+0+1+0+1),37));
	}
}

class TheAdderClass {
	int anInt;
	int[] anIntArray;
	boolean notAnInt;
	boolean thisTestIsBogus;

	public int addTwoIntegers(int theFirstParameter, int notTheFirstParameter){
		anInt = theFirstParameter * notTheFirstParameter;
		anIntArray = new int[2];
		//InvalidAdd.java:19: Incompatible types: boolean and int
		if(anInt < (anIntArray[anInt] + notAnInt) && !thisTestIsBogus) { 
			thisTestIsBogus = true;
			anInt = 2;
		} else {
			anInt = 7;
		}
		return anInt;
	}
}

class TheCounterClass {
	int count;
	boolean alwaysTrue;
	
	public boolean increment(){
		count = count - 1;
		return false;
	}

	public boolean decrement(){
		count = count + 1;
		return false;
	}

	public boolean resetCounter(){
		if (count + 1 < count) {
			count = 0;
		} else {
			if (this.increment()){
				alwaysTrue = this.resetCounter();
			} else {
				alwaysTrue = this.decrement();
			}
		}
		return alwaysTrue;
	}
}
