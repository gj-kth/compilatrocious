package mjc;

import mjc.parser.*;
import java.io.*;
import java.util.ArrayList;

class X86Main {

    public static void main(String[] args) {
        if(args.length < 1) {
            System.out.println("Not enough args...");
            System.exit(1);
        }
        
        String in_file = args[0];
        FileInputStream source;

        try{
            source = new FileInputStream(in_file);

            try{
                SimpleNode tree = ParseTree.parse(source);
                source.close();

                // Type check
                SymbolTableVisitor visitor = new SymbolTableVisitor();
                SymbolTable symbolTable = (SymbolTable) tree.jjtAccept(visitor, null);

                //TypeCheckVisitor visitor2 = new TypeCheckVisitor(symbolTable);
                //tree.jjtAccept(visitor2, null);

            }catch(TypecheckError | ParseException | TokenMgrError | IOException e) {
                //e.printStackTrace();
                printError(e);
                System.exit(1);
            }

        }catch(FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // Dummy program
        String program = "    .text\n    .globl      main\n    .type       main, @function\nmain:\n    movl $0, %eax\n    ret\n";

        // Get filename
        String[] path = in_file.split("[/\\.]");
        String out_file = path[path.length-2]+".s";

        PrintWriter destination;

        try{
            destination = new PrintWriter("./" + out_file);

            destination.print(program);
            destination.close();

        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }

        System.exit(0);
    }

    private static void printError(Throwable e) {
        StackTraceElement[] st = e.getStackTrace();
        ArrayList<StackTraceElement> stal = new ArrayList<StackTraceElement>();

        for(int i = 0; (i < 4 ) && (i < st.length); i++) {
            st[st.length-(1+i)] = st[i];
        }
        e.setStackTrace(st);
        e.printStackTrace();
    }
}
