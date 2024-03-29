package slobodan.siuvs2.shared;

import java.math.BigDecimal;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
public class NumberHelper {

    public static String getValidNumberString(String text) {
        return text
                .replaceAll("[\\.\\,](?=.*[\\.\\,])", "")
                .replaceAll("[\\.\\,]", ".")
                .replaceAll("[^\\d\\.]", "");
    }

    public static String getDisplayNumber(String text) {
        if (text == null) {
            return "/";
        }
        return text
                .replaceAll("[\\.\\,](?=.*[\\.\\,])", "")
                .replaceAll("[\\.\\,]", ",");
    }

    public static BigDecimal addNumberStringToNumber(BigDecimal number, String text) {
        String validNumberString = getValidNumberString(text);
        try {
            BigDecimal addValue = new BigDecimal(validNumberString);
            return number.add(addValue);
        } catch (NumberFormatException e) {
            //IF INVALID NUMBER FORMAT - IGNORE IT
        }
        return number;
    }

    public static boolean isValidNumber(String text) {
        return text.matches("^\\d+[\\.,]?\\d*$");
    }

}
