class UndefinedFunction {
    public static void main(String[] args) {

    }
}

class Testing {
    public int callingUndefined() {
        return this.undefinedFunction();
    }
}