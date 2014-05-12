// EXT:BDJ

class Logic {
  public static void main(String[] args) {
    A a;
    a = new A();
    if (!a.callMe() && a.dontCallMe()) {
    } else {
      System.out.println(100);
    }

    if (a.callMe() && a.callMe()) {
      System.out.println(101);
    } else {}

    if (a.callMe() || a.dontCallMe()) {
      System.out.println(102);
    } else {}

    if (!a.callMe() || !a.callMe()) {
    } else {
      System.out.println(103);
    }

    // This is tricky, requires correct ordering
    if (true && true || false && false) {
      System.out.println(104);
    } else {

    }
  }
}

class A {
  public boolean callMe() {
    System.out.println(0);
    return true;
  }

  public boolean dontCallMe() {
    System.out.println(1000);
    return true;
  }
}
