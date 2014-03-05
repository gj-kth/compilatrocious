package compilatrocious.parser;

import java.io.*;
import java.util.*;

public class Test {

    public static final boolean PRINT_FILENAMES = false;
    public static final boolean PRINT_PARSETREE = false;
    public static final boolean PRINT_FAILED_TESTS = true;

    public static void main(String[] args){
         println("COMPILATROCIOUS_ROOT = " + System.getenv("COMPILATROCIOUS_ROOT")); 
         try{
            Map<String,TestResult> results = new HashMap<String,TestResult>();
            results.put("compile", testCompile());
            results.put("noncompile", testNonCompile());
            results.put("noncompile oneliners", testNonCompileOneliners());
            boolean allTestsSucceeded = true;
            System.out.println();
            for(Map.Entry<String,TestResult> entry : results.entrySet()){
                TestResult res = entry.getValue();
                System.out.println(entry.getKey() + ": " + res);
                if(res.numPassed < res.numTests){
                    allTestsSucceeded = false;
                }
            }
            if(allTestsSucceeded){
                System.out.println("\n+++ ALL TESTS PASSED +++");
            }else{
                System.out.println("\n--- SOME TESTS FAILED ---");
            }
         }catch(Exception e){
            printException(e);
         }
    }

    public static TestResult testCompile(){
        System.out.println("Testing compile ...");
        return testFilesInDir(new File(System.getenv("COMPILATROCIOUS_ROOT") + "/test/compile"), true);
    }

    public static TestResult testNonCompile(){
        System.out.println("Testing noncompile ...");
        return testFilesInDir(new File(System.getenv("COMPILATROCIOUS_ROOT") + "/test/noncompile"), false);   
    }    

    public static TestResult testNonCompileOneliners() throws FileNotFoundException, IOException{
        System.out.println("Testing noncompile oneliners ...");
        return testOneliners( new File(System.getenv("COMPILATROCIOUS_ROOT") + "/test/noncompile/oneliners"), false);
    }   

    private static void printFiles(List<File> files){
        for(File f : files){
            System.out.println(f.getPath());
        }
    }

    private static TestResult testOneliners(File fileContainingOneliners, boolean positiveTests) throws FileNotFoundException, IOException{
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
        br.close();
        return new TestResult(numOneliners - failedLines.size(), numOneliners);
    }

    private static boolean testOneliner(String oneliner, boolean positiveTest, String fakeFilepath) throws IOException{
        println("Oneliner: " + fakeFilepath);
        String programText = createProgramFromOneliner(oneliner);
        InputStream is = new ByteArrayInputStream(programText.getBytes());
        return testProgram(is, positiveTest, fakeFilepath);
    }

    private static String createProgramFromOneliner(String oneliner){
        return  "class test {\n"
              + "  public static void main (String [] args) {\n"
              +        oneliner + "\n"
              + "  }\n"
              + "}";
    }

    static TestResult testFilesInDir(File dir, boolean positiveTests){
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
        return new TestResult(files.length - failedTests.size(), files.length);
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
            SimpleNode parsed = ParseTree.parse(programText);
            if(PRINT_PARSETREE){
                ParseTree.printSimple(parsed);
            }
            programText.close(); 
            if(!positiveTest){
                if(PRINT_FAILED_TESTS){
                    System.out.println(filePath);
                    System.out.println("Negative test case didn't throw exception.");    
                }
                return false;
            }
        } catch (TokenMgrError | ParseException e) {
            if(positiveTest){
                if(PRINT_FAILED_TESTS){
                    System.out.println(filePath);
                    printException(e);
                }
                return false;
            }
        }
        return true;
    }

    private static void println(String str){
        if(PRINT_FILENAMES){
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

    private static class TestResult{
        int numTests;
        int numPassed;
        
        TestResult(int numPassed, int numTests){
            this.numTests = numTests;
            this.numPassed = numPassed;
        }

        public String toString(){
            return "TESTS PASSED: " + numPassed + "/" + numTests;
        }
    }
}
