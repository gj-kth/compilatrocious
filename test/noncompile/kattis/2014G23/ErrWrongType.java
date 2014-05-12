/* Wrong type errors */
// EXT:CEQ
// EXT:CNE

class Main {
    public static void main(String[] args) {
        int a;
        boolean b;
        int c;
        int _d;
        int x;
        Objection obj;

        a = 1;
        x = 2;
        b = true;
        c = a + b; // Wrong type
        obj = new Objection().object();

        // Wrong type
        if(1 == true) {
            x = 0;
        } else {
            x = 24;
        }

        // Wrong type
        if(false != 3) {
        } else {}

        System.out.println(1);
    }
}

class Objection {
    boolean incorrect;

    public Objection object() {
        incorrect = true;
        return this;
    }
}
