//EXT:IWE
class IWE {
	public static void main(String[] argv){
		System.out.println((new Foo()).bar());		
	}
}
class Foo {
	public int bar(){
		int a;
		a = 1;
		if(false)
			if(true)
				a = 3;
		else // Should adhere to inner
			a=5;
		return a; //Therefore a  == 1 :)
	}
}