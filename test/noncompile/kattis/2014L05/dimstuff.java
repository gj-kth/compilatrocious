class foomain {
    public static void main(String [] str) {
        int [] a;
        int [] b;

        a = new int[10];
        b = new int[a];
        a[3] = new x().hop();
        b[4] = a[2];
    }
}

class x {

    public int hop() {
        return 4;
    }
}
