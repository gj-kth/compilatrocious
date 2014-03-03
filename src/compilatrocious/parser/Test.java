package compilatrocious.parser;

import java.io.*;

public class Test {

    public static final boolean PRINT = true;

    public static void main(String[] args) {
         println("Testing compile ... ");
         testCompile();
         println("Testing noncompile ... ");
         testNonCompile();
    }

    public static void testCompile(){
        System.out.println(System.getenv("COMPILATROCIOUS_ROOT"));
        testFilesInDir(new File(System.getenv("COMPILATROCIOUS_ROOT") + "/test/compile"));
    }

    public static void testNonCompile(){
        testFilesInDir(new File(System.getenv("COMPILATROCIOUS_ROOT") + "/test/noncompile"));   
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
            ParseTree.parse(is);
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
