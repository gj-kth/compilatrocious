/* Tries to do array lookup on non int array type */

class Main
{
    public static void main(String[] s) 
    {
        int a;
        boolean b;
        int c;
        int x;

        a = 1;
        x = 2;
        b = true;
        c = a + b;

        System.out.println(1);
    }
}

class Other {
    int a;
    boolean b;
    int c;

    public int c() {
        a = 1;
        b = true;
        c = a + b;
        return a;
    }

    public int v() {
        int y;
        boolean z;
        int[] array;
        array = new int[8];
        y[0] = 2; // Invalid
        z[7] = 3; // Invalid
        z[3] = false; // Invalid
        return y[7]; // Invalid
    }
}
