package mjc;

import mjc.parser.*;
import java.io.*;

class X86Main {

    public static void main(String[] args) {
        if(args.length < 1) {
            System.out.println("Not enough args...");
            System.exit(1);
        }
        
        String file = args[0];
        FileInputStream source;

        try{
            source = new FileInputStream(file);

            try{
                SimpleNode tree = ParseTree.parse(source);
                source.close();
            }catch(TokenMgrError | ParseException | IOException e) {
                System.exit(1);
            }

        }catch(FileNotFoundException e) {
            System.exit(1);
        }

        System.exit(0);
    }
}
