class ____ {

	public static void main(String[] args) {
		t1 t;

		t = new t1();
		System.out.println(t.get().get().get().get().get().get().get()
				.seti(555));
		System.out.println(t.get().get().get().get().get().get().get().geti() + 5);

	}
}

class t1 {
	t2 t2;

	public t2 get() {
		t2 = new t2();
		return t2;
	}

}

class t2 {
	t3 t3;

	public t3 get() {
		
		boolean b;
		
		b = true;
		
		if(b){
		t3 = new t3();
		}else{
			
			b = false;
		}
		
		return t3;
	}
}

class t3 {
	t4 t4;
	t4 t2;
	public t4 get() {
		
		if(1+1-22 < 2-1){
			t4 = new t4();
		}
		else{
			
			t2 = new t4();
		}

		return t4;
	}
}

class t4 {
	t5 t5;

	public t5 get() {
		boolean boo;
		
		boo = true;
		t5 = new t5();
		while(boo){
			boo = false;
		}
		return t5;
	}
}

class t5 {
	t6 t6;

	public t6 get() {
		t6 = new t6();

		return t6;
	}
}

class t6 {
	t7 t7;

	public t7 get() {
		t7 = new t7();

		return t7;
	}
}

class t7 {
	t8 t8;

	public t8 get() {
		t8 = new t8();

		return t8;
	}
}

class t8 {
	int i;

	public int seti(int ii) {
		i = ii;
		return i;
	}

	public int geti() {
		i = i + 55 + 55 + 55 + 55 + 5*2;
		return i-2;
	}

}
