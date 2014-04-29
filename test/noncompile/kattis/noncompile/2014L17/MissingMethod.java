
class Main {

    public static void main(String[] args) {
        Test t;
        int i;
        t = new Test();
        i = t.test(2 + 3);
    }
}

class Test {
    public int test(int x) {
        x = this.test2(x);
        return x;
    }
}
