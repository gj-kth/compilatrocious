class main {
    public static void main(String[] args) {
        int a;
        Other o;
        o = new Other();
        a = o.wrong_return();
    }

    
}

class Other{
	public int wrong_return() {
        return true;
    }
}
