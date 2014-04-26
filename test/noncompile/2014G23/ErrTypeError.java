/* Different type errors */

class Main {
    public static void main(String[] a) {
        System.out.println(new Test().start());
    }
}

class Test {
    int a;
    boolean b;
    Other c;

    public int start() {
        c = a + b; // Wrong type
        c = true; // Wrong type
        return 1;
    }

    public boolean starter() {
        return false;
    }
}

class Test2 {
    boolean x;
    public int v() {
        return x; // Wrong type
    }
}

class Other {
    int a;
    boolean b;

    public Other v() {
        return false; // Wrong type
    }
}
