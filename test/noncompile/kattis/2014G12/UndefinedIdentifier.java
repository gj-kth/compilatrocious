class UndefinedIdentifier {
    public static void main(String[] args) {
        int x;
    }
}

class testing {
    public int initializingVariables() {
        int x;
        int y;
        return y;
    }

    public int usingOtherVariables() {
        return x;
    }
}