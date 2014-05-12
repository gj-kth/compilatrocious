// noncompile: Re-declaration of local variable 'a'

class Main { public static void main(String[] args) { } }

class A {
  public int foo(int a) {
    int a;
    return 0;
  }
}
