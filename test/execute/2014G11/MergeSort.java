class MergeSort {
	public static void main(String[] args) {
		int i;
		int size;
		int[] array;
		MergeSorter merge;

		size = 100;
		array = new int[size];
		merge = new MergeSorter();

		array[0] = 17066;
		array[1] = 14828;
		array[2] = 29913;
		array[3] = 1944;
		array[4] = 6306;
		array[5] = 28776;
		array[6] = 16374;
		array[7] = 20717;
		array[8] = 8872;
		array[9] = 17916;
		array[10] = 17172;
		array[11] = 3056;
		array[12] = 16458;
		array[13] = 25482;
		array[14] = 6855;
		array[15] = 6969;
		array[16] = 29368;
		array[17] = 5623;
		array[18] = 13028;
		array[19] = 18385;
		array[20] = 8875;
		array[21] = 3293;
		array[22] = 6389;
		array[23] = 12958;
		array[24] = 15675;
		array[25] = 30351;
		array[26] = 9851;
		array[27] = 25958;
		array[28] = 6325;
		array[29] = 5566;
		array[30] = 7846;
		array[31] = 22758;
		array[32] = 30327;
		array[33] = 10708;
		array[34] = 21555;
		array[35] = 30147;
		array[36] = 24213;
		array[37] = 17329;
		array[38] = 19357;
		array[39] = 25154;
		array[40] = 3770;
		array[41] = 32455;
		array[42] = 15694;
		array[43] = 28558;
		array[44] = 24939;
		array[45] = 14906;
		array[46] = 23603;
		array[47] = 11204;
		array[48] = 21458;
		array[49] = 7116;
		array[50] = 5069;
		array[51] = 3960;
		array[52] = 7965;
		array[53] = 24034;
		array[54] = 13730;
		array[55] = 8642;
		array[56] = 25855;
		array[57] = 21602;
		array[58] = 28457;
		array[59] = 980;
		array[60] = 28829;
		array[61] = 28147;
		array[62] = 30566;
		array[63] = 32754;
		array[64] = 31575;
		array[65] = 3605;
		array[66] = 14962;
		array[67] = 5825;
		array[68] = 5127;
		array[69] = 5780;
		array[70] = 2470;
		array[71] = 20854;
		array[72] = 27426;
		array[73] = 25660;
		array[74] = 14484;
		array[75] = 8406;
		array[76] = 3345;
		array[77] = 2197;
		array[78] = 10161;
		array[79] = 4441;
		array[80] = 4775;
		array[81] = 26646;
		array[82] = 20905;
		array[83] = 1443;
		array[84] = 20015;
		array[85] = 12558;
		array[86] = 11206;
		array[87] = 6359;
		array[88] = 8291;
		array[89] = 6261;
		array[90] = 29784;
		array[91] = 30364;
		array[92] = 7315;
		array[93] = 20374;
		array[94] = 12212;
		array[95] = 9365;
		array[96] = 32713;
		array[97] = 4471;
		array[98] = 31317;
		array[99] = 8084;

		i = merge.sort(array);

		i = 0;
		while(i < size) {
			System.out.println(array[i]);
			i = i + 1;
		}
	}
}

class MergeSorter{

	public int sort(int[] array) {
		int nil;
		nil = this.divide(array, 0, array.length-1);
		return 0;
	}

	public int divide(int[] array, int low, int high) {
		int i;
		int temp;
		int middle;
		boolean skip;

		if(low < high) {
			temp = low + high;
			/*
 			 * Here follows ugly code for dividing by 2
 			 */
			i = 0;
			middle = 0;
			skip = false;
			while(i < temp) {
				if(skip) {
					middle = middle + 1;
					skip = !skip;
				}else{
					skip = !skip;
				}
				i = i + 1;
			}
			/*
 			 * End of divide by 2 code
 			 * middle should now be approximately (low+high)/2
 			 */

			temp = this.divide(array, low, middle);
			temp = this.divide(array, middle+1, high);

			temp = this.merge(array, low, middle, high);

		}else{
			/*
 			 * Do nothing
 			 */
		}
		return 0;
	}

	public int merge(int[] array, int low, int middle, int high) {
		int i;
		int tmp;
		Queue q1;
		Queue q2;

		q1 = new Queue();
		q2 = new Queue();

		tmp = q1.init(middle-low + 1);
		tmp = q2.init(high-(middle+1) + 1);

		i = low;
		while(i < middle+1) {
			tmp = q1.enqueue(array[i]);
			i = i + 1;
		}

		i = middle + 1;
		while(i < high+1) {
			tmp = q2.enqueue(array[i]);
			i = i + 1;
		}

		i = low;
		while(q1.hasElement() && q2.hasElement()) {
			if(q1.peek() < q2.peek()+1) {
				array[i] = q1.dequeue();
			}else{
				array[i] = q2.dequeue();
			}
			i = i + 1;
		}

		while(q1.hasElement()) {
			array[i] = q1.dequeue();
			i = i + 1;
		}

		while(q2.hasElement()) {
			array[i] = q2.dequeue();
			i = i + 1;
		}

		return 0;
	}

}

class Queue{
	int[] queue;
	int front;
	int back;

	public int init(int size) {
		front = 0;
		back = 0;
		queue = new int[size];
		return 0;
	}

	public int enqueue(int element) {
		queue[back] = element;
		back = back + 1;
		return back;
	}

	public int dequeue() {
		int retval;
		retval = queue[front];
		front = front + 1;
		return retval;
	}

	public int peek() {
		return queue[front];
	}

	public boolean hasElement() {
		boolean retval;
		if((back-1) < front) {
			retval =  false;
		}else{
			retval =  true;
		}
		return retval;
	}

}
