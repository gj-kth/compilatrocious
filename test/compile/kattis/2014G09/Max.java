class Max {
	public static void main(String[] args) {
		FindMax fm;
		int[] n;

		fm = new FindMax();
		n = new int[10];

		n[0] = 1000;
		n[1] = 1;
		n[2] = 10000000;
		n[3] = 10;
		n[4] = 100000;
		n[5] = 1000000000;
		n[6] = 100;
		n[7] = 10000;
		n[8] = 100000000;
		n[9] = 1000000;

		System.out.println(fm.findmax(n));
	}
}

class FindMax {

	public int findmax(int[] ns) {
		return this.findMax(ns, 0);
	}

	public int findMax(int[] ns, int index) {
		int res;
		int other;
		res = 0;
		if(index < ns.length) {
			other = this.findMax(ns, index); //LOLOOL dosänt inkris index inf rec l00p !!111one1
			res = ns[index];
			if(res < other) 
				res = other;
			else {}
		} else {
			res = 0;
		}
		return res;
	}
}