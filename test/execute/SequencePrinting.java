// EXT:IWE
// EXT:CLE
// EXT:CGT
// EXT:CGE
// EXT:BDJ
// EXT:ISC
// EXT:ICG

class SequencePrinting {
	public static void main(String[] args) {
		SequencePrinter sp;
		FibonacciGenerator fg;
		LinearGenerator lg;
		sp = new SequencePrinter();
		fg = new FibonacciGenerator();
		lg = new LinearGenerator();
		sp.printFibonacci(fg, 2); // 0 1
		sp.printFibonacci(fg, 0); // 
		sp.printFibonacci(fg, 6); // 0 1 1 2 3 5
		sp.printLinear(lg);		  // 1 2 3 4 5 6 7 8 9 10
		sp.printFibonacci(fg, 1); // 0
		sp.printFibonacci(fg, 1); // 0
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
		dummy = setArray(fg.getArray(seqLength));
		return printArray();
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

		dummy = setArray(lg.getArray(seqLength));
		dummy = printArray();
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





