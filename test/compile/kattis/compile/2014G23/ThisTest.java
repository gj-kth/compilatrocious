/* A test with tricky uses of 'this' that should succeed. */

class ThisTest {
    public static void main(String[] args) {
        A a;
        B b;
        C c;
        D d;
        E e;
        a = new A();
        b = new B();
        c = new C();
        d = new D();
        e = new E();
    }
}

class A {
    C cvar;
    A retA;
    int intVar;

    public A a(int asd) {
        return this;
    }

    public C c(int p1, A p2) {
        return cvar;
    }

    public C d(A asf) {
        return this.c(0, this);
    }

    public A rec(A recs) {
        return this.recs(this);
    }

    public A recs(A recs) {
        while(false) {
            if(this.inter() < 0) {
                intVar = this.inter();
            } else {}
        }

        return retA;
    }

    public int inter() {
        cvar = this.c(123, this);
        cvar = this.d(this);
        return 0;
    }
}

class B {
    boolean i;
    boolean l;
    int j;
    int k;

    public boolean a() {
        i = false;
        k = this.b(this.c(this.d(), 0), 123);
        return i;
    }

    public int b(int f1, int f2) {
        return 1;
    }

    public int c(int first, int second) {
        return 123;
    }

    public int d() {
        return 1 - 1;
    }

    public boolean e() {
       l = 3 < this.b(1, 2) + 0 * this.d();

        return this.e() && false && true && this.v(this.a());
    }

    public boolean v(boolean param) {
        return param && j < k;
    }
}

class C {
    int v1;
    int v2;
    int v3;

    public int m1() {
        v1 = this.m2();

        if(v1 < this.m2()) {

        } else {}

        return 0;
    }

    public int m2() {
        if(this.m1() < this.m2() && this.m3(1) < this.m4()) {

        } else {}

        return this.m3(v3);
    }

    public int m3(int f1) {
        if(this.m3(this.m3(this.m3(0))) < this.m2()) {

        } else {}
        return 0;
    }

    public int m4() {
        return 0;
    }
}

class D {
    int v;
    int w;

    public int method(int a, int b, int c) {
        w = 88;

        return this.method(this.method2(2), this.method2(2),
                this.method(1, 2, this.method2(1)));
    }

    public int method2(int a) {
        while(this.method(1, 3, w) < 10) {
            v = this.method(1, 2, 3);
        }

        return this.method(v, v, this.method2(w));
    }
}

class E {
    A a;
    B b;
    C c;
    E e;

    public A m1() {
        a = new A();
        c = this.m3(this);
        return this.m2(b);
    }

    public A m2(B f1) {
        b = new B();
        return a;
    }

    public C m3(E f1) {
        e = f1;
        c = new C();
        return c;
    }
}

class F {
    E e;

    public boolean m1(B b) {
        return this.m2().m3(e).m1() < this.m2().m3(e).m3(0);
    }

    public E m2() {
        return e;
    }
}
