import edu.csc413.calculator.evaluator.Operand;
import edu.csc413.calculator.operators.MultiplyOperator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Multiplication Test")
public class MultiplyOperatorTest {

    @Test
    public void simpleMultiplicationTest(){
        Operand operandOne =  new Operand(6);
        Operand operandTwo =  new Operand(11);
        MultiplyOperator multiplyOperator =  new MultiplyOperator();
        Operand result = new Operand(multiplyOperator.execute(operandOne,operandTwo).getValue());
        assertThat(result.getValue(), is(equalTo(66)));
    }

    @Test
    public void simpleMultiplicationTestReversedOperands(){
        Operand operandOne =  new Operand(11);
        Operand operandTwo =  new Operand(6);
        MultiplyOperator multiplyOperator =  new MultiplyOperator();
        Operand result = new Operand(multiplyOperator.execute(operandOne,operandTwo).getValue());
        assertThat(result.getValue(), is(equalTo(66)));
    }

    @Test
    public void simpleMultiplicationTestNegativeOperand(){
        Operand operandOne =  new Operand(-25);
        Operand operandTwo =  new Operand(5);
        MultiplyOperator multiplyOperator =  new MultiplyOperator();
        Operand result = new Operand(multiplyOperator.execute(operandTwo,operandOne).getValue());
        assertThat(result.getValue(), is(equalTo(-125)));
    }

    @Test
    public void simpleMultiplicationTestNegativeOperandReversed(){
        Operand operandOne =  new Operand(25);
        Operand operandTwo =  new Operand(-5);
        MultiplyOperator multiplyOperator =  new MultiplyOperator();
        Operand result = new Operand(multiplyOperator.execute(operandTwo,operandOne).getValue());
        assertThat(result.getValue(), is(equalTo(-125)));
    }

    @Test
    public void multiplicationPriorityTest(){
        assertThat((new MultiplyOperator().priority()), is(equalTo(2)));
    }
}
