/*
	MiniJava-implementation of the mergesort algorithm.
    The implementation uses only the base MiniJava-language.
*/
class SortingTest {
    public static void main(String[] args) {
        MergeSort sorter;
        int[] test_list;
        int i;

        sorter = new MergeSort();
        test_list = new int[14];
        test_list[0] = 98; test_list[1] = 114;
        test_list[2] = 3; test_list[3] = 98;
        test_list[4] = 55; test_list[5] = 62;
        test_list[6] = 99; test_list[7] = 115;
        test_list[8] = 4; test_list[9] = 97;
        test_list[10] = 51; test_list[11] = 111;
        test_list[12] = 50; test_list[13] = 100;

        test_list = sorter.merge_sort(test_list);
        i = 0;
        while(i < test_list.length) {
            System.out.println(test_list[i]);
            i = i + 1;
        }
    }
}

/*
    The MergeSort-class, which contains the merge_sort function.
    It sorts an int-array by recursively sorting two sub-arrays of it,
    where the length of each sub-array is the length of the original array
    divided by two.
*/
class MergeSort {

    /*
        Sort the int-array 'list' by recursively sorting two sub-arrays of it,
        and then merging them into a complete sorted array.
    */
    public int[] merge_sort(int[] list) {
        int divide_index;
        int[] sublist1;
        int[] sublist2;
        int[] sorted_list;
        boolean fill_successful;
        if(1 < list.length) {
            divide_index = this.get_divide_index(list);

            sublist1 = new int[divide_index];
            sublist2 = new int[list.length - divide_index];
            fill_successful = this.fill_sublists(list, sublist1, sublist2);

            sublist1 = this.merge_sort(sublist1);
            sublist2 = this.merge_sort(sublist2);

            sorted_list = this.merge(sublist1, sublist2);
        } else {
            sorted_list = list;
        }
        return sorted_list;
    }

    /*
        Fill the two sublist-arrays with the elements of the original array.
    */
    public boolean fill_sublists(int[] list, int[] sublist1, int[] sublist2) {
        int i;
        int j;
        int x;
        i = 0;
        j = 0;
        x = 0;
        while(i < sublist1.length) {
            sublist1[i] = list[x];
            i = i + 1;
            x = x + 1;
        }
        while(j < sublist2.length) {
            sublist2[j] = list[x];
            j = j + 1;
            x = x + 1;
        }
        return x < list.length && !(x + 1 < list.length);
    }

    /*
        Compute the index which is half of the arrays length.
    */
    public int get_divide_index(int[] list) {
        int i;
        i = 0;
        while(i * 2 < list.length) {
            i = i + 1;
        }
        return i;
    }

    /*
        Linearly sort together the two sub-arrays into one sorted array.
    */
    public int[] merge(int[] sublist1, int[] sublist2) {
        int[] merged_list;
        int i;
        int j;
        int x;

        merged_list = new int[sublist1.length + sublist2.length];
        i = 0;
        j = 0;
        x = 0;

        while(i < sublist1.length && j < sublist2.length) {
            if(sublist1[i] < sublist2[j]) {
                merged_list[x] = sublist1[i];
                i = i + 1;
            } else {
                merged_list[x] = sublist2[j];
                j = j + 1;
            }
            x = x + 1;
        }

        if(i < sublist1.length) {
            while(i < sublist1.length) {
                merged_list[x] = sublist1[i];
                x = x + 1;
                i = i + 1;
            }
        } else {
            if(j < sublist2.length) {
                while(j < sublist2.length) {
                    merged_list[x] = sublist2[j];
                    x = x + 1;
                    j = j + 1;
                }
            } else {
                //The two arrays were of equal size.
            }
        }

        return merged_list;
    }
}