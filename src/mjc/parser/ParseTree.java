package mjc.parser;

public class ParseTree {
    public static void main(String[] args) {
        try{
            SimpleNode parsed = parse(System.in);
            dumpTree(parsed);
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

    public static void dumpTree(SimpleNode root){
        dump(root, "");
    }

    private static void dump(SimpleNode root, String prefix) {
        System.out.print(prefix + root);
        Printer.printGreen(root.jjtGetValue() != null ? " (" + root.jjtGetValue() + ")" : "");
        if (root.children != null) {
          for (int i = 0; i < root.children.length; ++i) {
            SimpleNode n = (SimpleNode)root.children[i];
            if (n != null) {
              dump(n, prefix + "  ");
            }
          }
        }
    }
}
