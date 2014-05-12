// EXT:ISC
class Inheritance {
    public static void main(String[] args) {
        Vehicle v1;
        Car c;
        Boat b;
        Vehicle v2;
        Vehicle v3;
        int trash;
        v1 = new Vehicle();
        c = new Car();
        b = new Boat();
        v2 = c;
        v3 = b;
        trash = v1.setSpeed();
        System.out.println(v1.getSpeed()); // 100
        System.out.println(v2.getSpeed()); // 120
        System.out.println(v3.getSpeed()); // 50
        System.out.println(c.getSpeed()); // 120
        System.out.println(b.getSpeed()); // 50
    }
}

class Vehicle {
    int speed; // Speed in km/h
    public int setSpeed() {
        speed = 100;
        return 0;
    }
    public int getSpeed() {
        return speed;
    }
}

class Car extends Vehicle {
    public int getSpeed() {
        int t;
        t = this.setSpeed();
        return speed+20;
    }
}

class Boat extends Vehicle {
    public int getSpeed() {
        int t;
        t = this.setSpeed();
        return speed-50;
    }
}
