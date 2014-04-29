class MethodOverload {
  public static void main(String[] args) {
    new Helper().foo(1, 2, 3);
  }
}

class Helper {
  public void foo(int a, boolean b, int c) {
  }

  public void foo(int a, int b, boolean c) {
  }
}
