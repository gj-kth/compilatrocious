// EXT:ISC
class Main {
    public static void main(String[] args) {
        System.out.println(0);
    }
}

// Circular dependencies
class A extends C {
    public int aMethod() {
        System.out.println(1);
    }
}

class B extends A {
    public int bMethod() {
        System.out.println(2);
    }
}

class C extends B {
    public int cMethod() {
        System.out.println(3);
    }
}
