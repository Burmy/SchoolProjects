
import edu.csc413.calculator.evaluator.Evaluator;
import edu.csc413.calculator.exceptions.InvalidTokenException;
import org.junit.jupiter.api.*;

import java.util.EmptyStackException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Evaluator Test")
public class EvaluatorTest {

    private Evaluator evaluator;

    @BeforeEach
    void setup() {
        evaluator = new Evaluator();
    }

    @AfterEach
    void tearDown() { evaluator = null; }

    @Test
    void basicExpressionSingleOperatorTest(){
        String testExpression = "1+2";
        int expectedResult = 3;
        int actualResult;
        try{
            actualResult = this.evaluator.evaluateExpression(testExpression);
            //assertEquals(3,actualResult);
            assertThat(actualResult, is(equalTo(expectedResult)));
        }catch (Exception ex){
            fail();
        }

    }

    @Test
    void basicExpressionDivisionNumeratorLessThanDenominatorTest(){
        String testExpression = "1/2";
        int expectedResult = 0;
        int actualResult;
        try{
            actualResult = this.evaluator.evaluateExpression(testExpression);
            assertThat(actualResult, is(equalTo(expectedResult)));
        }catch (Exception ex){
            fail();
        }
    }

    @Test
    void basicExpressionMultipleOperatorTest(){
        String testExpression = "1+2*3";
        int expectedResult = 7;
        int actualResult;
        try{
            actualResult = this.evaluator.evaluateExpression(testExpression);
            assertThat(actualResult, is(equalTo(expectedResult)));
        }catch (Exception ex){
            fail();
        }
    }


    @Test
    void basicExpressionParensOnLeftTest(){
        String testExpression = "(1+2)*3";
        int expectedResult = 9;
        int actualResult;
        try{
            actualResult = this.evaluator.evaluateExpression(testExpression);
            assertThat(actualResult, is(equalTo(expectedResult)));
        }catch (Exception ex){
            fail();
        }
    }

    @Test
    void basicExpressionParensOnRightTest(){
        String testExpression = "1+(2*3)";
        int expectedResult = 7;
        int actualResult;
        try{
            actualResult = this.evaluator.evaluateExpression(testExpression);
            assertThat(actualResult, is(equalTo(expectedResult)));
        }catch (Exception ex){
            fail();
        }
    }

    @Test
    void mediumExpressionParensAndOperatorsTest(){
        String testExpression = "2-(3/10)+2-5";
        int expectedResult = -1;
        int actualResult;
        try{
            actualResult = this.evaluator.evaluateExpression(testExpression);
            assertThat(actualResult, is(equalTo(expectedResult)));
        }catch (Exception ex){
            fail();
        }
    }

    @Test
    void mediumExpressionParensAsSubExpressionTest(){
        String testExpression = "(6-12*2)/3";
        int expectedResult = -6;
        int actualResult;
        try{
            actualResult = this.evaluator.evaluateExpression(testExpression);
            assertThat(actualResult, is(equalTo(expectedResult)));
        }catch (Exception ex){
            fail();
        }
    }


    @Test
    void mediumExpressionPowerTest(){
        String testExpression = "3^2";
        int expectedResult = 9;
        int actualResult;
        try{
            actualResult = this.evaluator.evaluateExpression(testExpression);
            assertThat(actualResult, is(equalTo(expectedResult)));
        }catch (Exception ex){
            fail();
        }
    }

    @Test
    void mediumExpressionPowerWithMultipleOperators(){
        String testExpression = "3^2/2";
        int expectedResult = 4;
        int actualResult;
        try{
            actualResult = this.evaluator.evaluateExpression(testExpression);
            assertThat(actualResult, is(equalTo(expectedResult)));
        }catch (Exception ex){
            fail();
        }
    }

    @Test
    void mediumExpressionPowerWithParensTest(){
        String testExpression = "3^2/2 +(4+5)";
        int expectedResult = 13;
        int actualResult;
        try{
            actualResult = this.evaluator.evaluateExpression(testExpression);
            assertThat(actualResult, is(equalTo(expectedResult)));
        }catch (Exception ex){
            fail();
        }
    }

    @Test
    void mediumExpressionWithMultipleParensTest(){
        String testExpression = "3^2 + (2^4) +(4+5)";
        int expectedResult = 34;
        int actualResult;
        try{
            actualResult = this.evaluator.evaluateExpression(testExpression);
            assertThat(actualResult, is(equalTo(expectedResult)));
        }catch (Exception ex){
            fail();
        }
    }

    @Test
    void difficultExpressionMixtureOfOperatorsTest(){
        String testExpression = "3+2-9+8*2 + (3+9-8/2)";
        int expectedResult = 20;
        int actualResult;
        try{
            actualResult = this.evaluator.evaluateExpression(testExpression);
            assertThat(actualResult, is(equalTo(expectedResult)));
        }catch (Exception ex){
            fail();
        }
    }

    @Test
    void veryDifficultExpressionMixtureOfOperatorsNestedParensTest() {
        String testExpression = "2+3-5*((2-3)*2-5*2+3*(2-3-5-5*6)+4/2)*2-9";
        int expectedResult = 1176;
        int actualResult;
        try{
            actualResult = this.evaluator.evaluateExpression(testExpression);
            assertThat(actualResult, is(equalTo(expectedResult)));
        }catch (Exception ex){
            fail();
        }

    }


    @Test
    void unbalanceExpressionProducesNullPointerExceptionTest() {
        String testExpression = "2+3-5*((2-3)*2-5*2+3*(2-3-5-5*6)+4/2*2-9";
        //no expected result since we are looking for an exception here.
        assertThrows(NullPointerException.class, () -> {
            this.evaluator.evaluateExpression(testExpression);
        });

    }

    @Test
    void invalidOperatorExpressionProducesEmptyStackExceptionTest() {
        String testExpression = "2+3**3";
        //no expected result since we are looking for an exception here.
        assertThrows(EmptyStackException.class, () -> {
            this.evaluator.evaluateExpression(testExpression);
        });
    }

    @Test
    void invalidOperatorExpressionProducesInvalidTokenExceptionTest() {
        String testExpression = "2+3c*3";
        //no expected result since we are looking for an exception here.
        assertThrows(InvalidTokenException.class, () -> {
            this.evaluator.evaluateExpression(testExpression);
        });
    }

}
