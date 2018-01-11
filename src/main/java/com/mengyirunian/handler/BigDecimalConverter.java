package com.mengyirunian.handler;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Format;

public class BigDecimalConverter {

    /**
     * bigdecimal 展现后4位
     *
     */
    public static class BigDecimal2StringConverter4Bits extends StdConverter<BigDecimal, String> {

        private final Format df   = new DecimalFormat("0.0000");
        private static final String ZERO = "0.0000";

        @Override
        public String convert(BigDecimal value) {
            if (null == value) {
                return ZERO;
            }
            BigDecimal newValue = value.setScale(4, BigDecimal.ROUND_HALF_UP);
            return df.format(newValue);
        }
    }

    /**
     * bigdecimal 展现后2位
     *
     * @author xiaofeng.zhouxf
     * @version $Id: DefaultBigDecimalConverter.java, v 0.1 2015年1月3日 上午11:29:44 jason Exp $
     */
    public static class BigDecimal2StringConverter2Bits extends StdConverter<BigDecimal, String> {

        private final Format df   = new DecimalFormat("0.00");
        private static final String ZERO = "0.00";

        @Override
        public String convert(BigDecimal value) {
            if (null == value) {
                return ZERO;
            }
            BigDecimal newValue = value.setScale(2, BigDecimal.ROUND_HALF_UP);
            return df.format(newValue);
        }
    }
}
