
class good1 {
    
    public static void main(String[] args){
        int counter;
        int [] array;
        int[] sortedarray;
        Sortingclass sorter;
        array = new int[8];
        array[0] = 0;
        array[1] = 7;
        array[2] = 6;
        array[3] = 5;
        array[4] = 4;
        array[5] = 3;
        array[6] = 2;
        array[7] = 1;
        sorter = new Sortingclass();
        sortedarray = sorter.sort(array);
        counter = 0;
        while(counter < array.length){
            System.out.println(sortedarray[counter]);
            counter = counter + 1;
        }
    }
    
    
}
class Sortingclass{
    int n;
    int left;
    int right;
    int largest;
    int [] a;
    
    public int[] sort(int []array){
        int i;
        int rint;
        a = array;
        n= a.length -1;
        i = n;
        rint = this.buildheap(a);
        rint = this.printlist(a);
        while(0 < i){ 
            rint = this.exchange(0,i);
            rint = this.printlist(a);
            n = n-1;
            
            rint = this.maxheap(a,0); 
            i = i-1;
        }
        return a;
    }
    public int div2(int a){
        int i; 
        i = 0;
        while(2 < a){
            a = a - 2;
            i = i + 1;
        }
        if(this.equal(a, 2)){
            i = i +1;
        }else{
            
        }
        return i;
    }
    public int buildheap(int[] b){
        int i;
        int rint;
        n = a.length -1;
        i = this.div2(n);
        i = i +1;
        while(0<i){
            rint = this.maxheap(b,i-1);
            i = i -1;
        }
        return 0;
    }
    public int maxheap(int[]a, int i){
        int rint;
        int ifn;
        left = 2*i;
        right = 2*i +1;

        ifn = n+1;
        if(left< ifn && a[i]< a[left]){
            largest = left;
        }
        else{
            largest = i;
        }
        if(right< ifn && a[largest]< a[right]){
            largest = right;
        }
        else{
            
        }
        if(!this.equal(largest, i)){
            rint = this.exchange(i,largest);
            rint = this.maxheap(a,largest);
        }
        else{
            
        }
        return 0;
            
    }
    public int exchange(int i, int j){
        int t;
        t = a[i];
        a[i] = a[j];
        a[j] = t;
        return 0; 
    }
    public boolean equal(int a, int b) {
        boolean eq;
        if (!(a < b) && !(b < a)) {
          eq = true;
        } else { eq = false; }
        return eq;
     }
    public int printlist(int[]a){
        int le;
        le = 0;
        while(le < a.length){

            System.out.println(a[le]);
           
            le = le +1;
        }
        return le;
    }
}
