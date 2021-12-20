import edu.csc413.calculator.evaluator.Operand;
import edu.csc413.calculator.operators.DivideOperator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Division Test")
public class DivideOperatorTest {

    @Test
    public void simpleDivisionTest(){
        Operand operandOne =  new Operand(6);
        Operand operandTwo =  new Operand(11);
        DivideOperator divideOperator =  new DivideOperator();
        Operand result = new Operand(divideOperator.execute(operandOne,operandTwo).getValue());
        assertThat(result.getValue() , is(equalTo(0)));
    }

    @Test
    public void simpleDivisionTestReversedOperands(){
        Operand operandOne =  new Operand(11);
        Operand operandTwo =  new Operand(6);
        DivideOperator divideOperator =  new DivideOperator();
        Operand result = new Operand(divideOperator.execute(operandOne,operandTwo).getValue());
        assertThat( result.getValue(), is(equalTo(1)));
    }

    @Test
    public void simpleDivisionTestEvenlyDivisible() {
        Operand operandOne =  new Operand(25);
        Operand operandTwo =  new Operand(5);
        DivideOperator divideOperator =  new DivideOperator();
        Operand result = new Operand(divideOperator.execute(operandOne,operandTwo).getValue());
        assertThat(result.getValue() ,  is(equalTo(5)));
    }

    @Test
    public void simpleDivisionTestNegativeResult (){
        Operand operandOne =  new Operand(25);
        Operand operandTwo =  new Operand(-5);
        DivideOperator divideOperator =  new DivideOperator();
        Operand result = new Operand(divideOperator.execute(operandOne,operandTwo).getValue());
        assertThat( result.getValue() ,  is(equalTo(-5)));
    }

    @Test
    public void divisionPriorityTest(){
        assertThat((new DivideOperator().priority()), equalTo(2));
    }
}
