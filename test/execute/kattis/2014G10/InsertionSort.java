class InsertionSort {
	public static void main(String[] args) {
		int[] ans;
		int[] foo;
		Sort s;

		foo = new int[10];
		foo[0] = 9;
		foo[1] = 8;
		foo[2] = 5;
		foo[3] = 6;
		foo[4] = 2;
		foo[5] = 4;
		foo[6] = 3;
		foo[7] = 7;
		foo[8] = 1;
		foo[9] = 0;

		s = new Sort();
		ans = s.sortArray(foo);

		System.out.println(ans[0]);
		System.out.println(ans[1]);
		System.out.println(ans[2]);
		System.out.println(ans[3]);
		System.out.println(ans[4]);
		System.out.println(ans[5]);
		System.out.println(ans[6]);
		System.out.println(ans[7]);
		System.out.println(ans[8]);
		System.out.println(ans[9]);
	}
}

class Sort {
	public int[] sortArray(int[] array) {
		int i;
		int j;
		int tmp1;
		int tmp2;
		int[] ret;
		boolean noBreak;

		ret = new int[array.length];
		noBreak = true;

		i = 0;
		while (i < array.length) {
			j = 0;
			while (j < i && noBreak) {
				if (array[i] < ret[j]) {
					noBreak = false;
					tmp1 = ret[j];
					ret[j] = array[i];
					j = j + 1;
					while (!(i < j)) {
						tmp2 = tmp1;
						tmp1 = ret[j];
						ret[j] = tmp2;
						j = j + 1;
					}
				} else {}
				j = j + 1;
			}
			if (noBreak) {
				ret[j] = array[i];
			} else {
				noBreak = true;
			}
			i = i + 1;
		}
		return ret;
	}
}
