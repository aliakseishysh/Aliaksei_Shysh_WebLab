package by.aliakseishysh.customjar;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class StringUtilsTest {

    private static Stream<Arguments> provideData() {
        return Stream.of(
                Arguments.of("1", true),
                Arguments.of("-1", false),
                Arguments.of("0f", false),
                Arguments.of("zzzzzzzzz", false),
                Arguments.of(null, false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideData")
    public void validationTest(String string, boolean expected) {
        boolean actual = StringUtils.isPositiveNumber(string);
        assertEquals(expected, actual);
    }
}
