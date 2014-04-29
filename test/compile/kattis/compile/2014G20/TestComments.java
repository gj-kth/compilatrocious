class TestComments { 
	public static void main ( String [] args ) {
		int a;
		CommentClass b;
		a = 1;
		//Calculator b;
		a = b.add(2+a, a);

		/*if(a < b) {
			System.out.println(a < b);
		} */
		/** * **/
		if(a < a) {
			System.out.println(a < a);
		} else {

		}
	}
}

class CommentClass {
	public int add(int a, int b) {
		//return a + b;
		if((a + b) < (a * b)) {
			/*
			if(a < b) {
				System.out.println(a == b);
			} else {
				System.out.println(false);
			}
			*/
			a = a + b;
		} else {
			
		}
		return a;
		//return a+b;
	}
}
