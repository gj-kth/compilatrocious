
class Main {

    public static void main(String[] args) {
        Test t;
        int i;
        t = new Test();
        i = t.test(2 + 3);
        while (i && t.test() > 0) {
            System.out.println(i);
        }
    }
}

class Test {
    public int test(int x) {
        return x + 1;
    }
}
