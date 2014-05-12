
class ListGame {
    public static void main(String[] args) {
        Util u;
        u = new Util();
        System.out.println(u.numDivisors(7));
        System.out.println(u.numDivisors(428));
        System.out.println(u.numDivisors(10000));
        System.out.println(u.numDivisors(123456));
        System.out.println(u.numDivisors(764821));
        System.out.println(u.numDivisors(4792));
        System.out.println(u.numDivisors(1));
        System.out.println(u.numDivisors(4));
    }
}

class Util {
    public int div(int n, int d) {
        int res;
        res = 0;
        while (res*d < n) {
            res = res + 1;
        }
        if (!this.equals(res*d, n)) {
            res = res - 1;
        } else {
            
        }
        return res;
    }
    
    public int mod(int x, int m) {
        return x - m*this.div(x, m);
    }
    
    public boolean equals(int a, int b) {
        return !(a < b) && !(b < a);
    }
    
    public int numDivisors(int n) {
        int x;
        int res;
        x = 2;
        res = 0;
        while (!(n < x*x)) {
            while (this.equals(this.mod(n, x), 0)) {
                res = res + 1;
                n = this.div(n, x);
            }
            x = x + 1;
        }
        if (!(this.equals(n, 1))) {
            res = res + 1;
        } else {
            
        }
        return res;
    }
}

