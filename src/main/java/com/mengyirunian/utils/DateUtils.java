package com.mengyirunian.utils;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
public abstract class
DateUtils extends PropertyEditorSupport {

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYYMM = "yyyy-MM";
    public static final String REGX = "\\d{4}-\\d{2}-\\d{2}";
    public static final long ONE_DAY = 24 * 60 * 60 * 1000L;

    public DateUtils() {
    }

    public static Date getCurrentDate() {
        return DateTime.now().toDate();
    }

    @Override
    public void setAsText(String text) {
        SimpleDateFormat frm = new SimpleDateFormat(DATE_FORMAT);

        try {
            Date exp = frm.parse(text);
            this.setValue(exp);
        } catch (Exception var4) {
            log.error(String.format("parse date error, dateText:%s", text), var4);
        }

    }

    public static Date parse(String text) throws ParseException {
        SimpleDateFormat frm = new SimpleDateFormat(DATE_FORMAT);
        return frm.parse(text);
    }

    public static String parse(Date date) {
        if (date == null) {
            return "";
        } else {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
            return format.format(date);
        }
    }

    public static String parse(Date date, String formatString) {
        if (date == null) {
            return "";
        } else {
            SimpleDateFormat format = new SimpleDateFormat(formatString);
            return format.format(date);
        }
    }

    public static Calendar getDateZeroCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(13, 0);
        cal.set(12, 0);
        cal.set(14, 0);
        cal.set(11, 0);
        return cal;
    }

    public static Date add(Date date, int zoom, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(zoom, amount);
        return cal.getTime();
    }

    public static Date getDate(String date) {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        return getDate((String) date, DATE_TIME_FORMAT);
    }

    public static Date getDate(String date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);

        try {
            return df.parse(date);
        } catch (ParseException var4) {
            return null;
        }
    }

    public static int getDayCount(Date date1, Date date2) {
        Calendar cal1 = getDateZeroCalendar(date1);
        Calendar cal2 = getDateZeroCalendar(date2);
        long betweenDays = Math.abs((cal1.getTime().getTime() - cal2.getTime().getTime()) / 86400000L);
        return Integer.parseInt(String.valueOf(betweenDays));
    }

    public static String getDate(Date date) {
        return getDate((Date) date, DATE_TIME_FORMAT);
    }

    public static String getDate(Date date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    public static int getDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(7);
    }

    public static int getDayNumber(Date date) {
        int week = getDay(date) - 1;
        return week == 0 ? 7 : week;
    }

    public static Date getLastMonthStartDay(Date date, int lastMonthCount) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
            calendar.set(5, 1);
            calendar.set(10, 0);
            calendar.set(12, 0);
            calendar.set(13, 0);
            int month = calendar.get(2);
            calendar.set(2, month - lastMonthCount);
            return calendar.getTime();
        } else {
            return null;
        }
    }

    public static Date getDayStartOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(5, calendar.getActualMinimum(5));
        calendar.set(10, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        return calendar.getTime();
    }

    public static boolean isToday(Date date) {
        Date d = new Date();
        return getDate((Date) date, YYYYMMDD).equals(getDate((Date) d, YYYYMMDD));
    }

    public static Date getMaxMonthDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(5, calendar.getActualMaximum(5));
        calendar.set(10, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        return calendar.getTime();
    }

    public static List<Long[]> splitTime(long start, long end) {
        Preconditions.checkArgument(start > 0L && end > start);
        String startStr = parse(new Date(start));
        String endStr = parse(new Date(end));
        Preconditions.checkArgument(!StringUtils.isEmpty(startStr));
        Preconditions.checkArgument(!StringUtils.isEmpty(endStr));

        Date startDate = getDate(startStr, DATE_FORMAT);
        if (startDate != null) {
            start = startDate.getTime();
        }
        Date endDate = getDate(endStr, DATE_FORMAT);
        if (endDate != null) {
            end = endDate.getTime();
        }
        ArrayList result = Lists.newArrayList();

        for (int i = 0; i < 2147483647; ++i) {
            long endTime = getMaxMonthDate(new Date(start)).getTime();
            if (start > end) {
                break;
            }

            Long[] timePair;
            if (endTime > end) {
                timePair = new Long[]{Long.valueOf(start), Long.valueOf(end)};
                result.add(timePair);
                break;
            }

            timePair = new Long[]{Long.valueOf(start), Long.valueOf(endTime)};
            result.add(timePair);
            start = getLastMonthStartDay(new Date(start), -1).getTime();
        }

        return result;
    }

    public static Date getPreWeekend(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        while (cal.get(7) != 1) {
            cal.add(7, -1);
        }

        return cal.getTime();
    }

    public static String toString(Date currentDate, String dateTimeFormat) {
        DateTime dateTime = new DateTime(currentDate);
        return dateTime.toString(dateTimeFormat);
    }

    /**
     * 返回2个给定日期相差天数
     *
     * @param day1 格式为String(yyyy-MM-dd)类型或者Date类型
     * @param day2 格式为String(yyyy-MM-dd)类型或者Date类型
     * @return
     */
    public static Integer getDayGapInTwoDate(Object day1, Object day2) {
        if (day1 == null || day2 == null)
            return null;
        String day1Str;
        String day2Str;
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        try {
            if (day1 instanceof Date) {
                day1Str = sdf.format((Date) day1);
            } else {
                day1Str = (String) day1;
                if (!((String) day1).matches(REGX)) {
                    return null;
                }
            }
        } catch (Exception e) {
            log.error("getDayGapInTwoDate error", e);
            return null;
        }

        try {
            if (day2 instanceof Date) {
                day2Str = sdf.format((Date) day2);
            } else {
                day2Str = (String) day2;
                if (!((String) day2).matches(REGX)) {
                    return null;
                }
            }
        } catch (Exception e) {
            log.error("getDayGapInTwoDate error", e);
            return null;
        }

        DateTimeFormatter format = DateTimeFormat.forPattern(DATE_FORMAT);
        DateTime day1time = DateTime.parse(day1Str, format);
        DateTime day2time = DateTime.parse(day2Str, format);

        return new Period(day1time, day2time, PeriodType.days()).getDays();
    }

    /**
     * 日期加减，返回计算后的日期
     *
     * @param day 格式为String(yyyy-MM-dd)类型或者Date类型，
     * @param num 整型
     * @return
     */
    public static String dateAdd(Object day, int num) {
        if (day == null)
            return null;
        String dayStr;
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        try {
            if (day instanceof Date) {
                dayStr = sdf.format((Date) day);
            } else {
                dayStr = (String) day;
                if (!((String) day).matches(REGX)) {
                    return null;
                }
            }
        } catch (Exception e) {
            log.error("dateAdd error", e);
            return null;
        }

        DateTimeFormatter format = DateTimeFormat.forPattern(DATE_FORMAT);
        DateTime daytime = DateTime.parse(dayStr, format);
        DateTime result = daytime.plusDays(num);
        return result.toString(format);
    }

    public static Date addDay(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, day);

        return c.getTime();
    }

    /**
     * 日期加减，精确到秒,返回计算后的日期
     *
     * @param day 格式为String(yyyy-MM-dd HH:mm:ss)类型或者Date类型，
     * @param num 整型,增加的变量
     * @return
     */
    public static String dateTimeAdd(Object day, int num) {
        if (day == null)
            return null;
        String dayStr;
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
        try {
            if (day instanceof Date) {
                dayStr = sdf.format((Date) day);
            } else {
                dayStr = (String) day;
                if (!dayStr.matches("\\d{4}-\\d{2}-\\d{2} \\d{4}:\\d{2}:\\d{2}")) {
                    return null;
                }
            }
        } catch (Exception e) {
            log.error("dateTimeAdd error", e);
            return null;
        }

        DateTimeFormatter dformat = DateTimeFormat.forPattern(DATE_TIME_FORMAT);
        DateTime daytime = DateTime.parse(dayStr, dformat);
        DateTime result = daytime.plusDays(num);
        return result.toString(DATE_TIME_FORMAT);
    }

    /**
     * 月份加减，返回计算后的日期
     *
     * @param day 格式为String(yyyy-MM)类型或者Date类型，
     * @param num 整型
     * @return
     */
    public static String monthAdd(Object day, int num) {
        if (day == null)
            return null;
        String dayStr;
        SimpleDateFormat sdf = new SimpleDateFormat(YYYYMM);
        try {
            if (day instanceof Date) {
                dayStr = sdf.format((Date) day);
            } else {
                dayStr = (String) day;
                if (!((String) day).matches("\\d{4}-\\d{2}")) {
                    return null;
                }
            }
        } catch (Exception e) {
            log.error("monthAdd error", e);
            return null;
        }

        DateTimeFormatter format = DateTimeFormat.forPattern(YYYYMM);
        DateTime daytime = DateTime.parse(dayStr, format);
        DateTime result = daytime.plusMonths(num);
        return result.toString(format);
    }

    public static String dayAdd(Object day, int num, String p) {
        if (day == null)
            return null;
        String dayStr;
        SimpleDateFormat sdf = new SimpleDateFormat(p);
        try {
            if (day instanceof Date) {
                dayStr = sdf.format((Date) day);
            } else {
                dayStr = (String) day;
                if (!((String) day).matches("\\d{4}-\\d{2}")) {
                    return null;
                }
            }
        } catch (Exception e) {
            log.error("dayAdd error ", e);
            return null;
        }

        DateTimeFormatter format = DateTimeFormat.forPattern(p);
        DateTime daytime = DateTime.parse(dayStr, format);
        DateTime result = daytime.plusMonths(num);
        return result.toString(format);
    }

    /**
     * 分钟加减，返回计算后的日期
     *
     * @param day 格式为String(yyyy-MM-dd HH:mm:ss)类型或者Date类型，
     * @param num 加减数量
     * @return
     */
    public static String dateAddMinutes(Object day, int num) {
        if (day == null)
            return null;
        String dayStr;
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
        try {
            if (day instanceof Date) {
                dayStr = sdf.format((Date) day);
            } else {
                dayStr = (String) day;
                if (!((String) day).matches("\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}")) {
                    return null;
                }
            }
        } catch (Exception e) {
            log.error("dateAddMinutes error ", e);
            return null;
        }

        DateTimeFormatter format = DateTimeFormat.forPattern(DATE_TIME_FORMAT);
        DateTime daytime = DateTime.parse(dayStr, format);
        DateTime result = daytime.plusMinutes(num);
        return result.toString(format);
    }

    public static Date getBeforeDate(Date date, Integer beforeDay) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.DATE);
        cal.set(Calendar.DATE, day - beforeDay);
        return cal.getTime();
    }

    /**
     * 得到本月最后一天的日期
     *
     * @param day 格式为String(yyyy-MM)类型或者Date类型
     * @return Date
     */
    public static Date getLastDayOfMonth(Object day) {
        if (day == null)
            return null;
        Date date = null;
        if (day instanceof String) {
            SimpleDateFormat sdf = new SimpleDateFormat(YYYYMM);
            try {
                date = sdf.parse((String) day);
            } catch (ParseException e) {
                log.error("getLastDayOfMonth", e);
                return null;
            }
        } else if (day instanceof Date) {
            date = (Date) day;
        }

        Calendar cDay = Calendar.getInstance();
        cDay.setTime(date);
        cDay.set(Calendar.DAY_OF_MONTH, cDay.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cDay.getTime();
    }

    /**
     * 根据2个区间拿到此区间所有的日期，格式如2014-01-01
     *
     * @param from
     * @param to
     * @return
     */
    public static List<String> getDateListBetweenPeriod(String from, String to) {
        List<String> resutlist = Lists.newArrayList();
        if (from == null || to == null) {
            return resutlist;
        }
        if (from.compareTo(to) > 0) {
            return resutlist;
        }
        DateTimeFormatter format = DateTimeFormat.forPattern(DATE_FORMAT);
        DateTime daytime = DateTime.parse(from, format);
        int i = 0;
        while (true) {
            String result = daytime.plusDays(i).toString(format);
            resutlist.add(result);
            if (result.equals(to))
                break;
            i++;
        }
        return resutlist;
    }

    /**
     * 根据日期拿到所有小时，格式如2014-01-01 01
     *
     * @return
     */
    public static List<String> getHourListByDay(String date) {
        List<String> result = Lists.newArrayList();
        if (!(date == null || !date.matches(REGX))) {
            result.add(date + " 00");
            result.add(date + " 01");
            result.add(date + " 02");
            result.add(date + " 03");
            result.add(date + " 04");
            result.add(date + " 05");
            result.add(date + " 06");
            result.add(date + " 07");
            result.add(date + " 08");
            result.add(date + " 09");
            result.add(date + " 10");
            result.add(date + " 11");
            result.add(date + " 12");
            result.add(date + " 13");
            result.add(date + " 14");
            result.add(date + " 15");
            result.add(date + " 16");
            result.add(date + " 17");
            result.add(date + " 18");
            result.add(date + " 19");
            result.add(date + " 20");
            result.add(date + " 21");
            result.add(date + " 22");
            result.add(date + " 23");
        }
        return result;
    }

    /**
     * 返回date日期中月份的倒数num天的日期,比如传入2015-01和3,那么返回2015-1-29
     *
     * @param date
     * @param num
     * @return
     */
    public static String getDateInMonth(Object date, int num) {
        Date maxDateInMonth = getLastDayOfMonth(date);
        String result = dateTimeAdd(maxDateInMonth, 0 - (num - 1));
        return StringUtils.isEmpty(result) ? "" : result.substring(0, 10);

    }

    public static Date getStartDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static Date getEndDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTime();
    }

    public static Date getFirstSecondDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 1);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 获得两个时间中间间隔的日期
     * 如: 传入2016-06-01, 2016-06-03,返回的是2016-06-01,2016-06-02,2016-06-03
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    public static List<String> getIntervalDay(Date beginTime, Date endTime) {
        int day = getDayCount(beginTime, endTime);
        List<String> res = Lists.newArrayList();
        if (day >= 0) {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            Calendar calendar = Calendar.getInstance();
            for (int i = 0; i <= day; i++) {
                calendar.setTime(beginTime);
                calendar.add(Calendar.DAY_OF_MONTH, i);
                String oneDay = sdf.format(calendar.getTime());
                res.add(oneDay);
            }
        }
        return res;
    }

    //-----------------------------------------------------------------------

    /**
     * Adds a number of years to a date returning a new object.
     * The original {@code Date} is unchanged.
     *
     * @param date   the date, not null
     * @param amount the amount to add, may be negative
     * @return the new {@code Date} with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    public static Date addYears(final Date date, final int amount) {
        return toDoAddYears(date, Calendar.YEAR, amount);
    }

    //-----------------------------------------------------------------------

    /**
     * Adds to a date returning a new object.
     * The original {@code Date} is unchanged.
     *
     * @param date          the date, not null
     * @param calendarField the calendar field to add to
     * @param amount        the amount to add, may be negative
     * @return the new {@code Date} with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    private static Date toDoAddYears(final Date date, final int calendarField, final int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }

    public static Date addMinutes(Date date, int mins) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, mins);
        return c.getTime();
    }

    /**
     * 功能描述: 获取与某天间隔多少天的零点
     *
     * @param someDay：某天
     * @param interval：间隔天数，负数表示在某天之前，正数表示在某天之后
     * @return
     * @see [相关类/方法](可选)
     * @since V1.0
     */
    public static Date getDayStartIntervalToSomeDay(Date someDay, int interval) {

        Calendar cal = Calendar.getInstance();

        cal.setTime(someDay);
        cal.add(Calendar.DAY_OF_MONTH, interval);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    /**
     * The very beginning of today.
     *
     * @return
     */
    public static Date todayStart() {
        return dayStart(new Date());
    }

    public static Date dayStart(Date someday) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(someday);

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }


    /**
     * 时间间隔(天数),date1-date2
     *
     * @param date1
     * @param date2
     * @return 负数表示date1在date2前
     */
    public static int intervalDays(Date date1, Date date2) {
        Calendar cNow = org.apache.commons.lang3.time.DateUtils.toCalendar(dayStart(date1));
        Calendar cReturnDate = org.apache.commons.lang3.time.DateUtils.toCalendar(dayStart(date2));
        long todayMs = cNow.getTimeInMillis();
        long returnMs = cReturnDate.getTimeInMillis();
        long intervalMs = todayMs - returnMs;
        return (int) (intervalMs / ONE_DAY);
    }

}
