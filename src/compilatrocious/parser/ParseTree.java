package compilatrocious.parser;

class ParseTree {
    public static void main(String[] args) {
        try{
            SimpleNode parsed = parse(System.in);
            printSimple(parsed);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static SimpleNode parse(java.io.InputStream stream) throws ParseException{
        MiniJava minij = new MiniJava(stream);
        SimpleNode parseTree = minij.Program();
        return parseTree;
    }

    static void printSimple(SimpleNode parseTree){
        System.out.println("---------------------------------------------------");
        System.out.println("Success!!!");
        System.out.println("----------");
        parseTree.dump("");
        printDetails(parseTree);
        System.out.println("---------------------------------------------------");
    }

    //Currently not very useful. 
    static void printDetails(SimpleNode parseTree){
        System.out.println("--- " + parseTree.jjtGetValue() + " ---");
        System.out.println("numchildren: " + parseTree.jjtGetNumChildren());
        //SimpleNode node = (SimpleNode) parseTree.jjtGetChild(0);
        printNodes(parseTree);
    }

    static void printNodes(SimpleNode node) {
        SimpleNode child;
        for(int c = 0; c < node.jjtGetNumChildren(); c++){
            child = (SimpleNode) node.jjtGetChild(c);
            System.out.println("token is: " + child.toString() + " value is: " + child.jjtGetValue());
            printNodes(child);
        }
    }
}
