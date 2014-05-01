package mjc;

import mjc.parser.*;
import mjc.frame.*;
import java.io.*;
import java.util.ArrayList;
import java.util.*;

class JVMMain {

    public static void main(String[] args) {
        if(args.length < 1) {
            System.out.println("Not enough args...");
            System.exit(1);
        }

		boolean genAsm = false;
		for(String arg : args) {
			if(arg.equals("-S"))
				genAsm = true;
		}

        
        String in_file = args[0];
        System.out.println(in_file);


        FileInputStream source;
        StringBuilder sb = null;



        try{
            source = new FileInputStream(in_file);


            try{
                SimpleNode tree = ParseTree.parse(source);
                source.close();

                // Type check
                SymbolTableVisitor visitor = new SymbolTableVisitor();
                SymbolTable symbolTable = (SymbolTable) tree.jjtAccept(visitor, null);

                TypeCheckVisitor visitor2 = new TypeCheckVisitor(symbolTable);
                tree.jjtAccept(visitor2, null);

				if(genAsm) {
                    System.out.println("visiting");
					JVMVisitor visitor3 = new JVMVisitor(symbolTable);
					sb = (StringBuilder) tree.jjtAccept(visitor3, null);
					System.out.println(sb.toString());
				}

            }catch(TypecheckError | ParseException | TokenMgrError | IOException e) {
                //e.printStackTrace();
                printError(e);
                System.exit(1);
            }

        }catch(FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

		if(genAsm) {
        	// Dummy program
        	String program = sb.toString();

        	// Get filename
        	String[] path = in_file.split("[/\\.]");
        	String out_file = path[path.length-2]+".j";

            System.out.println(out_file);
        	PrintWriter destination;

        	try{
            	destination = new PrintWriter("./" + out_file);

            	destination.print(program);
            	destination.close();

        	}catch(Exception e){
            	e.printStackTrace();
            	System.exit(1);
        	}
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
