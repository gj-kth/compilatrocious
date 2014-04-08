package mjc.parser;

import java.io.*;
import java.util.*;

public class Test {

    public static final boolean PRINT_FILENAMES = true;
    public static final boolean PRINT_PARSETREE = false;
    public static final boolean PRINT_SYMBOL_TABLE = false;
    public static final boolean PRINT_FAILED_TESTS = true;
    public static final boolean PRINT_NEGATIVE_ST = false;

    public static void main(String[] args){
         Printer.conditionalPrintln("COMPILATROCIOUS_ROOT = " + System.getenv("COMPILATROCIOUS_ROOT"), PRINT_FILENAMES); 
         try{
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
         }catch(Exception e){
            Printer.printThrowable(e);
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
        Printer.conditionalPrintln("Oneliner: " + fakeFilepath, PRINT_FILENAMES);
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
        TestResult result = new TestResult(0,0);
        Printer.conditionalPrintln("Directory: " + dir.getPath(), PRINT_FILENAMES);
        File[] files = dir.listFiles();
        for(File f : files){
            if(f.isDirectory()){
                TestResult subResult = testFilesInDir(f, positiveTests);
                result.numTests += subResult.numTests;
                result.numPassed += subResult.numPassed;
            }else if(f.getName().contains(".minij") || f.getName().contains(".java")){
                boolean success = testFile(f, positiveTests);                
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
    * (positiveTest == true) means that parsing 'should' be successful.
    * (return == true) means that test was passed
    */
    static boolean testFile(File testFile, boolean positiveTest){
        Printer.conditionalPrintln("File: " + testFile.getPath(), PRINT_FILENAMES);
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
            if(!positiveTest){
                if(PRINT_FAILED_TESTS){
                    System.out.println(filePath);
                    System.out.println("Negative test case didn't throw exception.");    
                }
                return false;
            }
        } catch (TypecheckError | TokenMgrError | ParseException e) {
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
        return true;
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
