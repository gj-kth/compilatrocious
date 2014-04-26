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
		
		if(anInt < anIntArray[anInt] && !thisTestIsBogus) { 
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
		return alwaysTure; // UndefinedVariable.java:53: Undefined variable alwaysTure in resetCounter in class TheCounterClass
	}
}
