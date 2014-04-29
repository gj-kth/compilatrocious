class ____ {

	public static void main(String[] args) {
		
		int i;
		int j;
		int k;
		int kl;
		boolean booo;
		boolean v;
		boolean v2;
		boolean v3;
		boolean v4;
		MissingMEthod mm;
		
		i = 1;
		j = i+1;
		k = i + j + 1;
		kl = i + j + k + 1;
		
		v = false;
		v2 = v && true;
		v3 = v2 && (kl < i+2);
		mm = new MissingMEthod();
		v4 = mm.init___();
		
		System.out.println(v4);
		
		
		
	}

}

class MissingMEthod{
	
	public boolean init__(){
		return true&&false&&(1<22);
	
}
}
