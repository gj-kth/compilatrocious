class bogus4 {
  public static void main(String[] args) {
    System.out.println(new Foo().bar(1, 2, 3));
  }
}

class Foo {
  public int bar(int a, int b) {
    return 4;
  }
}
