class Recursion {
  public static void main(String[] args) {
    int out;
    A a;
    a = new A();
    out = a.init();
    out = a.method();
    System.out.println(out);
  }
}

class A {
  int count;

  public int init() {
    count = 0;
    return count;
  }

  public int method() {
    int out;
    count = count + 1;
    if (count < 10) {
      out = this.method();
    } else {
      out = count;
    }
    return out;
  }
}
