// EXT:CEQ

class Factorial {
    public static void main(String [] str) {
        Test a;
        int x;
        int [] tfv;
        a = new Test();
        x = a.test();

        if (a.tfone() == 1) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }

        if (a.tftwo() == 2) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }

        if (a.b()) {
            x = 1;
        } else {
            x = 0;
        }
        System.out.println(x);

        tfv = a.getTF();
        System.out.println(tfv[0]);
        System.out.println(tfv[1]);
        System.out.println(tfv[2]);

        System.out.println(a.getTF()[5]);
    }
}

class Test {
    int [] tf;
    public int tfone() {
        return tf[0];
    }
    public int tftwo() {
        return tf[1];
    }
    public int [] getTF() {
        return tf;
    }
    public boolean b() {
        return tf[0] < tf[1];
    }
    public int test() {
        tf = new int[10];
        tf[0] = 1;
        tf[1] = 2;
        return 10;
    }
}
