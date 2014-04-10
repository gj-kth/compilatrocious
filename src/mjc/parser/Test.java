package mjc.parser;

import java.io.*;
import java.util.*;

public class Test {

    public static final boolean PRINT_FILENAMES = true;
    public static final boolean PRINT_PARSETREE = false;
    public static final boolean PRINT_SYMBOL_TABLE = false;
    public static final boolean PRINT_FAILED_TESTS = true;
    public static final boolean PRINT_NEGATIVE_ST = false;

    //Each negative test should fail with a specific exception
    //All tests in the same directory should throw the same exception (something similar to the dirname)
    //Below is a mapping from directoryNames to name of exception class
    private static Map<String, Class> dirToException = new HashMap<String, Class>();

    public static void main(String[] args){
         Printer.conditionalPrintln("COMPILATROCIOUS_ROOT = " + System.getenv("COMPILATROCIOUS_ROOT"), PRINT_FILENAMES); 
         try{
           
            String s = "mjc.parser.";
            dirToException.put("duplicateDecl", Class.forName(s + "DuplicateDeclaration"));  
            dirToException.put("refMissingMethod", Class.forName(s + "ReferencedMissingMethod"));  
            dirToException.put("refMissingType", Class.forName(s + "ReferencedMissingType"));
            dirToException.put("refMissingVar", Class.forName(s + "ReferencedMissingVariable"));
            dirToException.put("wrongNumArgs", Class.forName(s + "WrongNumberArgs"));
            dirToException.put("wrongType", Class.forName(s + "WrongType"));           

            Map<String,TestResult> results = new HashMap<String,TestResult>();
            results.put("compile", testCompile());
            results.put("noncompile", testNonCompile());
            results.put("noncompile oneliners", testNonCompileOneliners());
            results.put("programs", testPrograms());
            boolean allTestsSucceeded = true;
            System.out.println();
            for(Map.Entry<String,TestResult> entry : results.entrySet()){
                TestResult res = entry.getValue();
                System.out.print(entry.getKey() + ": PASSED ");
                if(res.numPassed < res.numTests){
                    allTestsSucceeded = false;
                    Printer.printRed("" + res);
                }else{
                    Printer.printGreen("" + res);
                }
            }
            System.out.println();
            if(allTestsSucceeded){
                Printer.printGreen("+++ ALL TESTS PASSED +++");
            }else{
                Printer.printRed("--- SOME TESTS FAILED ---");
            }
         }catch(Throwable e){
            Printer.printThrowable(e);
            System.exit(0); //TODO
         }
    }

    public static TestResult testCompile(){
        System.out.println("Testing compile ...");
        return testFilesInDir(new File(System.getenv("COMPILATROCIOUS_ROOT") + "/test/compile"), true);
    }

