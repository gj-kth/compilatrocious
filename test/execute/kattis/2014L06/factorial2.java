class Fac2 {
    public static void main(String[] args) {
        Factorial2 fac2;
        fac2 = new Factorial2();
        System.out.println(fac2.calc(0));
        System.out.println(fac2.calc(1));
        System.out.println(fac2.calc(7));
    }
}

class Factorial2 {
    public int calc(int num) {
        int ret;
        if (num < 1) {
            ret = 1;
        } else {
            ret = num*this.calc(num-1);
        }
        return ret;
    }
}