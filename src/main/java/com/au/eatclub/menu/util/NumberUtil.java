package com.au.eatclub.menu.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberUtil {
    public static String formatPrice(BigDecimal price) {
        return price.setScale(2, RoundingMode.HALF_UP).toPlainString();
    }
}
