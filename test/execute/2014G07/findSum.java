class MainClass {
    public static void main(String[] argz) {
        boolean bad;
        SumFinder f;
        f = new SumFinder();
        bad = f.init(100,4);
        System.out.println(f.run());  //176851
    }
}

class SumFinder {
    int sumToFind;
    int numberOfNumbers;
    IntHelpers ints;
    ArrayHelpers ah;
    public boolean init(int sum,int numbers) {
        ints = new IntHelpers();
        ah = new ArrayHelpers();
        sumToFind = sum;
        numberOfNumbers = numbers;
        return false;
    }
    /* Returns the number of combinations that sum to sumToFind */
    public int run() {
        int[] numbers;
        boolean done;
        int sum;
        int count;

        done = false;
        count = 0;
        numbers = new int[numberOfNumbers]; /* Should default to 0 */
        while (!done) {
            done = ah.incArray(numbers,sumToFind);
            sum = ah.sumOfArray(numbers);
            if (ints.equals(sum,sumToFind)) {
                count = count+1;
            } else {
                if (sumToFind < sum) {
                    //Decrease the sum by setting the last number to 0 after the next inc
                    numbers[0] = sumToFind;
                } else {
                    //Increase sum so we get the correct sum
                    numbers[0] = numbers[0]+(sumToFind-sum-1);
                }
            }
        }
        return count;
    }
}

class ArrayHelpers {
    /**
     Sum all elements in array
     */
    public boolean printArray(int []arr) {
        int i; int sum;
        i = 0;
        while (i < arr.length) {
            System.out.println(arr[i]);
            i = i + 1;
        }
        return false;
    }

    /**
     Print array
     */
    public int sumOfArray(int []arr) {
        int i; int sum;
        i = 0;
        sum = 0;
        while (i < arr.length) {
            sum = sum + arr[i];
            i = i+1;
        }
        return sum;
    }
    /**
     Increases the values of an array
     eg (maxval 3) 0,0,0 becomes 0,0,1 while 0,3,3 becomes 1,0,0
     */
    public boolean incArray(int []arr,int maxVal) {
        boolean done; /* Should default to false  */
        int at; /* Should default to 0 */
        at = 0;
        done = false;
        while (at < arr.length && !done) {
            arr[at] = arr[at]+1;
            if (maxVal < arr[at]) {
                arr[at] = 0;
                at = at + 1;
            } else {
                done = true;
            }
        }

        return  !(at < arr.length) && !(at < arr.length);
    }
}

class IntHelpers {
    public boolean equals(int a,int b) {
        return !(a < b) && !(b < a);
    }
    /**
     a <= b
     */
    public boolean leq(int a,int b) {
        return !(b < a);
    }
    /**
     a >= b
     */
    public boolean geq(int a,int b) {
        return this.leq(b,a);
    }
}
