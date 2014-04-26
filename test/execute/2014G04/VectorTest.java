// EXT:CGT
// EXT:CEQ

/// Test case for execute - implements a vector and does some small checks.
///
/// @version 31-03-2014

class VectorTest{

	public static void main(String[] args){
		boolean temp;
		Vector v;
		v = new Vector();
		
		// Add elements
		temp = v.init(5);
		temp = v.add(4);
		temp = v.add(3);
		temp = v.add(2);
		temp = v.add(1);
		temp = v.add(0);
		
		// Check size
		System.out.println(v.size()); // 6
		
		// Output vector (last -> first)
		System.out.println(v.get(5)); // 0
		System.out.println(v.get(4)); // 1
		System.out.println(v.get(3)); // 2
		System.out.println(v.get(2)); // 3
		System.out.println(v.get(1)); // 4
		System.out.println(v.get(0)); // 5
	}
}

class Vector{
	boolean tailEmpty;
	IntElement head;
	Vector tail;
	int size;

	/**
	 * Initializes the data structure.
	 * param i: Integer to associate with this entry
	 */
	public boolean init(int i){
		IntElement e;
		boolean temp;
		e = new IntElement();
		temp = e.setX(i);
		head = e;
		tailEmpty = true;
		size = 1;
		return true;
	}
	
	/**
	 * Adds the element to the Vector.
	 * param i: Integer to add
	 */
	public boolean add(int i){
		boolean temp;
		if(tailEmpty){
			tail = new Vector();
			temp = tail.init(i);
			tailEmpty = false;
		}
		else{
			temp = tail.add(i);
		}
		size = size + 1;
		return true;
	}
	
	/**
	 * Returns the element at index (or a bogus element if index is out of bounds).
	 */
	public int get(int index){
		int i;
		i = 0 - 1;
		// Return bogus value since NULL is not defined in MiniJava (yay..)
		if(size > index){
			// Element was here (hurray)
			if(index == 0){
				i = head.getX();
			}
			else{
				// Continue along recursive chain in search for the element.
				if(!tailEmpty){
					i = tail.get(index-1);
				}
				else{}
			}
		}
		else{}
		return i;
	}
	
	/**
	 * Returns the size of the vector (nr of elements).
	 */
	public int size(){
		return size;
	}	
}

class IntElement{
	int x;
	
	/**
	 * Sets x to input.
	 * param i: Value to set x to.
	 */
	public boolean setX(int i){
		x = i;
		return true;
	}
	
	/**
	 * Returns the value of x.
	 */
	public int getX(){
		return x;
	}
}
