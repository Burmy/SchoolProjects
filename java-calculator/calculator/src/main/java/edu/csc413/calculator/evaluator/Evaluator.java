package edu.csc413.calculator.evaluator;

import edu.csc413.calculator.exceptions.InvalidTokenException;
import edu.csc413.calculator.operators.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Evaluator {

  private Stack<Operand> operandStack;
  private Stack<Operator> operatorStack;
  private StringTokenizer expressionTokenizer;
  private final String delimiters = " +/*-^()";

  public Evaluator() {
    operandStack = new Stack<>();
    operatorStack = new Stack<>();
  }

  //private method to calculate expressions
  private void evaluate(){
    Operator operatorFromStack = operatorStack.pop();
    Operand operandTwo = operandStack.pop();
    Operand operandOne = operandStack.pop();
    Operand result = operatorFromStack.execute( operandOne, operandTwo );
    operandStack.push(result);
  }

  public int evaluateExpression(String expression) throws InvalidTokenException {
    String expressionToken;

    // The 3rd argument is true to indicate that the delimiters should be used
    // as tokens, too. But, we'll need to remember to filter out spaces.
    this.expressionTokenizer = new StringTokenizer( expression, this.delimiters, true );

    // initialize operator stack - necessary with operator priority schema
    // the priority of any operator in the operator stack other than
    // the usual mathematical operators - "+-*/" - should be less than the priority
    // of the usual operators
    while ( this.expressionTokenizer.hasMoreTokens() ) {
      // filter out spaces
      if ( !( expressionToken = this.expressionTokenizer.nextToken() ).equals( " " )) {
        // check if token is an operand, push it onto the operand stack.
        if ( Operand.check( expressionToken )) {
          operandStack.push( new Operand( expressionToken ));
        } else {
          if ( ! Operator.check( expressionToken )) {
            throw new InvalidTokenException(expressionToken);
          }

          Operator newOperator = Operator.getOperator(expressionToken);

          //If the character is "(", then push it onto operator stack.
          if(newOperator.equals(Operator.getOperator("("))) {
            operatorStack.push(newOperator);
            continue;
          }

          // If the character is ")", then evaluate() until
          // the corresponding "(" is encountered in operator stack.
          // At this stage POP the operator stack and ignore "(."
          if(newOperator.equals(Operator.getOperator(")"))) {
            while (!(operatorStack.peek().equals(Operator.getOperator("(")))) {
              evaluate();
            }
            operatorStack.pop();
            continue;
          }

          // If an operator token is scanned, and the operator Stack is not empty,
          // if the top of the operator stack' priority is greator than the one we
          // just saw, then and Operator object is created from the token, and
          //pushed to the operator Stack
          while (!(operatorStack.isEmpty()) && operatorStack.peek().priority() >= newOperator.priority()) {
            evaluate();
          }
          operatorStack.push(newOperator);
        }
      }
    }

    // Control gets here when we've picked up all of the tokens; you must add
    // code to complete the evaluation - consider how the code given here
    // will evaluate the expression 1+2*3
    // When we have no more tokens to scan, the operand stack will contain 1 2
    // and the operator stack will have + * with 2 and * on the top;
    // In order to complete the evaluation we must empty the stacks,
    // that is, we should keep evaluating the operator stack until it is empty.

    //processes the operator stack until empty.
    while(!operatorStack.isEmpty()){
      evaluate();
    }
    return operandStack.pop().getValue();
  }
}
