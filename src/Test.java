package compilatrocious.parser;

import java.io.*;

class Test {

    public static final boolean PRINT = false;

    public static void main(String[] args) {
         println("Testing compile ... ");
         testCompile();
         println("Testing noncompile ... ");
         testNonCompile();
    }

    public static void testCompile(){
        testFilesInDir(new File("/home/jonathan/Documents/comp-constr/compilatrocious/test/compile"));
    }

    public static void testNonCompile(){
        testFilesInDir(new File("/home/jonathan/Documents/comp-constr/compilatrocious/test/noncompile"));   
    }       

    static void testFilesInDir(File dir){
        println("Directory: " + dir.getPath());
        File[] files = dir.listFiles();
        for(File f : files){
            if(f.getName().contains(".minij")){
                testFile(f);                
            }
        }
    }

    static void testFile(File testFile){
        println("File: " + testFile.getPath());
        try {
            InputStream is = new FileInputStream(testFile);
            ParseTree.parseAndPrint(is);
            is.close(); 
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private static void println(String str){
        if(PRINT){
            System.out.println(str);
        }
    }
}
