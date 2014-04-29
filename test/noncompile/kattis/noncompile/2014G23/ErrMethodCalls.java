/* Invalid method calls */

class Main {
    public static void main(String[] a) {
        System.out.println(new Test().start());
    }
}

class Test {
    Test2 t2;
    Test3 t3;
    boolean f;
    int a;
    int b;

    public int start() {
        f = t2.m(1+2, false, t3);
        f = t2.m(false, 1 < 3, t2); // Type errors
        a = this.end(f); // Type error
        return 0;
    }

    public int end(Other a) {
        return 0;
    }
}

class Test2 {
    T2 t2s;

    public boolean m(int i, boolean b, Test3 T3) {
        while(false) {
            i = i + 1;
        }

        i = false; // Type error
        t2s = T3.hey(); // Type error

        return false;
    }
}

class Test3 {
    public int hey() {
        return 23423;
    }
}

