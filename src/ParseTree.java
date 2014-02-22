package compilatrocious.parser;

class ParseTree {
    public static void main(String[] args) {
        MiniJava minij = new MiniJava(System.in);

        try{
            SimpleNode parseTree = minij.Program();

            System.out.println("---------------------------------------------------");
            System.out.println("Success!!!");
            System.out.println("----------");
            parseTree.dump("");
            System.out.println("---------------------------------------------------");

        }catch(Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
