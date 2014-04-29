class MainClass {
	public static void main (String[] args) {

		Element startE;
		Element e;
		int counter;
		
		counter = 0;
		startE = new Element();
		e = startE;
		
		while(counter < 6){
			e = e.addElement(new Element(), counter);
			System.out.println(counter);
			counter = counter + 1;
		}
		
		System.out.println(11111111);
		
		counter = 0;
		e = startE;
		System.out.println(e.getValue());
		
		while(counter < 5){
			e = e.getNextElement();
			System.out.println(e.getValue());
			counter = counter + 1;
		
		}
		
		
	}
}

class Element{
	Element nextE;
	int value;
	
	public Element getNextElement(){
		return nextE;
	}
	
	public Element addElement(Element e, int a){
		value = a;
		nextE = e;
		return nextE;
	}
	
	public int getValue(){
		return value;
	}
}
