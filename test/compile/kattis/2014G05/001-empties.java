// EXT:CEQ
// compile:

// Tests "empty" edgecases of repetitions in the grammar, such as empty classes.

class Foo {
  public static void main(String[] args) { }
}

class Bar { }

class Baz {
  public int foo() {
    return 0;
  }

  public boolean bar(int b) {
    { }
    return this.foo() == 0;
  }

  public boolean baz() {
    return this.bar(1);
  }
}

// vim: set ft=java:
