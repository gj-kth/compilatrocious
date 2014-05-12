// EXT:CEQ

class LinSearch {
    public static void main(String[] args) {
        int[] array;
        LinearSearch2 ls;
        array = new int[10];
        array[0] = 7;
        array[1] = 9;
        array[2] = 5;
        array[3] = 4;
        array[4] = 7;
        array[5] = 8;
        array[6] = 16;
        array[7] = 32;
        array[8] = 14;
        array[9] = 0;

        ls = new LinearSearch2();
        System.out.println(ls.search(array, 14));
        System.out.println(ls.search(array, 7));
        System.out.println(ls.search(array, 0));
        System.out.println(ls.search(array, 5));
    }
}

class LinearSearch2 {
    public int search(int[] array, int value) {
        int pos;
        int ret;
        pos = 0;
        ret = 0;
        while (pos < array.length) {
            if (array[pos] == value) {
                ret = pos;
                pos = array.length;
            } else {
                pos = pos + 1;
            }
        }
        return ret;
    }
}