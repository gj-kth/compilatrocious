/*
 * This is a valid test which contains all the extensions in
 * our grammar and does useful things.
 */
// EXT:CLE
// EXT:CGT
// EXT:CGE
// EXT:CEQ
// EXT:CNE
// EXT:BDJ
// EXT:NBD

class Master {
    public static void main(String[] args) {
        boolean same;
        int[] toBeSorted;
        ExpandableArray expArray;

        same = new SearchSort().startSort();

        // Init
        toBeSorted = new int[5];
        toBeSorted[0] = 123;
        toBeSorted[1] = 2;
        toBeSorted[2] = 20934;
        toBeSorted[3] = 293;
        toBeSorted[4] = 0;

        // Print true if all the values of the array were identical
        if(same != true) { // CNE
            System.out.println(0);
        } else {
            System.out.println(1);
        }
        System.out.println(999999); // Delimiter

        // Will print the index of the number searched for
        System.out.println(new SearchSort().startSearch());
        System.out.println(999999); // Delimiter
        // Returns the sorted array
        toBeSorted = new SearchSort().insertionSort(toBeSorted);
        System.out.println(999999); // Delimiter
        // Will print the given Fibonacci number
        System.out.println(new Fibonacci().start(10));
        System.out.println(999999); // Delimiter

        expArray = new ExpandableArray();
        // Will print 1 if it was the first insertion, which it was
        System.out.println(expArray.insertAt(5, 10));
        System.out.println(expArray.insertAt(8, 20));
        // Will print the value at that location
        System.out.println(expArray.get(5));
        System.out.println(999999); // Delimiter
        System.out.println(expArray.get(8));
    }
}

/* Various search and sort algorithms for arrays */
class SearchSort {
    int[] sortMe;
    int[] searchMe;

    public boolean startSort() {
        sortMe = new int[10];
        sortMe[0] = 145;
        sortMe[1] = 0;
        sortMe[2] = 7;
        sortMe[3] = 44;
        sortMe[4] = 28;
        sortMe[5] = 945;
        sortMe[6] = 117;
        sortMe[7] = 9348;
        sortMe[8] = 198;
        sortMe[9] = 8;

        return this.bubbleSort(sortMe);
    }

    public int startSearch() {
        searchMe = new int[10];
        searchMe[0] = 112;
        searchMe[1] = 2;
        searchMe[2] = 124;
        searchMe[3] = 9;
        searchMe[4] = 1111;
        searchMe[5] = 42;
        searchMe[6] = 0;
        searchMe[7] = 1;
        searchMe[8] = 5696;
        searchMe[9] = 123;

        return this.linearSearch(searchMe, 42);
    }

    // Bubble sort, returns true if all elements were identical
    // Ascending order
    public boolean bubbleSort(int[] sortMe) {
        int i;
        boolean cont;
        boolean same;
        cont = true;
        System.out.println(this.printArray(sortMe)); // Print before sort

        while(cont) {
            cont = false;
            i =  0;
            while(i <= sortMe.length - 2) { // CLE
                if(sortMe[i] >= sortMe[i + 1] + 1) { // CGE
                    int temp; // NBD
                    temp = sortMe[i];
                    sortMe[i] = sortMe[i + 1];
                    sortMe[i + 1] = temp;
                    cont = true; // Swap occured
                } else {}

                i = i + 1;
            }
        }

        System.out.println(this.printArray(sortMe)); // Print after sort

        if(sortMe[sortMe.length - 1] == sortMe[0]) { // CEQ
            same = false;
        } else {
            same = true;
        }
        return same;
    }

    // Search for the index of the given number with linear search
    public int linearSearch(int[] searchMe, int num) {
        int i;
        int numIndex;
        numIndex = 123456;

        System.out.println(num); // Print the wanted number
        System.out.println(999999); // Delimiter
        System.out.println(this.printArray(searchMe)); // Print searched array

        i = 1;
        while(i < searchMe.length) {
            if(searchMe[i] == num) {
                numIndex = i;
            } else {}

            i = i + 1;
        }

        return numIndex;
    }

    // Insertion sort of given array for descending order
    public int[] insertionSort(int[] a) {
        int i;
        int j; // Number of items sorted so far
        int key; // Item to be sorted
        j = 1; // Start with 1
        System.out.println(this.printArray(a)); // Print before sort

        while(j < a.length) {
            key = a[j];

            i = j - 1;
            while((i >= 0) && (a[i] < key)) {
                a[i + 1] = a[i]; // Move up smaller values

                i = i - 1;
            }
            a[i + 1] = key; // Put the key where it belongs

            j = j + 1;
        }

        System.out.println(this.printArray(a)); // Print after sort
        return a;
    }

    // Helper method to print the contents of an array
    public int printArray(int[] array) {
        int i;
        i = 0;
        while(i < array.length) {
            System.out.println(array[i]);
            i = i + 1;
        }
        System.out.println(999999); // Delimiter
        return 0;
    }
}

/* Class which has a method that prints the Fibonacci series
 * based on given n */
class Fibonacci {
    public int start(int n) {
        int f1;
        int f2;
        int fn;
        int i;
        f1 = 0;
        f2 = 1;
        fn = 0; // Init

        i = 2;
        while(i < n) {
            fn = f1 + f2;
            f1 = f2;
            f2 = fn;
            System.out.println(fn); // Print generated number

            i = i + 1;
        }

        return fn;
    }
}

/* An array that will expand if necessary */
class ExpandableArray {
    int[] array;
    boolean init; // Should be default false in Java

    public boolean init(int n) {
        array = new int[n];
        init = true;
        System.out.println(n); // Print the array length
        System.out.println(999999); // Delimiter
        return true || false; // BDJ
    }

    // Insert x at the given index i
    public int insertAt(int i, int x) {
        boolean dummy;
        int wasInit;
        // Make sure it has been created first
        if(!init) {
            dummy = this.init(i + 1);
            wasInit = 1;
        } else {
            wasInit = 0;
        }

        if(i > array.length - 1) { // CGT
            dummy = this.expandArray(i);
        } else {}
        array[i] = x; // Store the new value

        System.out.println(this.printArray(array)); // Print the array in its given state
        return wasInit;
    }

    // At is the index we would want to insert at
    public boolean expandArray(int at) {
        int[] tempArray;
        int i;
        // Calculate how big the new array needs to be
        tempArray = new int[array.length + (at - array.length) + 1];
        i = 0;
        while(i < array.length) {
            // Copy over old values
            tempArray[i] = array[i];
            i = i + 1;
        }
        array = tempArray; // Use the new instead

        System.out.println(this.printArray(array)); // Print the array in its given state
        return true;
    }

    // Get the value at the given index, returns 0 if out-of-bounds
    public int get(int i) {
        int ret;
        ret = 0;

        if(i < array.length) {
            ret = array[i];
        } else {}

        return ret;
    }

    // Helper method to print the contents of an array
    public int printArray(int[] array) {
        int i;
        i = 0;
        while(i < array.length) {
            System.out.println(array[i]);
            i = i + 1;
        }
        System.out.println(999999); // Delimiter
        return 0;
    }
}
