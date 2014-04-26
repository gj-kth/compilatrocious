class Main {
    public static void main(String[] args) {
        int a;
        Foobar foo;
        a = 3;
        System.out.println(a); //3
        if (a < 4) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
        while (a < 10) {
            a = a+1;
        }
        System.out.println(a); //10

        System.out.println(!true); //false
        System.out.println(!!true); //true

        System.out.println((a-2)*3+2); //26

        foo = new Foobar();
        System.out.println(foo.lessThan5(a)); //false

        if (false && foo.call()) {
            System.out.println(666);
        } else {
            System.out.println(77);
        }

        System.out.println(foo.testStuff());
    }
}

class Foobar {
    public boolean lessThan5(int number)  {
        return number < 5;
    }

    public boolean call() {
        System.out.println(999);
        return true;
    }

    public boolean testStuff() {
        int[] a;
        a = new int[70];
        a[25] = 55;
        System.out.println(a[25]); //55
        System.out.println(a.length); //70
        return true;
    }
}