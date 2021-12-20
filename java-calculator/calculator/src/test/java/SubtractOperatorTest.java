import edu.csc413.calculator.evaluator.Operand;
import edu.csc413.calculator.operators.SubtractOperator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Subtraction Test")
public class SubtractOperatorTest {

    @Test
    public void simpleSubtractionTest(){
        Operand operatorOne =  new Operand(6);
        Operand operatorTwo =  new Operand(11);
        SubtractOperator so =  new SubtractOperator();
        Operand result = new Operand(so.execute(operatorOne,operatorTwo).getValue());
        assertThat( result.getValue(), is(equalTo(-5)));
    }

    @Test
    public void simpleSubtractionTestReversedOperands(){
        Operand operatorOne =  new Operand(11);
        Operand operatorTwo =  new Operand(6);
        SubtractOperator so =  new SubtractOperator();
        Operand result = new Operand(so.execute(operatorOne,operatorTwo).getValue());
        assertThat( result.getValue(), is(equalTo(5)));
    }

    @Test
    public void simpleSubtractionTestNegativeOperands(){
        Operand operatorOne =  new Operand(-11);
        Operand operatorTwo =  new Operand(6);
        SubtractOperator so =  new SubtractOperator();
        Operand result = new Operand(so.execute(operatorOne,operatorTwo).getValue());
        assertThat( result.getValue(), is(equalTo(-17)));
    }

    @Test
    public void simpleSubtractionTestNegativeOperandsReversed(){
        Operand operandOne =  new Operand(11);
        Operand operatorTwo =  new Operand(-6);
        SubtractOperator so =  new SubtractOperator();
        Operand result = new Operand(so.execute(operandOne,operatorTwo).getValue());
        assertThat( result.getValue(), is(equalTo(17)));
    }

    @Test
    public void subtractionPriorityTest(){
        assertThat((new SubtractOperator().priority()), is(equalTo(1)));
    }
}
