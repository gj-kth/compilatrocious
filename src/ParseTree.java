package compilatrocious.parser;

class ParseTree {
    public static void main(String[] args) {
        parseAndPrint(System.in);
    }

    public static void parseAndPrint(java.io.InputStream stream){
        MiniJava minij = new MiniJava(stream);
        try{
            SimpleNode parseTree = minij.Program();
            printSimple(parseTree);
        }catch(Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    static void printSimple(SimpleNode parseTree){
        System.out.println("---------------------------------------------------");
        System.out.println("Success!!!");
        System.out.println("----------");
        parseTree.dump("");
        System.out.println("---------------------------------------------------");
    }

    //Currently not very useful. 
    static void printDetails(SimpleNode parseTree){
        System.out.println("--- " + parseTree.jjtGetValue() + " ---");
        for(int c = 0; c < parseTree.jjtGetNumChildren(); c++){
            Node child = parseTree.jjtGetChild(c);
            System.out.println(child);
        }
    }
}
