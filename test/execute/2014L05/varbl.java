// EXT:NBD

class varbl {
    public static void main(String [] str) {
        int c;

        c = 0;
        {
            int d;
            d = 1;
            System.out.println(d);
        }
        System.out.println(c);

        {
            int d;
            d = 2;
            System.out.println(d);
            {
                int e;
                e = 3;
                System.out.println(e);
            }
            {
                int e;
                e = 4;
                System.out.println(e);
            }
        }
        {
            int e;
            e = 5;
            System.out.println(e);
        }
    }
}
