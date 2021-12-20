import edu.csc413.calculator.evaluator.Operand;
import edu.csc413.calculator.operators.AddOperator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@DisplayName("Addition Test")
public class AddOperatorTest {

    @Test
    public void simpleAdditionTest(){
        Operand operandOne =  new Operand(6);
        Operand operandTwo =  new Operand(11);
        AddOperator addOperator =  new AddOperator();
        Operand result = new Operand(addOperator.execute(operandOne,operandTwo).getValue());
        assertThat(result.getValue(), equalTo(17));
    }

    @Test
    public void simpleAdditionTestReverseOperands(){
        Operand operandOne =  new Operand(11);
        Operand operandTwo =  new Operand(6);
        AddOperator addOperator =  new AddOperator();
        Operand result = new Operand(addOperator.execute(operandOne,operandTwo).getValue());
        //assertEquals(17, result.getValue());
        assertThat(result.getValue(), is(equalTo(17)));
    }

    @Test
    public void simpleAdditionTestWithNegativeOperand(){
        Operand operandOne =  new Operand(6);
        Operand operandTwo =  new Operand(-11);
        AddOperator addOperator =  new AddOperator();
        Operand result = new Operand(addOperator.execute(operandOne,operandTwo).getValue());
        assertThat(result.getValue(), is(equalTo(-5)));
    }

    @Test
    public void simpleAdditionTestWithNegativeOperandsReversed(){
        Operand operandOne =  new Operand(-11);
        Operand operandTwo =  new Operand(6);
        AddOperator addOperator =  new AddOperator();
        Operand result = new Operand(addOperator.execute(operandOne,operandTwo).getValue());
        assertThat(result.getValue(), is(equalTo(-5)));
    }

    @Test
    public void additionPriorityTest(){
        assertThat((new AddOperator().priority()), equalTo(1));
    }
}