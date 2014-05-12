// EXT:INT32
class test {
  public static void main(String[] grizzly) {
	int i;
	int []array;
	boolean trash;
	evalCounter c;
	c = new evalCounter();
	
	array = new int[c.printAndRet(1,10)];
	array[c.printAndRet(2,9)] = c.printAndRet(3,256);
	i = 0;
	if (c.printAndRet(4,10) < c.printAndRet(5,10)) {
		// 10 > 10 , should never happen
		i = c.printAndRet(0,0); //Make sure fine is false
	} else {
		i = c.printAndRet(6,10);
	}
	
	trash = (c.printAndRet(7,11) < c.printAndRet(8,10)) && (c.printAndRet(0,0) < c.printAndRet(0,1));
	//Java evaluates things left to right, even if there's precedence thus (note compare val)
	i = c.printAndRet(9,1)+c.printAndRet(10,1)+c.printAndRet(11,1)*c.printAndRet(12,1)+c.printAndRet(13,1);
	
	//So last value of i is unused, but the calls to printAndRet must still have been performed.
	i = c.printAndRet(14,1)*c.printAndRet(15,1)-c.printAndRet(16,1)*c.printAndRet(17,1)+c.printAndRet(18,1);
	i = c.printAndRet(19,10);
	array[c.printAndRet(20,0)] = c.printAndRetArray(21,array)[c.printAndRet(22,9)];
	trash = c.dummy(c.printAndRet(23,0),
			c.printAndRet(24,0),
			c.printAndRet(25,0),
			c.printAndRet(26,0),
			c.printAndRet(27,0),
			c.printAndRet(28,0),
			c.printAndRet(29,0));
			
	//Try to overflow an integer
	i = 2147483647+c.printAndRet(30,1);
	//Overflow should become -2147483648
	if (c.printAndRet(31,i) < c.printAndRet(32,0-2147483647)) {
		trash = false;
	} else {
		System.out.println(0-1);
	}
	
	//Try to undeflow the integer
	i = c.printAndRet(33,i)-c.printAndRet(34,1);
	if (c.printAndRet(35,2147483646) < c.printAndRet(36,i)) {
		trash = false;
	} else {
		System.out.println(0-1);
	}
	i = c.printAndRet(37,0);
  }
}

class evalCounter {
	public int printAndRet(int order,int value) {
		System.out.println(order);
		return value;
	}
	public int[] printAndRetArray(int order,int[] value) {
		System.out.println(order);
		return value;
	}
	public boolean dummy(int a,int b,int c,int d,int e,int f,int g) {
		return true;
	}
}
