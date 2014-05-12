/* This is a valid test which tests different usages of 'this'. */

class Test {
    public static void main(String[] args) {
        boolean dummy;
        dummy = new Test2().start();
        System.out.println(dummy);
    }
}

class Test2 {
    boolean b;
    int f;
    int[] g;
    Test2 t2;
    Test3 t3;

    public boolean start() {
        t2 = this;
        t3 = new Test3();
        b = (this.te2().te3().test3().getInt() < this.te2().te3().test2().bar());
        b = this.run().finish();
        return true;
    }

    public Test2 te2() {
        return t2;
    }

    public Test3 te3() {
        return t3;
    }

    public boolean finish() {
        f = this.foo(this.run().run().run());
        if (this.run().foo(this) < this.foo(this)) {
            b = true;
        } else {
            b = false;
        }
        return b;
    }

    public int foo(Test2 t) {
        System.out.println(this.foo(this) < this.bar());
        System.out.println(this.go(true)[1] < this.go(false)[2]);
        f = this.go(this.foo(this) < this.foo(this))[this.bar()];
        return this.bar();
    }

    public int bar() {
        if(this.bar() < this.bar())
            f = 6;
        else
            f = 6;
        return 6;
    }

    public int[] go(boolean c) {
        return g;
    }

    public Test2 run() {
        while(this.bar() < this.foo(this)) {
            f = this.bar();
        }
        return this;
    }
}

class Test3 {
    Test2 t2;
    Test3 t3;

    public int run() {
        t3 = this.test3().test2().te2().run().te3().test3();
        return 8;
    }

    public int[] run2(int[] ia) {
        return this.test2().te3().test2().go(true);
    }

    public Test2 test2() {
        return t2;
    }

    public Test3 test3() {
        return t3;
    }

    public int getInt() {
        return 4;
    }
}
