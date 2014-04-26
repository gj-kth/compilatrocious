class MethodOverload {
    public static void main(String[] args) {
        System.out.println(new M().add(1,2));
    }
}

class M {
    public int add(int a) {
        return this.add(a, 0);
    }
    
    public int add(int a, int b) {
        return a + b;
    }
}
