class Chains {
  public static void main(String[] args) {
    int out;
    A a;
    a = new A();
    out = a.init();
    a = a.method().method().method().method().method().method().method().method().method().method()
    .method().method().method().method().method().method().method().method().method().method();
    System.out.println(a.count());
  }
}

class A {
  int count;

  public int init() {
    count = 0;
    return count;
  }

  public A method() {
    count = count + 1;
    return this;
  }

  public int count() {
    return count;
  }
}
