// EXT:CEQ

/// Tests that variables are not declared twice or more, also that no undeclared variables
/// are used.
///
/// @version 25-02-2014
class Declaration{

	public static void main(String[] args){
		Torbjoern t;
		int result;
		result = 0;
		t = new Torbjoern();
		result = t.torbjoern(result, true);
	}
}

class Torbjoern{
	
	int y;
	
	public int torbjoern(int hej, boolean b){
		// Duplicate declaration of var hej
		int hej;
		A a;
		// Duplicate declaration of var b
		B b;
		// Non-existing class C
		C c;
		
		// Undeclared variables
		fika = saft + bullar;
		
		hej = saft + bullar;
		
		// Non-matching types in assignment
		a = b;
		
		// Non-matching types in comparison
		if(a == b){
		
		}
		else{
		
		}
		
		return hej;
	}

}

class A{

}

class B{

}
