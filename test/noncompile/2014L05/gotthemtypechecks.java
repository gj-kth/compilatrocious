class multidim {
    public static void main(String [] str) {
        int [] a;
        a = new int[10];
        if (a) {} else {}
        System.out.println(a);
        //a[0] = a[3][4];
        //a[0] = new int[10];//[3] = new x().z();
    }
}

class x {
    int [] a;

    public int [] z(int [] inp) { 
        a = new int[10];
        //a[0] = inp;
        return a;
    }


}
