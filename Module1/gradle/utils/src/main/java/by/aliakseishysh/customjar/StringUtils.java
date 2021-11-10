package by.aliakseishysh.customjar;
import org.apache.commons.lang3.math.NumberUtils;

public class StringUtils {
    public static boolean isPositiveNumber(String str) {
        boolean result = false;
        if (NumberUtils.isCreatable(str)) {
            result = NumberUtils.createNumber(str).doubleValue() > 0;
        }
        return result;
    }
}
