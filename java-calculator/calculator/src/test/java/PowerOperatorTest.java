import edu.csc413.calculator.evaluator.Operand;
import edu.csc413.calculator.operators.PowerOperator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Power Test")
public class PowerOperatorTest {

    @Test
    public void simplePowerTest(){
        Operand operandOne =  new Operand(2);
        Operand operandTwo =  new Operand(3);
        PowerOperator powerOperator =  new PowerOperator();
        Operand res = new Operand(powerOperator.execute(operandOne,operandTwo).getValue());
        assertThat(res.getValue(), is(equalTo(8)));
    }

    @Test
    public void simplePowerTestNegativeBase(){
        Operand operandOne =  new Operand(-2);
        Operand operandTwo =  new Operand(8);
        PowerOperator powerOperator =  new PowerOperator();
        Operand res = new Operand(powerOperator.execute(operandOne,operandTwo).getValue());
        assertThat(res.getValue(), is(equalTo(256)));
    }


    @Test
    public void powerPriorityTest(){
        assertThat((new PowerOperator().priority()), is(equalTo(3)));
    }
}
