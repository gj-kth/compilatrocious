
class Main {

    public static void main(String[] args) {
        Test t;
        int i;
        t = new Test();
        i = t.test(2 + 3, 10);
        i = i + t.test2(2);
    }
}

class Test {
    public int test(int x) {
        x = this.test2(x);
        return x;
    }
    public int test2(int x, int y) {
        return (x + y) * 2;
    }
}
