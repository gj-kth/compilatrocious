class UndefinedFunctionOfOtherClass {
    public static void main(String[] args) {
        int x;

        x = new ClassWithNoFunction().getInt();
    }
}
class ClassWithNoFunction {

}