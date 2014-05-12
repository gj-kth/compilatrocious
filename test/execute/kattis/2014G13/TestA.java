
class TestA {
	
	public static void main(String[] args){
		int a;
		int b;
		int c;
		int counter;
		TestSum d;
		boolean e;
		boolean f;
		int[] arr;
		
		arr = new int[7];
		d = new TestSum();
		a = 3;
		b = 5;
		c = d.sum(a, b);
		e = true;
		f = true;
		
		if(a < b){
			f = false;
			arr[0] = a;
		}
		else{
			e = false;
			arr[0] = b;
		}
		
		if(!(c < 8) && !(8 < c) && e){
			arr[1] = 17;
		}
		else{
			arr[1] = 3;
		}
		
		arr[2] = arr[1] + arr[0];
		arr[3] = arr[2] - arr[1];
		arr[4] = arr[2] + a * b - c;
		arr[5] = arr[2] + a * (b - c);
		
		if(!!true){
			arr[6] = 11;
		}else{
			arr[6] = 3;
		}
		
		counter = 0;
		while(counter < arr.length){
			System.out.println(arr[counter]);
			counter = counter + 1;
		}
	}
}

class TestSum {
		
	public int sum(int a, int b){
		int c;
		c = a+b;
		return c;
	}
	
}

