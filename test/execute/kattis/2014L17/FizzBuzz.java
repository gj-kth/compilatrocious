/*
 * FizzBuzz without strings!
 */

class Main1 {
    public static void main(String[] a) {
        FizzBuzz fizzbuzzer;
        int i;
        fizzbuzzer = new FizzBuzz();
        i = 1;

        while (i < 100) {
            if (fizzbuzzer.test(i, 15)) {
                System.out.println(51228022);
            }
            else if (fizzbuzzer.test(i, 5)) {
                System.out.println(8022);
            }
            else if (fizzbuzzer.test(i, 3)) {
                System.out.println(5122);
            }
            else {
                System.out.println(i);
            }
            i = i + 1;
        }
    }
}

class FizzBuzz {

    public boolean test(int l, int r) {
        int v;
        v = this.modulo(l, r);
        return v < 1 && (0 - 1) < v;
    }
    
    public int modulo(int l, int r) {
        while (r < l + 1) {
            l = l - r;
        }
        return l;
    }
}
