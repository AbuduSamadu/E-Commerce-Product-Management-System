package abudu.product.utils;

import java.util.regex.Pattern;

public class ValidationUtil {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-\\.]+@[\\w-\\.]+\\.[a-zA-Z]{2,}$");

    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidPrice(Double price) {
        return price != null && price > 0;
    }

    public static boolean isValidQuantity(Integer quantity) {
        return quantity != null && quantity >= 0;
    }
}
