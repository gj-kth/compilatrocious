package compilatrocious.parser;

import java.io.*;
import java.util.*;

public class Test {

    public static final boolean PRINT_FILENAMES = true;
    public static final boolean PRINT_PARSETREE = false;

    public static void main(String[] args) {
         println("COMPILATROCIOUS_ROOT = " + System.getenv("COMPILATROCIOUS_ROOT")); 
         testCompile();
         testNonCompile();
    }

    public static void testCompile(){
        System.out.print("Testing compile ... ");
        testFilesInDir(new File(System.getenv("COMPILATROCIOUS_ROOT") + "/test/compile"), true);
    }

    public static void testNonCompile(){
        System.out.print("Testing noncompile ... ");
        testFilesInDir(new File(System.getenv("COMPILATROCIOUS_ROOT") + "/test/noncompile"), false);   
    }       

    private static void printFiles(List<File> files){
        for(File f : files){
            System.out.println(f.getPath());
        }
    }


    static void testFilesInDir(File dir, boolean positiveTests){
        println("Directory: " + dir.getPath());
        File[] files = dir.listFiles();
        List<File> failedTests = new ArrayList<File>();
        for(File f : files){
            if(f.getName().contains(".minij")){
                boolean success = testFile(f, positiveTests);                
                if(! success){
                    failedTests.add(f);
                }
            }
        }

        System.out.println("TESTS PASSED: " + (files.length - failedTests.size()) + "/" + files.length);
    }

    /**
    * Tests to parse program in given file.
    * (positiveTest == true) means that parsing 'should' be successful.
    * (return == true) means that test was passed
    */
    static boolean testFile(File testFile, boolean positiveTest){
        println("File: " + testFile.getPath());
        try {
            InputStream is = new FileInputStream(testFile);
            if(PRINT_PARSETREE)
                ParseTree.parseAndPrint(is);
            else
                ParseTree.parse(is);
            is.close(); 
            if(!positiveTest){
                System.out.println(testFile.getPath());
                System.out.println("Negative test case didn't throw exception.");
                return false;
            }
        } catch (ParseException e) {
            if(positiveTest){
                System.out.println(testFile.getPath());
                printException(e);
                return false;
            }
        } catch(Exception e){
            e.printStackTrace();
            System.exit(0);
        }
        return true;
    }

    private static void println(String str){
        if(PRINT_FILENAMES){
            System.out.println(str);
        }
    }

    private static void printException(Exception e){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        System.out.println("--------------------------------------------------------");
        System.out.println(sw.toString());
        System.out.println("--------------------------------------------------------");
    }
}
