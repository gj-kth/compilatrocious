/**
 * The goal is to find a few numbers using the collatz conjecture
 * sort them and then print them on system out.
 *
 * We will use bubble sort for the actual sorting, but start off
 * by making sure that the sorting is correct using a test class.
 *
 * Since we do not know how big a number sequence will be before actually
 * testing the number using the conjecture, we use a simplified version of an
 * ArrayList<Int> which can expand when needed, as well as inserting numbers at
 * given locations when we want to sort the numbers later on.
 */
class SortCollatzConjecture {
    public static void main(String[] args) {
        SimpleArrayList collCollection;
        SimpleArrayList sortedCollection;
        int i;
        boolean testResult;

        //Assert that the sorting algorithm works
        //Prints "1" if the sorting has worked
        testResult = new TestCases().testBubbleSort();
        if(testResult) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
        //Test the collatz conjecture
        testResult = new TestCases().testBasicCollatzConjecture();
        if(testResult) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }

        //Get the collatz numbers of the number 7
        //Expected output:
        //7, 22, 11, 34, 17,
        //52, 26, 13, 40, 20,
        //10, 5, 16, 8, 4, 2, 1
        /**
         * First print the unsorted list, which should be the sequence
         * in the order the numbers were generated
         *
         * Then print the sorted version for easier reading
         */

        collCollection = new CollatzConjecture().collatzConjectureOf(7);
        i = 0;
        while(i < collCollection.getSize()) {
            System.out.println(collCollection.get(i));
            i = i + 1;
        }

        sortedCollection = new Sorter().bubbleSort(collCollection);
        i = 0;
        while(i < sortedCollection.getSize()) {
            System.out.println(sortedCollection.get(i));
            i = i + 1;
        }
    }
}

/**
 * Calculates the number series that is generated using the Collatz conjecture
 *
 * Recursive definition:
 * collatz(1) = 1
 * collatz(n+1) = n%2=0  => collatz(n/2)
 *              = n%2!=0 => collatz(3n+1)
 */
class CollatzConjecture {
    public SimpleArrayList collatzConjectureOf(int number) {
        boolean kek;
        SimpleArrayList allNumbers;

        allNumbers = new SimpleArrayList();
        kek = allNumbers.initialize(4);
        while(1 < number) {
            kek = allNumbers.add(number);
            if(this.isOdd(number)) {
                number = 3 * number + 1;
            }
            else {
                number = this.divide(number, 2);
            }
        }
        return allNumbers;
    }

    /**
     * Returns true iff n%2=0
     * Otherwise false
     */
    public boolean isOdd(int n) {
        boolean toReturn;
        while(2 < n) {
            n = n - 2;
        }
        if(1 < n) {
            toReturn = false;
        } else {
            toReturn = true;
        }
        return toReturn;
    }

    /**
     * Calculates the integer division of a/b
     */
    public int divide(int a, int b) {
        int q;
        q = 1;
        while(b < a) {
            a = a - b;
            q = q + 1;
        }
        return q;
    }
}

/**
 * For sorting algorithms.
 *
 * Currently implemented:
 * bubble sort,
 *              worst case performance: O(n^2) (probably all the time in this version)
 *              best case performance: O(n) (Ha, probably not this version)
 *              average performance: O(n^2)
 *
 *Will probably not include anything else for the moment
 *
 */
class Sorter {
    public SimpleArrayList bubbleSort(SimpleArrayList sal) {
        int index;
        int swap;
        boolean swapped;
        boolean kek;

        swapped = true;
        /**
         * Really (REALLY) bad implementation of the bubble sort algorithm
         */
        while(swapped) {
            swapped = false;
            index = 0;
            while(index < (sal.getSize()-1)) {
                if(sal.get(index+1) < sal.get(index)) {
                    swap = sal.get(index);
                    kek = sal.addAtIndex(sal.get(index+1), index);
                    kek = sal.addAtIndex(swap, index+1);
                    swapped = true;
                } else {
                    //Do nothing
                }
                index = index + 1;
            }
        }
        return sal;
    }


}

/**
 * Simple version of an ArrayList that can contain integers.
 * The basic functionality of adding items to an expanding array is here.
 *
 * The implementation is not that fast.
 */
