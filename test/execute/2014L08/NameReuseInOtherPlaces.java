class NameReuseInOtherPlaces {
	public static void main(String[] args) {
		System.out.println(new DEF().ABC(3) + new DEF().DEF(4));
	}
}

class ABC {

}

class DEF {
	int ABC;
	int DEF;
	
	public int ABC(int ABC) {
		return ABC;
	}
	public int DEF(int DEF) {
		return this.ABC(DEF);
	}
}
