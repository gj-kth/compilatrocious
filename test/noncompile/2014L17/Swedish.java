//Test f�r errors with non ascii symbols
//

class Huvudsaklig {

    public static void main(String[] argumenten) {
        Student t;
        int i;
        t = new Student();
        i = t.init(20);
        t = t.g�rTenta().g�rTenta().g�rTenta();
        System.out.println(t.ta�ldern());
    }
}

class Student {
    int �lder;

    public int initialisera(int a) {
        �lder = a;
        return a;
    }

    public int ta�ldern() {
        return �ldern;
    }

    public Student g�rTenta() {
        �lder = �lder + 1;
        return this;
    }
}
