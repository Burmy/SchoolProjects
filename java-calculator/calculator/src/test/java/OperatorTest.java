import edu.csc413.calculator.operators.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.PipedWriter;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OperatorTest {

    @ParameterizedTest
    @CsvSource({
            "+, true",
            "-, true",
            "*, true",
            "^, true",
            "/, true",
            "156, false",
            "156., false",
            "X, false",
            "**, false"
    })
    void operatorCheckTest(String operatorToken, Boolean expectedResult ) {
        assertThat(Operator.check(operatorToken), is(equalTo(expectedResult)));
    }

    private static Stream<Arguments> getOperatorArgumentStream() {
        return Stream.of(
                 Arguments.of("+", AddOperator.class),
                 Arguments.of("-", SubtractOperator.class),
                 Arguments.of("/", DivideOperator.class),
                 Arguments.of("*", MultiplyOperator.class),
                 Arguments.of("^", PowerOperator.class)
               );
    }
    @ParameterizedTest
    @MethodSource("getOperatorArgumentStream")
    void getOperatorReturnTypeTest(String operatorToken, Class<Operator> expectedReturnOperatorType) {
        assertThat(Operator.getOperator(operatorToken), isA(expectedReturnOperatorType));
    }
}
