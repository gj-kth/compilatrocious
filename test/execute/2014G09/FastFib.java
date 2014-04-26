//EXT:IWE

class FastFib {
	public static void main(String[] args) {
		Fib fib;
		int f;
		int i;
		
		fib = new Fib();
		f = fib.init();
		f = 0;
		while(47 > f) {
			System.out.println(fib.fib(f));
			f = f + 1;
		}
		f = fib.setModulo(32768);
		f = 1;
		i = 0;
		while( i < 10  ){
			System.out.println(fib.fib(f));
			f = f * 10;
			i = i + 1;
		}
	}
}

class Fib {
	BitSet bs;
	boolean modular;
	int modulo;


	public int init() {
		int i;
		bs = new BitSet();
		i = bs.init();
		modular = false;
		modulo = 0;
		return 0;
	}
	public int setModulo(int i){
		modular = true;
		modulo = i;
		return 0;
	}
	public int resetModulo(){
		modular = false;
		return 0;
	}

	public int fib(int n) {
		int index;
		int bitCount;
		boolean test;
		int[] m1;
		int[] m2;

		index = 0;
		m1 = new int[4];
		m2 = new int[4];
		bitCount = bs.repNumber(n);

		m1[0] = 1;
		m1[1] = 1;
		m1[2] = 1;
		m1[3] = 0;

		m2[0] = 1;
		m2[1] = 0;
		m2[2] = 1;
		m2[3] = 0;

		if(! modular){
			while(index > 0-1 && bitCount > 0) {
				test = bs.test(index);
				if(test) {
					m2 = this.mmult(m2, m1);
					bitCount = bitCount - 1;
				}
				m1 = this.mmult(m1, m1);
				index = index + 1;
			}
		} else {
			while(index > 0-1 && bitCount > 0) {
				test = bs.test(index);
				if(test) {
					m2 = this.mmult_m(m2, m1);
					bitCount = bitCount - 1;
				}
				m1 = this.mmult_m(m1, m1);
				index = index + 1;
			}
		}

		return m2[1];
	}


	public int mod(int a, int b){
		int[] bpow;
		int mod;
		int i;
		int last;
		int bp;

		bpow = new int[32];

		i = 0;
		bp = b;
		last = 0;
		while(bp > last){
			bpow[i] = bp;
			last = bp;
			bp = bp * b;
			i = i+1;
		}
		i = i - 1;
		while( i > 0-1 ){
			while( a - bpow[i] > 0-1) {
				a = a - bpow[i];
			}
			i = i - 1;
		}
		return a;
	}

	public int[] mmult(int[] m1, int[] m2) {
		int[] res;
		res = new int[4];
		res[0] = m1[0]*m2[0] + m1[1]*m2[2];
		res[1] = m1[0]*m2[1] + m1[1]*m2[3];
		res[2] = m1[2]*m2[0] + m1[3]*m2[2];
		res[3] = m1[2]*m2[1] + m1[3]*m2[3];
		return res;
	}
	public int[] mmult_m(int[] m1, int[] m2) {
		int[] res;
		res = new int[4];
		res[0] = this.mod(this.mod(m1[0]*m2[0], modulo) + this.mod(m1[1]*m2[2], modulo), modulo);
		res[1] = this.mod(this.mod(m1[0]*m2[1], modulo) + this.mod(m1[1]*m2[3], modulo), modulo);
		res[2] = this.mod(this.mod(m1[2]*m2[0], modulo) + this.mod(m1[3]*m2[2], modulo), modulo);
		res[3] = this.mod(this.mod(m1[2]*m2[1], modulo) + this.mod(m1[3]*m2[3], modulo), modulo);
		return res;
	}
}

class BitSet {
	int[] pow2;
	int[] set;
	
	public int init() {
		int p2;
		int index;

		pow2 = new int[32];
		p2 = 1;
		index = 0;
		while(31 > index) {
			pow2[index] = p2;
			index = index+1;
			p2 = p2*2;
		}
		return 0;
	}

	public int repNumber(int n) {
		int index;
		int bitcount;
		set = new int[32];

		index = 30;
		bitcount = 0;
		while(n > 0 && index > (0-1)) {
			if(n > pow2[index]-1) {
				bitcount = bitcount + 1;
				set[index] = 1;
				n = n - pow2[index];

			}
			index = index - 1;
		}
		return bitcount;
	}

	public boolean test(int index) {
		return set[index] > 0;
	}
}
