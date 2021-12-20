import edu.csc413.calculator.evaluator.Operand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;


import static org.hamcrest.core.Is.isA;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@DisplayName("Operand Test")
public class OperandTest {

    @Test
    public void getValueTest(){
        Operand operand = new Operand(6);
        /* note the use of is() here is purely done for readability.
        the is function does nothing in terms of functionality.
         */
        assertThat(operand.getValue(), is(equalTo(6)));
    }

    @Test
    public void getValueTypeTest(){
        Operand operand = new Operand(6);
        /* the isA() function is used to test types,
        we are saying is the return value from getValue() actually
        an Integer.
         */
        assertThat(operand.getValue(), isA(Integer.class));
    }
    
    @ParameterizedTest
    @CsvSource({
            "13, true",
            "c, false",
            "*, false",
            "430., false",
            "430.456, false",
            "343324fd, false"
    })
    public void operandCheckTest(String testValue, boolean expectedResult){
        assertThat(Operand.check((testValue)), is(equalTo(expectedResult)));
    }
}
