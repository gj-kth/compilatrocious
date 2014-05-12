// EXT:IWE
// EXT:CLE
// EXT:CGT
// EXT:CGE
// EXT:BDJ
// EXT:ISC
// EXT:ICG
// EXT:CEQ
// EXT:CNE
class SequencePrinting {
	public static void main(String[] args) {
		SequencePrinter sp;
		FibonacciGenerator fg;
		LinearGenerator lg;
		boolean tmp;
		sp = new SequencePrinter();
		fg = new FibonacciGenerator();
		lg = new LinearGenerator();
		tmp = sp.printFibonacci(fg, 2); // 0 1
		tmp = sp.printFibonacci(fg, 0); // 
		tmp = sp.printFibonacci(fg, 6); // 0 1 1 2 3 5
		tmp = sp.printLinear(lg);		  // 1 2 3 4 5 6 7 8 9 10
		tmp = sp.printFibonacci(fg, 1); // 0
		tmp = sp.printFibonacci(fg, 1); // 0
	}
}

class ArrayPrinter{

	int[] array;

	public boolean setArray(int[] a){
		array = a;
		return true;
	}

	public boolean printArray(){
		int i;
		i = 0;
		while(i < array.length){
			System.out.println(array[i]);
			i = i + 1;
		}
		return true;
	}
}

class SequencePrinter extends ArrayPrinter{
	public boolean printFibonacci(FibonacciGenerator fg, int seqLength){
		boolean dummy;
		dummy = this.setArray(fg.getArray(seqLength));
		return this.printArray();
	}

	public boolean printLinear(LinearGenerator lg){
		boolean dummy;
		int seqLength;
		boolean result;
		seqLength = (15 - 10) * 2;
		if(seqLength > 1000){
			result = false;
		}else{
			result = true;
		}

		dummy = this.setArray(lg.getArray(seqLength));
		dummy = this.printArray();
		if(dummy != true || result == false){
			result = false;
		}
		return result;
	}
}

class FibonacciGenerator{
	public int[] getArray(int seqLength){
		int[] a;
		int i;
		a = new int[seqLength];
		if(seqLength == 1){
			a[0] = 0;
		}
		if(seqLength >= 2){
			a[0] = 0;
			a[1] = 1;
		}
		i = 2;
		while(i < a.length){
			a[i] = a[i-2] + a[i-1];
			i = i + 1;
		}
		return a;
	}
}

class LinearGenerator{
	public int[] getArray(int seqLength){
		int[] a;
		int i;
		a = new int[seqLength];
		i = 0;
		while(i <= a.length - 1){
			a[i] = i+1;
			i = i + 1;
		}
		return a;
	}
}





