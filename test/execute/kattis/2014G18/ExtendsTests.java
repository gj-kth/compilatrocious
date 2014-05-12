// EXT:ISC
// EXT:ICG
// EXT:IWE

class Main {
	
	public static void main(String[] args){
		Test test;
		Base base;
		Base base2;
		Base base3;
		Extension extension;
		Top top;

		test = new Test();
		base = new Base();
		base2 = new Extension();
		base3 = new Top();
		extension = new Extension();
		top = new Top();


		// Test method accesses
		// Expected: 1, 1, 1, 3, 3
		System.out.println(base.getLevel());
		
		System.out.println(base2.getLevel());
		System.out.println(extension.getLevel());

		System.out.println(base3.getLevel());
		System.out.println(top.getLevel());

		// Test function calls 
		// Expected: 10, 10, 10, 30, 30
		System.out.println(test.doTest(base));

		System.out.println(test.doTest(base2));
		System.out.println(test.doTest(extension));

		System.out.println(test.doTest(base3));
		System.out.println(test.doTest(top));

		// Test variable access
		// Expected: 1, 2, 3, 1, 3, 6

		System.out.println(base.getX());
		System.out.println(extension.getY());
		System.out.println(top.getZ());

		System.out.println(base.getWeight());
		System.out.println(extension.getWeight());
		System.out.println(top.getWeight());

		// Expected: 1, 3, 3, 7, 0
		System.out.println(test.testIfWithoutElse());
	}

}


class Test {

	public int doTest(Base base){
		return base.getLevel() * 10;
	}

	public int testIfWithoutElse(){

		int x;

		x = 20;

		if(x < 21){
			x = 4;
		}

		if(x < 3){
			System.out.println(100);
		} else {
			System.out.println(1);
		}

		if(true){
			if(false){
				System.out.println(200);
			} 
			if(true){
				System.out.println(3);
				if(true){
					System.out.println(3);
				}
			} else {
				System.out.println(300);
			}
			if(true){
				System.out.println(7);
			} else {
				System.out.println(400);
			}
		}

		return 0;


	}
}

class Base {

	int x; 

	public int getLevel(){
		return 1;
	}

	public int getX(){
		x = 1;
		return x;
	}

	public int getWeight(){
		return x;
	}



}

class Extension extends Base {

	int y;

	public int getY(){
		x = this.getX();
		y = 2;
		return y;
	}

	public int getWeight(){
		return x + y;
	}

}


class Top extends Extension {

	int z;

	public int getLevel(){
		return 3;
	}

	public int getZ(){
		x = this.getX();
		y = this.getY();
		z = 3;
		return z;
	}

	public int getWeight(){
		return x + y + z; 
	}


}







