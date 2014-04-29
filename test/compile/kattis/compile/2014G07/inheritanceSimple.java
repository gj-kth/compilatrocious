// EXT:ISC
class Inheritance {
    public static void main(String[] args) {
        Car c;
        int trash;
        c = new Car();
        trash = c.init();
        System.out.println(c.test()); // 10
    }
}

class Vehicle {
    int s;
    public int init() {
        s = 10;
        return 0;
    }
}

class Car extends Vehicle {
    public int test() {
        return s;
    }
}
