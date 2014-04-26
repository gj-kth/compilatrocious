//Test får errors with non ascii symbols
//

class Huvudsaklig {

    public static void main(String[] argumenten) {
        Student t;
        int i;
        t = new Student();
        i = t.init(20);
        t = t.görTenta().görTenta().görTenta();
        System.out.println(t.taÅldern());
    }
}

class Student {
    int ålder;

    public int initialisera(int a) {
        ålder = a;
        return a;
    }

    public int taÅldern() {
        return åldern;
    }

    public Student görTenta() {
        ålder = ålder + 1;
        return this;
    }
}
