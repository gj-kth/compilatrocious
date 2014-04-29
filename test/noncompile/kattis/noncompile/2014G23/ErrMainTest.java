/* Invalid main method */

class Main {
    // No VarDecls allowed outside main method
    int a;

    public static void main(String[] args) {
        a = 1 + 2;
    }

    // No methods allowed in main class other than 'main'
    public boolean method() {
        return false;
    }
}
