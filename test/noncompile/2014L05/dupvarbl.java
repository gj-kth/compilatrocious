// EXT:NBD

class dupvarbl {
    public static void main(String [] str) {
        int c;

        c = 0;
        {
            int c;
            c = 1;
        }
        System.out.println(c);
    }
}
