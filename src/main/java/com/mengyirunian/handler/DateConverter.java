package com.mengyirunian.handler;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.mengyirunian.utils.DateUtils;

import java.util.Date;

/**
 * Created by zhuhezan on 2017/8/11.
 *
 * 日期转换器
 */
public class DateConverter {

    public static class Date2LocalDateTimeString extends StdConverter<Date, String> {
        @Override
        public String convert(Date date) {
            if (date == null){
                return "";
            }
            return DateUtils.getDate(date, "yyyy-MM-dd HH:mm:ss");
        }
    }


    public static class Date2LocalDateString extends StdConverter<Date, String> {
        @Override
        public String convert(Date date) {
            if (date == null){
                return "";
            }
            return DateUtils.getDate(date, "yyyy-MM-dd");
        }
    }
}