class SimpleArrayList {
    int[] array;
    int currSize;
    int maxSize;

    /**
     * This needs to be done in order for the arraylist to work
     */
    public boolean initialize(int size) {
        array = new int[size];
        currSize = 0;
        maxSize = size;
        return true;
    }

    public int get(int index) {
        return array[index];
    }

    /**
     * Adds an integer to the arraylist
     * Expands the array as necessary
     */
    public boolean add(int toAdd) {
        int[] temparray;
        int i;
        if(!(currSize < maxSize)) {
            temparray = new int[maxSize + maxSize];
            i = 0;
            while(i < maxSize) {
                temparray[i] = array[i];
                i = i + 1;
            }
            array = temparray;
            maxSize = maxSize + maxSize;
        } else {
            //Do nothing
        }
        array[currSize] = toAdd;
        currSize = currSize + 1;
        return true;
    }

    public int getSize() {
        return currSize;
    }

    /**
     * Tries to insert an int at the specified index or at last position in array
     *
     * If index is bigger than maxsize then return false.
     * If add worked, return true
     */
    public boolean addAtIndex(int toAdd, int index) {
        boolean toReturn;
        if(!(maxSize-1 < index)) {
            if(currSize < index) {
                currSize = currSize + 1;
                index = currSize;
            } else {
                //Do nothing
            }
            array[index] = toAdd;
            toReturn = true;
        }
        else {
            toReturn = false;
        }
        return toReturn;
    }
}

/**
 * Test the code above
 */
class TestCases {
    public boolean testBubbleSort() {
        int i;
        boolean wasSorted;
        boolean kek;
        boolean toReturn;
        SimpleArrayList sal;
        SimpleArrayList toSort;
        SimpleArrayList returned;
        Sorter sorter;

        i = 0;
        sal = new SimpleArrayList();
        kek = sal.initialize(10);
        toSort = new SimpleArrayList();
        kek = toSort.initialize(10);
        sorter = new Sorter();

        while(i < 10) {
            kek = sal.add(i+1);
            i = i + 1;
        }

        //Always worst case search
        i = 10;
        while(0 < i) {
            kek = toSort.add(i);
            i = i - 1;
        }

        returned = sorter.bubbleSort(toSort);
        wasSorted = this.assertEquals(returned, sal);
        //Basically, a printed 1 means it succeeded and a 0 means it didnt
        if(wasSorted) {
            toReturn = true;
        } else {
            toReturn = false;
        }
        return toReturn;
    }

    /**
     * Test the collatz conjecture.
     */
    public boolean testBasicCollatzConjecture() {
        boolean succeded;
        SimpleArrayList sorted;
        SimpleArrayList collatz;

        sorted = new SimpleArrayList();
        succeded = sorted.initialize(5);
        succeded = sorted.add(2);
        succeded = sorted.add(4);
        succeded = sorted.add(5);
        succeded = sorted.add(8);
        succeded = sorted.add(16);
        collatz = new CollatzConjecture().collatzConjectureOf(5);
        collatz = new Sorter().bubbleSort(collatz);
        succeded = this.assertEquals(sorted, collatz);
        return succeded;
    }

    /**
     * Just checks that two arraylists are the same.
     */
    public boolean assertEquals(SimpleArrayList sal1, SimpleArrayList sal2) {
        int i;
        int current;
        boolean fail;
        boolean toReturn;
        i = 0;

        fail = false;
        toReturn = true;
        //Assert equal size of the arraylists
        if(sal1.getSize() < sal2.getSize()) {
            fail = true;
            toReturn = false;
        } else {
            //Do nothing
        }
        if(sal2.getSize() < sal1.getSize()) {
            fail = true;
            toReturn = false;
        } else {
            //Do nothing
        }

        if(!fail) {
            while(i < sal1.getSize()) {
                current = sal1.get(i);
                //Assert equal size of the ints in the arraylists
                if(current < sal2.get(i)) {
                    toReturn = false;
                } else {
                    //Do nothing
                }
                if(sal2.get(i) < current) {
                    toReturn = false;
                } else {
                    //Do nothing
                }
                i = i + 1;
            }
        } else {
            //Do nothing
        }
        return toReturn;
    }
}
