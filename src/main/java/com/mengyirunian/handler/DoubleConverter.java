package com.mengyirunian.handler;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Format;

public class DoubleConverter {

    public static class Double2DoubleWith2Scale extends StdConverter<Double, Double> {

        private final Format df = new DecimalFormat("0.00");
        private static final Double ZERO = 0.00D;

        @Override
        public Double convert(Double value) {
            if (null == value) {
                return ZERO;
            }
            BigDecimal newValue = new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP);
            return newValue.doubleValue();
        }
    }
}
