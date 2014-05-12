
class compile1 {

	public static void main(String[] args) {
		boolean b;
		int a;
		bad c;

		b=true;
		a=2;
		c=new bad();

		if(b){
			a=c.test(a);
			System.out.println(a);
		}else{
			System.out.println(a);
		}
		b=!true;
		if(b){
			a=1-2;
		}else{
			b=!false;
		}
	}
}

class bad{
	public int test(int a){
		return 0;
	}
}