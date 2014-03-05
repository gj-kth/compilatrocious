package compilatrocious.parser;

import java.io.*;
import java.util.*;

public class Test {

    public static final boolean PRINT = false;

    public static void main(String[] args){
         println("COMPILATROCIOUS_ROOT = " + System.getenv("COMPILATROCIOUS_ROOT")); 
         try{
            testCompile();
             testNonCompile();
             testNonCompileOneliners();   
         }catch(Exception e){
            printException(e);
         }
         
    }

    public static void testCompile(){
        System.out.print("Testing compile ... ");
        testFilesInDir(new File(System.getenv("COMPILATROCIOUS_ROOT") + "/test/compile"), true);
    }

    public static void testNonCompile(){
        System.out.print("Testing noncompile ... ");
        testFilesInDir(new File(System.getenv("COMPILATROCIOUS_ROOT") + "/test/noncompile"), false);   
    }    

    public static void testNonCompileOneliners() throws FileNotFoundException, IOException{
        System.out.print("Testing noncompile-oneliners ...");
        testOneliners( new File(System.getenv("COMPILATROCIOUS_ROOT") + "/test/noncompile/oneliners"), false);
    }   

    private static void printFiles(List<File> files){
        for(File f : files){
            System.out.println(f.getPath());
        }
    }

   

    private static void testOneliners(File fileContainingOneliners, boolean positiveTests) throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new FileReader(fileContainingOneliners));
        String line;
        int lineNumber = 1;
        List<Integer> failedLines = new ArrayList<Integer>();
        while ((line = br.readLine()) != null) {
            String filePath = "oneliner_" + lineNumber;
            boolean success = testOneliner(line, positiveTests, filePath);
            if(! success){
                failedLines.add(lineNumber);
            }
            lineNumber ++;
        }
        int numOneliners = lineNumber - 1;
        System.out.println("TESTS PASSED: " + (numOneliners - failedLines.size()) + "/" + numOneliners);
        br.close();
    }

    private static boolean testOneliner(String oneliner, boolean positiveTest, String fakeFilepath) throws IOException{
        //System.out.println("Testing oneliner " + fakeFilepath); //TODO
        //System.out.println("----------         " + oneliner + "            ------------");
        String programText = "class test {\n"
                           + "  public static void main (String [] args) {\n"
                           +        oneliner + "\n"
                           + "  }\n"
                           + "}";
        InputStream is = new ByteArrayInputStream(programText.getBytes());
        return testProgram(is, positiveTest, fakeFilepath);
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
            return testProgram(is, positiveTest, testFile.getPath());
        } catch(Exception e){
            e.printStackTrace();
            System.exit(0);
        }
        return false; //Will never be reached?
    }

    static boolean testProgram(InputStream programText, boolean positiveTest, String filePath) throws IOException{
        try {
            ParseTree.parse(programText);
            programText.close(); 
            if(!positiveTest){
                System.out.println(filePath);
                System.out.println("Negative test case didn't throw exception.");
                return false;
            }
        } catch (TokenMgrError | ParseException e) {
            if(positiveTest){
                System.out.println(filePath);
                printException(e);
                return false;
            }
        }
        return true;
    }

    private static void println(String str){
        if(PRINT){
            System.out.println(str);
        }
    }

    private static void printException(Throwable e){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        System.out.println("--------------------------------------------------------");
        System.out.println(sw.toString());
        System.out.println("--------------------------------------------------------");
    }
}
