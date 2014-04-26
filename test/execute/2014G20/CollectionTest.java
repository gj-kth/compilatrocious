// EXT:ISC
// EXT:ICG
// EXT:LONG
// EXT:IWE
// EXT:NBD
// EXT:BDJ
// EXT:CEQ
// EXT:CNE
// EXT:CLE
// EXT:CGT
// EXT:CGE

/*
	A MiniJava-implementation of List collections,
	namely the Array List and the Linked List.
	The lists implement support for storing any number
	of longs. The Linked List is built on singly-linked nodes
	pointing to their next node. The Array List is built on a
	long-array, which is doubled in size every time it's current size
	is reached.
*/
class CollectionTest {
    public static void main(String[] args) {
        System.out.println(new TestSuite().start());
    }
}

/*
	Test suite which tests the standard functionality of both the
	Array List and the Linked List.
*/
class TestSuite {
	int testshadow1;
	int testshadow2;
	
	public boolean start() {
		List arrayList;
        List linkedList;
        boolean arrayListInit;
        boolean linkedListInit;
        int i;
        int j;
        boolean trash;
		testshadow1 = 100;
        testshadow2 = 200;
		
        arrayList = new ArrayList();
        linkedList = new LinkedList();
        arrayListInit = arrayList.init();
        linkedListInit = linkedList.init();
        
        System.out.println(arrayList.isEmpty());
        System.out.println(linkedList.isEmpty());
        
        //Simple adding and getting items to and from arraylist.
        i = 0;
        while(i < 1000) {
			long tmp;
			tmp = i;
            trash = arrayList.add(tmp);
            i = i + 1;
        }
        i = 999;
        while(i > 0) {
			long tmp;
			tmp = i;
            trash = arrayList.add(tmp);
            i = i - 1;
        }
		trash = arrayList.add(0L);
        i = 990;
        while(i < 1000 || i < 1010) {
            System.out.println(arrayList.get(i));
            i = i + 1;
        }
        
        System.out.println(arrayList.size());
        System.out.println(linkedList.size());
        
        //Fibonacci numbers.
        i = 2;
        trash = linkedList.add(0);
        trash = linkedList.add(1);
        while(i <= 20) {
            trash = linkedList.add(linkedList.get(i - 2) + linkedList.get(i - 1));
            i = i + 1;
        }
        j = 10;
        while(j >= 0) {
            System.out.println(linkedList.get(0));
            trash = linkedList.remove(0);
            j = j - 1;
        }
        System.out.println(linkedList.size());
		
		arrayList = new ArrayList();
		arrayListInit = arrayList.init();
		{
			int testshadow1;
			testshadow1 = 50;
			{
				int testshadow2;
				testshadow2 = 150;
				trash = arrayList.add(testshadow1);
				trash = arrayList.add(testshadow2);
			}
			trash = arrayList.add(testshadow1);
			trash = arrayList.add(testshadow2);
		}
		trash = arrayList.add(testshadow1);
		trash = arrayList.add(testshadow2);
		while(!arrayList.isEmpty()) {
			System.out.println(arrayList.get(0));
			trash = arrayList.remove(0);
		}
		return true;
	}
}

/*
	The List-interface (or superclass). This class
	is not meant to be instantiated. Rather, both
	the Linked List and Array List extend this class.
*/
class List {

    public long get(int i) {
        return 0;
    }
    
    public boolean add(long value) {
        return false;
    }
    
    public boolean isEmpty() {
        return true;
    }
    
    public boolean remove(int i) {
        return false;
    }
    
    public int size() {
        return 0;
    }
    
    public boolean init() {
        return false;
    }
    
    public boolean contains(long value) {
        return false;
    }
}

/*
	The MiniJava implementation of an Array List.
*/
class ArrayList extends List {
    long[] backing_array;
    int actual_size;
    
    public boolean init() {
        backing_array = new long[8];
        actual_size = 0;
        return true;
    }
    
    public int size() {
        return actual_size;
    }
    
    public boolean add(long value) {
        if(actual_size == backing_array.length) {
            long[] new_backing_array;
            int i;
            new_backing_array = new long[backing_array.length * 2];
            
            i = 0;
            while(i < backing_array.length) {
                new_backing_array[i] = backing_array[i];
                i = i + 1;
            }
            backing_array = new_backing_array;
        }
        backing_array[actual_size] = value;
        actual_size = actual_size + 1;
        return true;
    }
    
    public boolean isEmpty() {
        boolean empty;
        empty = false;
        if(actual_size == 0)
            empty = true;
        return empty;
    }
    
    public long get(int i) {
        return backing_array[i];
    }
    
    public boolean remove(int i) {
        int it;
        it = i;
        
        while(it + 1 < actual_size) {
            backing_array[it] = backing_array[it + 1];
            it = it + 1;
        }
        actual_size = actual_size - 1;
		return true;
    }
    
    public boolean contains(int value) {
        int i;
        boolean contains;
        contains = false;
        
        i = 0;
        while(i < actual_size) {
            if(backing_array[i] == value) {
                contains = true;
                i = actual_size;
            }
        }
        return contains;
    }
}

/*
	The MiniJava implementation of a Linked List.
*/
class LinkedList extends List {
    LinkedNode current;
    LinkedNode start;
    int size;

    public long get(int i) {
        int it;
        LinkedNode tmp;
        
        it = 0;
        tmp = start;
        while(it < i) {
            tmp = tmp.getNext();
            it = it + 1;
        }
        return tmp.getValue();
    }
    
    public boolean add(long value) {
        LinkedNode tmp;
        boolean init;
		boolean trash;
        
		tmp = new LinkedNode();
        init = tmp.setValue(value);
        if(size > 0) {
            trash = current.setNext(tmp);
            current = tmp;
        } else {
           current = tmp;
           start = current; 
        }
        size = size + 1;
        return true;
    }
    
    public boolean isEmpty() {
        return !(size != 0);
    }
    
    public boolean remove(int i) {
        int it;
        LinkedNode tmp;
        LinkedNode prev_tmp;
        boolean done;
        
        if(i > 0) {
            it = 0;
            tmp = start;
			prev_tmp = start;
            while(it < i) {
                prev_tmp = tmp;
                tmp = tmp.getNext();
                it = it + 1;
            }
            done = prev_tmp.setNext(tmp.getNext());
        } else {
            start = start.getNext();
            done = true;
        }
        size = size - 1;
        return done;
    }
    
    public int size() {
        return size;
    }
    
    public boolean init() {
        size = 0;
        return true;
    }
    
    public boolean contains(long value) {
        int i;
        LinkedNode tmp;
        boolean contains;
        contains = false;
        
        i = 0;
        tmp = start;
        while(i < size) {
            if(tmp.getValue() == value) {
               contains = true;
               i = size;
            }
            tmp = tmp.getNext();
            i = i + 1;
        }
        return contains;
    }
}

/*
	A representation of a singly-linked node that is
	used as base structure in the Linked List for storing
	values.
*/
class LinkedNode {
    long value;
    LinkedNode next;
    
    public boolean init(long value, LinkedNode next) {
        return this.setValue(value) && this.setNext(next);
    }
    
    public boolean setValue(long tempValue) {
        value = tempValue;
        return true;
    }
    
    public boolean setNext(LinkedNode tempNext) {
        next = tempNext;
        return true;
    }
    
    public long getValue() {
        return value;
    }
    
    public LinkedNode getNext() {
        return next;
    }
}
