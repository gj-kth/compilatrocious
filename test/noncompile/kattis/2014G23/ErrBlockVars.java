/*
 * Testing of variables in blocks.
 * Should return 4 different errors.
 * Assumes variable declarations is allowed in blocks (extension).
 **/
// EXT:NBD

class Test {
    public static void main(String[] args) {
        int a;
        a = 5;
        if(a < 5) {
            int i;
            int a; // a is already declared outside the block
        } else {
            int j;
            i = 1; // i is declared in another block
        }
        j = 2; // j is declared inside a block

        while(a < 1) {
            int b;
            b = 3;
            if(b < 1) {
                int b; // b is defined in outer block
            } else {
                b = b - 1; // SHOULD BE LEGAL
                if(b < 2) {
                    b = b - 1; // SHOULD BE LEGAL
                } else {
                    // DO NOTHING
                }
            }
            a = a - 1;
        }
    }
}
