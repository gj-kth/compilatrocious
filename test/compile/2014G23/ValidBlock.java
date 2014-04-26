// EXT:NBD

class ValidBlock {
    public static void main(String[] args) {
        int i;
        int a;
        i = 1;
        while(i < 10) {
            i = i + 1;
            a = i * 2;
        }
    }
}

class A {
    int z;
    int x;

    public int method() {
        z = 3;
        x = 3;
        while(false) {
            z = z + 2;
            x = x + z;
        }
        return x;
    }
}

class B {
    int b;
    int c;

    public int method() {
        b = 123;
        while(false) {
            int u;
            b = 12;
            u = 3;
            while(false) {
                int v;
                b = 13;
                v = 0;
                while(false) {
                    int w;
                    w = c + b;
                }
            }
        }
        return 0;
    }
}
