/* Invalid blocks according to spec, 11 errors */
// EXT:NBD

class InvalidBlock {
    public static void main(String[] args) {
        int i;
        int u;
        i = 1;
        u = i - 0;
        while(i < 10) {
            int i; // Already defined
            i = i + 1;
            a = i * 2; // Not declared
        }
    }
}

class Help {
    int u;

    public int helper() {
        u = 3;
        while(false) {
            int u; // Allowed to override
            u = u;
        }
        return u;
    }
}

class Hello {
    int b;
    int c;
    int t;

    public int testMethod() {
        b = 123;
        while(false) {
            int a;
            int z;
            b = 12;
            a = 3;
            u = 123; // Not declared
            while(false) {
                int a; // Already defined
                int z; // Already defined
                b = 13;
                z = b + 2;
                while(false) {
                    int a; // Already defined
                    int b; // Allowed to override
                    int u;
                    int t; // Allowed to override
                    u = a + b;
                    t = 3;
                }
            }
        }
        return 0;
    }
}

class Classy {
    int argv;

    public boolean withParams(int a, boolean b, int[] c, Hello d) {
        int a; // Already declared
        int b; // Already declared
        int c; // Already declared

        while(false) {
            boolean a; // Already defined
            a = 3;
            while(false) {
                int d; // Already defined
                while(false) {
                    d = new Hello();
                }
            }
        }

        return false;
    }
}
