class Main {
    public static void main(String[] a) {
    	FieldParamAndVar f;

    	f = new FieldParamAndVar().init();

    	System.out.println(f.foo(1));
    	System.out.println(f.var());
    }
}

class FieldParamAndVar {
	int field;

	public FieldParamAndVar init() {
		field = 0;
		return this;
	}

	public int foo(int field) {
		return field;
	}

	public int var() {
		int field;
		field = 2;
		return field;
	}
}
