class BubbleSortTest{
    public static void main(String[] a){
    	System.out.println(new Tester().test1(1*3*3*7));
    	System.out.println(new Tester().test2(11));
    }
}

class Tester {
	BoubleSort bs;
	int[] array;
    int size;
	int var1;
	
    public boolean test1(int sz){
    	bs = new BoubleSort();
		array = this.initDescendingArray(sz);
		var1 = bs.sort(array);
		var1 = this.print();
		return true;
    }


    public boolean test2(int sz){
    	bs = new BoubleSort();
		array = this.initRandomArray(sz);
		var1 = bs.sort(array);
		var1 = this.print();
		return false;
    }

    // initialize in ascending order
    public int[] initDescendingArray(int sz){
		int j;
		size = sz;
		array = new int[sz];
		
		j = 0;
		while (!false && (j < size)) {
		    array[j] = size-j;
		    j = j + 1;
		}
		return array;	
    }

   // Initialize array of integers
    public int[] initRandomArray(int sz){
		size = sz ;
		array = new int[sz] ;
		
		array[0] = 123;
		array[1] = 7; 
		array[2] = 1;
		array[3] = 0-5;
		array[4] = 251; 
		array[5] = 25;
		array[6] = 23; 
		array[7] = 2; 
		array[8] = 55; 
		array[9] = 2;
	
		return array;	
    }


    public int print(){
		int j ;
		j = 0 ;
		while (j < (size)) {
		    System.out.println(array[j]);
		    j = j + 1;
		}
		return 0-1;
    }
}

class BoubleSort{
	int nt;
	int i;
	int var1;
	int var2;
	int var3;
	int var4;
	int var5;
	int j;
	int t;
	
    // sort using bouble sort
    public int sort(int[] array){
		i = array.length - 1 ;
		var1 = 0 - 1;
		while (var1 < i) {
		    j = 1;
		    while (j < (i+1)){
			    var5 = j - 1;
				var2 = array[var5];
				var3 = array[j];
				if (var3 < var2) {
					var4 = j - 1;
				    t = array[var4];
				    array[var4] = array[j];
				    array[j] = t;
				}
				else{ 
					nt = 0;
				}
				
				j = j + 1;
			}
		    i = i - 1;
		}
		return 0;
    }

}
