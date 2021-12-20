package edu.csc413.calculator.evaluator;

import edu.csc413.calculator.exceptions.InvalidTokenException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class EvaluatorDriver {
    /**
     * Driver class that use Evaluator to evaluate expressions.
     * THe expressions can either be given as a command line argument
     * OR typed in at the keyboard. If you wish to type in expressions
     * give NO command line arguments. Otherwise you may give a list of
     * strings as a command line argument and the driver will run all strings
     * in that list.
     *
     * @param args command line arguments.
     */

    static HashMap<String, String> testExpressions;
    static HashMap<String, Result> testResults;
    /**
     * **** Static Initializer Block Explanation ****
     * This block of code is called a static initializer. It is executed when
     * this class loaded  by the JVM. Now, when the class is loaded is tricky.
     * But since this class has a main method and we use it as out main class,
     * it will be loaded immediately. However, if it wasn't our main class, it
     * would be loaded when the class is referenced. This becomes more interesting
     * if we were to mix inheritance with this.
     */
    static {
        EvaluatorDriver.testExpressions =  new HashMap<>();
        EvaluatorDriver.testExpressions.put("1+2","3");
        EvaluatorDriver.testExpressions.put("1/2","0");
        EvaluatorDriver.testExpressions.put("1+2*3","7");
        EvaluatorDriver.testExpressions.put("(1+2)*3","9");
        EvaluatorDriver.testExpressions.put("2-(3/10)+2-5","-1");
        EvaluatorDriver.testExpressions.put("(6-12*2)/3","-6");
        EvaluatorDriver.testExpressions.put("3^2","9");
        EvaluatorDriver.testExpressions.put("3^2/2","4");
        EvaluatorDriver.testExpressions.put("3^2/2 +(4+5)","13");
        EvaluatorDriver.testExpressions.put("3^2 + (2^4) +(4+5)","34");
        EvaluatorDriver.testExpressions.put("3+2-9+8*2 + (3+9-8/2)","20");
        EvaluatorDriver.testExpressions.put("2+3-5*((2-3)*2-5*2+3*(2-3-5-5*6)+4/2)*2-9","1176");
        EvaluatorDriver.testExpressions.put("(2+3-5*(6+5)","exception");
        EvaluatorDriver.testExpressions.put("2+3-5**(2-3)*2","exception");
        EvaluatorDriver.testExpressions.put("2+3-5**c2-3)*2","exception");

        EvaluatorDriver.testResults = new HashMap<>();
    }

    public static void main(String... args){
        if(args.length == 0) {
            BufferedReader userInputReader;
            String testExpression;
            int actualResult;
            /* if no command line arguments are given, we will ask for expressions */
            try {
                userInputReader = new BufferedReader(new InputStreamReader(System.in));
                while (true) {
                    System.out.print("Enter an Expression: ");
                    testExpression = userInputReader.readLine();
                    Evaluator evaluator = new Evaluator();
                    actualResult = evaluator.evaluateExpression(testExpression);
                    System.out.printf("Expression : %s , Result %6d\n", testExpression, actualResult);
                }
            } catch(InvalidTokenException ex) {
                System.out.println(ex.getMessage());
            } catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
        }else if(args.length == 1 && (args[0].toLowerCase()).equals("auto")){
            /* if only 1 command line argument is given AND it is auto we will
             * run expressions in the above HashMap
             */
            EvaluatorDriver.testExpressions.forEach( (testExpression, expectedResult) -> {
                try {
                    Evaluator evaluator = new Evaluator();
                    String actualResult = evaluator.evaluateExpression(testExpression) + "";
                    Result testResult = new Result(testExpression, expectedResult,actualResult,expectedResult.equals(actualResult) ? "Pass" : "Fail");
                    EvaluatorDriver.testResults.put(testExpression, testResult);
                } catch(Exception ex) {
                    Result testResult = new Result(testExpression, expectedResult,ex.toString(), expectedResult.equals("exception") ? "Pass" : "Fail");
                    EvaluatorDriver.testResults.put(testExpression, testResult);
                }
            });
            EvaluatorDriver.printTestResultsForAutoDriver();
        }else{
            /* if a list of expressions are given as command line arguments
             * we will execute all of them and display results.
             * The format of the list of expressions should be:
             * exp0 exp1 exp2 ... expN
             * Each expressions should separated by spaces.
             */
            for(String testExpression : args) {
                try {
                    int actualResult;
                    Evaluator evaluator = new Evaluator();
                    actualResult = evaluator.evaluateExpression(testExpression);
                    System.out.printf("Expression : %s , Result: %-6d\n", testExpression, actualResult);
                } catch(InvalidTokenException ex) {
                    System.out.println(ex.getMessage());
                } catch(Exception ex) {
                    System.out.println(ex.getMessage());
                }

            }

        }
    }


    static void printTestResultsForAutoDriver(){
        System.out.println("|               Test Expression               | Expected Result | Status | Actual Result");
        IntStream.range(0, 88).forEach( (i) -> System.out.print("-"));
        EvaluatorDriver.testResults.forEach((testExpression, testResultInformation) -> {
            System.out.printf("\n| %43s | %15s | %6s | %s", testExpression,
                    testResultInformation.getExpectedResult(),
                    testResultInformation.getPassOrFail(),
                    testResultInformation.getActualResult());
        });
    }
}


class Result {
    private String testExpression;
    private String expectedResult;
    private String actualResult;
    private String passOrFail;

    public Result(String testExpression, String expectedResult, String actualResult, String passOrFail) {
        this.testExpression = testExpression;
        this.expectedResult = expectedResult;
        this.actualResult = actualResult;
        this.passOrFail = passOrFail;
    }

    public String getTestExpression() {
        return testExpression;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public String getActualResult() {
        if(this.actualResult.contains("Exception")){
            return this.stripClassPathFromException();
        }
        return actualResult;
    }

    public String getPassOrFail() {
        return passOrFail;
    }

    public String stripClassPathFromException(){
        String[] classPathTokens = this.actualResult.split("\\.");
        return classPathTokens[classPathTokens.length-1];
    }
}
