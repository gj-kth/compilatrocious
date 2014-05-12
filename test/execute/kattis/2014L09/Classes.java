class Classes {
  public static void main(String[] args) {
    System.out.println(new Program().run());
  }
}

class Program {
  public int run() {
    A a;
    C c;
    int status;

    a = new A();
    c = new C();

    status = a.setC(c);
    status = c.setA(a);

    System.out.println(a.b(c) + c.d());
    return 0;
  }
}

class A {
  C c;

  public int setC(C _c) {
    c = _c;
    return 0;
  }

  public int a() {
    return 2;
  }

  public int b(C c2) {
    return c.c() + c2.c();
  }
}

class C {
  A a;

  public int setA(A _a) {
    a = _a;
    return 0;
  }

  public int d() {
    return a.a();
  }

  public int c() {
    return 4;
  }
}
