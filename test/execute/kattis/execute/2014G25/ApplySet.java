// EXT:IWE
// EXT:CLE
// EXT:CGT
// EXT:CGE
// EXT:CEQ
// EXT:CNE
// EXT:BDJ

class ApplySet {

	public static void main(String[] args) {
		Set s1;
		Set s2;
		Set s;
		s1 = new Set();
		s2 = new Set();	
		s = s1.initializeSet();
		s = s2.initializeSet();
		
		s = s1.printSet();
		s = s2.printSet();
		
		s = s1.union(s2).printSet();
		
		
		s = s1.addElement(4);
		s = s2.addElement(4);
		s = s1.printSet();
		s = s2.printSet();
		
		s = s1.union(s2).printSet();
		
		s = s1.clear();
		s = s1.printSet();
		s = s2.clear();
		
		s = s1.addElement(0-123);
		s = s2.addElement(34);
		
		s = s1.addElement(34556);
		s = s2.addElement(346547657);
		
		s = s1.addElement(0);
		s = s2.addElement(0);
		
		
		s = s1.union(s2).printSet();
		s = s1.addElement(0);
		s = s2.addElement(123);
		s = s2.addElement(34556);
		
		s = s1.union(s2).printSet();
	}
	
}

class Set {
	SetElement nullElement;
	SetElement first;
	SetElement last;
	int size;


	public Set initializeSet() {
		nullElement = new SetElement();
		first = nullElement;
		last = nullElement;
		size = 0;

		return this;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public Set addElement(int newElement) {
		SetElement newSetElement;
		SetElement previous;
		SetElement current;
		SetElement s;
		
		//If empty set.
		if (size == 0) {
			first = new SetElement().setElement(newElement, nullElement);
			last = first;
			size = 1;
		} else {
			//If singleton set.
			if (size == 1) {
				if (!(newElement <= first.getElement())) {
					s = last.setNext(new SetElement().setElement(newElement, nullElement));
					last = first.getNext();
					size = 2;
				} else {
					if (newElement < first.getElement()) {
						first = new SetElement().setElement(newElement, first);
						size = 2;
					}
				}
			} else {
				//Set with arbitrary size greater than 1.
				if (newElement < first.getElement()) {			//If first element is greater, then newElement is added first.
					newSetElement = new SetElement().setElement(newElement, first);
					first = newSetElement;
					size = size + 1;
				} else {
					if (newElement > last.getElement()) { 		//If last element is less, then newElement is added last.
						newSetElement = new SetElement().setElement(newElement, nullElement);
						s = last.setNext(newSetElement);
						last = newSetElement;
						size = size + 1;
					} else {
						if (newElement > first.getElement() && newElement < last.getElement()) {
							//If newElement is not in the end points and possibly exists/shall be added somewhere in the middle of the list.
							//newElement is greater than the first element and smaller than the last element in the list.

							previous = first;
							current = first.getNext();
							while (!(current.getElement() >= newElement)) {
								previous = current;
								current = current.getNext();
							}

							//newElement <= currentElement. If newElement < current then newElement is inserted.
							if (current.getElement() != newElement) {
								newSetElement = new SetElement().setElement(newElement, current);
								s = previous.setNext(newSetElement);
								size = size + 1;
							}
						}
					}
				}
			}
		}

		return this;
	}

	public SetElement getFirst() {
		return first;
	}

	public int getSize() {
		return size;
	}

	public Set clear() {
		first = nullElement;
		last = nullElement;
		size = 0;

		return this;
	}

	public boolean contains(int element) {
		boolean result;
		SetElement current;

		//Empty set.
		if (size == 0)
			result = false;
		else {
			//Singleton set.
			if (size == 1) {
				if (first.getElement() == element)
					result = true;
				else
					result = false;
			}
			//Large set.
			else {
				//If element is at the end points.
				if (first.getElement() == element || last.getElement() == element)
					result = true;
				else {
					//If element is not in the range.
					if (first.getElement() > element || last.getElement() < 0)
						result = false;
					//If element is in range.
					else {
						current = first;
						while (current.getElement() < element)
							current = current.getNext();

						//newElement <= currentElement. If newElement = current then true is returned.
						if (current.getElement() == element)
							result =  true;
						else
							result =  false;
					}
				}
			}
		}

		return result;
	}
	
	public SetElement getNullElement() {
		return nullElement;
	}

	/*
	 * Expands this set with other: this_post = this_pre ∪ other.
	 */
	public Set union(Set other) {
		SetElement currentOtherElement;
		Set s;
		currentOtherElement = other.getFirst();
		
		while (currentOtherElement != other.getNullElement()) {
			s = this.addElement(currentOtherElement.getElement());
			currentOtherElement = currentOtherElement.getNext();
		}

		return this;
	}

	public Set printSet() {		
		SetElement element;
		
		System.out.println(size);
		element = first;
		
		while (element != nullElement) {
			System.out.println(element.getElement());
			element = element.getNext();
		}
		
		return this;
	}
}

class SetElement {
	SetElement next;
	int element;

	public SetElement setElement(int e, SetElement n) {
		element = e;
		next = n;
		return this;
	}

	public int getElement() {
		return element;
	}

	public SetElement setNext(SetElement n) {
		next = n;
		return next;
	}

	public SetElement getNext() {
		return next;
	}
}

