class Classes {
  public static void main(String[] args) {
    A a;
    C c;
    a = new A();
    c = new C();

    a.setC(c);
    c.setA(a);

    System.out.println(a.b(c) + c.d());
  }
}

class A {
  C c;

  public void setC(C _c) {
    c = _c;
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

  public void setA(A _a) {
    a = _a;
  }

  public int d() {
    return a.a();
  }

  public int c() {
    return 4;
  }
}
