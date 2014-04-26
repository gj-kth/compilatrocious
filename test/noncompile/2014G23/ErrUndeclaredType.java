/* Undeclared type usage */

class Mainer {
    public static void main(String[] args) {
        Object o;
        Exist e;
        NonExist ne;
        ExistMethod em;
        int i;

        o = new Object();
        e = new Exist();
        ne = new NonExist();
        em = new ExistMethod().method();
        i = em.method2(ne);
    }
}

class Exist {}

class ExistMethod {
    public ExistMethod method() {
        return this;
    }

    public int method2(NonExist f) {
        return 0;
    }
}
