// EXT:CEQ

class Main {
	public static void main(String[] args) {
		ArrayList l;
		int i;

		l = new ArrayList().init();

		System.out.println(l.size());
		System.out.println(l.cap());

		i = 0;
		while (i < 5) {
			l = l.add(i + 7);
			i = i + 1;
		}

		System.out.println(l.size());

		while (0 < i) {
			i = i - 1;
			System.out.println(l.get(i));
		}
	}
}

class ArrayList {
	int[] arr;
	int s;

	public ArrayList init() {
		s = 0;
		arr = new int[10];
		return this;
	}

	public ArrayList add(int val) {
		ArrayList _;
		int[] arr_old;
		int i;

		if (s < arr.length) {
			arr[s] = val;
			s = s + 1;
		} else {
			arr_old = arr;
			i = 0;
			arr = new int[arr_old.length * 2];

			while (i < arr_old.length) {
				arr[i] = arr_old[i];
				i = i + 1;
			}

			_ = this.add(val);
		}

		return this;
	}

	public int size() {
		return s;
	}

	public int cap() {
		return arr.length;
	}

	public boolean isEmpty() {
		return s == 0;
	}

	public int get(int i) {
		return arr[i];
	}

	public int set(int i, int val) {
		int old;

		old = arr[i];
		arr[i] = val;

		return old;
	}
}
