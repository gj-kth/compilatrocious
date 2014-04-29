class StackOverFlow {
  public static void main(String[] args) {
    int a;
    a = new A().immaCreateAStackOverFlow();
  }
}

class A {
  public int immaCreateAStackOverFlow() {
    return this.immaCreateAStackOverFlow();
  }
}