    public static TestResult testPrograms(){
        System.out.println("Testing programs ...");
        return testFilesInDir(new File(System.getenv("COMPILATROCIOUS_ROOT") + "/test/programs"), true);
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
            boolean success = testOneliner(line, filePath, positiveTests);
            if(! success){
                failedLines.add(lineNumber);
            }
            lineNumber ++;
        }
        int numOneliners = lineNumber - 1;
        br.close();
        return new TestResult(numOneliners - failedLines.size(), numOneliners);
    }

    private static boolean testOneliner(String oneliner, String fakeFilepath, boolean positiveTest) throws IOException{
        Printer.conditionalPrintln("Oneliner: " + fakeFilepath, PRINT_FILENAMES);
        String programText = createProgramFromOneliner(oneliner);
        InputStream is = new ByteArrayInputStream(programText.getBytes());
        try{
            Class generalException = Class.forName("java.lang.Throwable");
            return testProgram(is, positiveTest, generalException, fakeFilepath);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
            System.exit(0);
            return false;
        }
        
    }

    private static String createProgramFromOneliner(String oneliner){
        return  "class test {\n"
              + "  public static void main (String [] args) {\n"
              +        oneliner + "\n"
              + "  }\n"
              + "}";
    }

    static TestResult testFilesInDir(File dir, boolean positiveTests){
        try{
            return testFilesInDir(dir, positiveTests, Class.forName("java.lang.Throwable"));
        }catch(ClassNotFoundException e){
            e.printStackTrace();
            System.exit(0);
            return null;
        }
    }

    // expectedException == null : positive test
    // otherwise it shows HOW the test should fail
    static TestResult testFilesInDir(File dir, boolean positiveTests, Class expectedException){
        TestResult result = new TestResult(0,0);
        Printer.conditionalPrintln("Directory: " + dir.getPath(), PRINT_FILENAMES);
        File[] files = dir.listFiles();
        for(File f : files){
            if(f.isDirectory()){
                String dirName = f.getName();
                TestResult subResult;
                if(dirToException.containsKey(dirName)){
                    subResult = testFilesInDir(f, positiveTests, dirToException.get(dirName));
                }else{
                    subResult = testFilesInDir(f, positiveTests, expectedException);
                }
                result.numTests += subResult.numTests;
                result.numPassed += subResult.numPassed;
            }else if(f.getName().contains(".minij") || f.getName().contains(".java")){
                boolean success = testFile(f, positiveTests, expectedException);                
                result.numTests ++;
                if(success){
                    result.numPassed ++;
                }
            }
        }
        return result;
    }


    /**
    * Tests to parse program in given file.
    * (expectedException == null) means that parsing 'should' be successful.
    * (return == true) means that test was passed
    */
    static boolean testFile(File testFile, boolean positiveTest, Class expectedException){
        Printer.conditionalPrintln("File: " + testFile.getPath(), PRINT_FILENAMES);
        try {
            InputStream is = new FileInputStream(testFile);
            return testProgram(is, positiveTest, expectedException, testFile.getPath());
        } catch(Exception e){
            e.printStackTrace();
            System.exit(0);
        }
        return false; //Will never be reached?
    }

    //Boolean determines success of test.
    // True = test successful,
    // False = test failed
    static boolean testProgram(InputStream programText, boolean positiveTest, Class expectedException, String filePath) throws IOException{
        try { 
            // Build parsetree
            SimpleNode parsed = ParseTree.parse(programText);
            if(PRINT_PARSETREE){
                System.out.println(filePath);
                ParseTree.dumpTree(parsed);
            }

            // Type check
            SymbolTableVisitor visitor = new SymbolTableVisitor();
            SymbolTable symbolTable = (SymbolTable) parsed.jjtAccept(visitor, null);
            if(PRINT_SYMBOL_TABLE){
                System.out.println(filePath);
                System.out.println(symbolTable.toString(""));
            }
            TypeCheckVisitor visitor2 = new TypeCheckVisitor(symbolTable);
            parsed.jjtAccept(visitor2, null);

            programText.close(); 
            if(!positiveTest){ //It's a negative test. But no exception was thrown!
                if(PRINT_FAILED_TESTS){
                    System.out.println(filePath);
                    System.out.println("Negative test case didn't throw exception.");    
                }
                return false;
            }
        }catch(Throwable e){
            if(expectedException.isInstance(e) && ! positiveTest){ //It's a negative test. The correct exception has been thrown.
                return true;
            }
            if(positiveTest){
                if(PRINT_FAILED_TESTS){
                    System.out.println(filePath);
                    Printer.printThrowable(e);
                }
                return false;    
            }else{
                if(PRINT_NEGATIVE_ST) {
                    System.out.println("Stacktrace for negative test " + filePath + ":");
                    System.out.println("\t" + e.getMessage());
                    StackTraceElement[] st = e.getStackTrace();
                    for(int i = 0; i < st.length; i++) {
                        System.out.println("\t" + st[i]);
                    }
                }
            }
        }

        return true; //It's a positive test. No exception was thrown.
    }

    private static class TestResult{
        int numTests;
        int numPassed;
        
        TestResult(int numPassed, int numTests){
            this.numTests = numTests;
            this.numPassed = numPassed;
        }

        public String toString(){
            return "" + numPassed + "/" + numTests;
        }
    }
}
