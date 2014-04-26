// noncompile: expecting: 'public'

// This testcase is negative since the main class has to be the first class in
// a legal Minijava program.

class Foo {
}
class Bar {
  public static void main(String[] args) {
  }
}


// vim: set ft=java:
