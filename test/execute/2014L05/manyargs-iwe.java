// EXT:IWE
// EXT:SPILL

class manyargs {
    public static void main(String [] str) {
        int [] arr;
        hello x;

        arr = new int[10];
        arr[0] = 1;
        arr[1] = 2;
        arr[2] = 0-3;
        arr[3] = 14;
        arr[4] = 523;
        arr[5] = 0-11;
        arr[6] = 6;
        arr[7] = 66;
        arr[8] = 128;
        arr[9] = 14;

        x = new hello();

        System.out.println(x.spew(1,2,3,4, 5,6,7,8, true, false, false, true, arr));
        // should be 1098 (as returned by a compile and run through
        // Sun Java)
    }
}

class hello {
    public int spew(int a, int b, int c, int d, int e, int f, int g, int h, boolean i, boolean j, boolean k, boolean l, int [] m) {
        a = a + e + c;
        if (i) { a = a * 2; } 
        a = a + d * b;
        if (j) { a = a * 2; } 
        a = a + f + g + h;
        if (k) { a = a * 2; } 
        a = a + b + c + d + e + f + g + h + g + f + e + d + c + b + a;
        if (l) { a = a * 2; } 
        b = m.length;
        g = 0;
        while (g < b) {
            a = a + m[g];
            g = g + 1;
        }
        return a + b + c + d + e + f + g + h;
    }
}
