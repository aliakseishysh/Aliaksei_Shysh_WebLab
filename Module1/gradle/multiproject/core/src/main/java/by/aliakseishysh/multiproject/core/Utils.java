package by.aliakseishysh.multiproject.core;

import java.util.Arrays;
import java.util.List;

import by.aliakseishysh.utils.StringUtils;

public class Utils {
    public static boolean isAllPositiveNumbers(String ... str) {
        List<String> numbers = Arrays.asList(str);
        return numbers.stream().allMatch(StringUtils::isPositiveNumber);
    }

}
