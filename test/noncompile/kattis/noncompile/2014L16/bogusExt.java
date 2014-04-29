//EXT:ICG
//EXT:ISC
class bogusExt {

//asdasda
	
	public static void main(String[] args) {
		/*System.out.println(true);*/
		little l;
		big b;
		
		
		l = new little();
		b = new big();
		l = b;
		b=l;
		
	}

}

class little{
	int b;
}

class big extends little{
	int j;
}
