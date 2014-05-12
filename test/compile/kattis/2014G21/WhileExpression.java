class WhileExpression{
	public static void main(String[] args) {
		int i;
		class1 c1;
		c1 = new class1();
		i = 0;
		while(c1.method(i)){
			i = i + 1;
		}
		System.out.println(i);
	}
}

class class1{
	public boolean method(int i) {
		boolean b;
		if(i < 10){
			b = true;
		} else {
			b = false;
		}
		return b;
	}
}
