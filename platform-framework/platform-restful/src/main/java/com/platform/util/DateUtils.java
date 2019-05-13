package com.platform.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

public class DateUtils{
    public static String F19 = "yyyy-MM-dd HH:mm:ss";

    public static String F14 = "yyyyMMddHHmmss";

    public static String F10 = "yyyy-MM-dd";

    public static String F8 = "yyyyMMdd";

    public static String[] dataStringFormats = {F8, F10, F14, F19, "yyyy/MM/dd", "yyyy/MM/dd HH:mm", "yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd HH:mm"};

    /**
     * 将字符串转换为long类型的值(不包含-符号)
     * @param dateString 2016-10-12
     * @return 20161012
     */
    public static long stringToDateLong(String dateString) {
        String[] dates = dateString.split("-");
        return Long.valueOf(dates[0] + dates[1] + dates[2]);
    }

    /**
     * 将日期转化为默认的格式显示
     * @param date Date实例
     * @return 2017-06-06
     */
    public static String dateToString(Date date) {
        return dateToString(date, F10);
    }

    /**
     * @param date Date实例
     * @param format yyyy-MM-dd
     * @return 2017-06-06
     */
    public static String dateToString(Date date, String format) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sf = new SimpleDateFormat(format);
        return sf.format(date);
    }

    /**
     * 时间戳转默认字符串日期
     * @param time 1496739850253
     * @return 2017-06-06
     */
    public static String longToString(long time) {
        return longToString(time, F10);
    }

    /**
     * 时间戳转字符串日期（格式可以自己选择）
     * @param time 1496739850253
     * @param format yyyy-MM-dd
     * @return 2017-06-06
     */
    public static String longToString(long time, String format) {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        return sf.format(new Date(time));
    }

    /**
     * 在指定日期上加上一定天数获得新的日期
     * @param day 2016-06-06
     * @param addDay 10
     * @return 2016-06-16
     */
    public static String getNextDay(String day, int addDay) {
        Calendar calendar = getCalendar(day);
        calendar.add(Calendar.DAY_OF_MONTH, addDay);
        return getDateString(calendar);
    }

    /**
     * 获取当前时间（包含小时、分、秒）
     * @return 2016-06-06 10:20:10
     */
    public static String getCurrTime() {
        return dateToString(new Date(), F19);
    }

    /**
     * 获取当前日期（不包含小时、分、秒）
     * @return 2016-06-06
     */
    public static String getCurrDate() {
        return dateToString(new Date(), F10);
    }

    /**
     * 获取日期相距天数
     * @param startDate 2016-06-06
     * @param endDate 2016-06-10
     * @return int 4
     */
    public static int getCompareDate(String startDate, String endDate) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(F10);
            Date date1 = formatter.parse(startDate);
            Date date2 = formatter.parse(endDate);
            long l = date2.getTime() - date1.getTime() + 1000;
            long d = l / (24 * 60 * 60 * 1000);
            return (int) d;
        } catch (ParseException e) {
        }
        return 0;
    }

    private static Calendar getCalendar(String day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Integer.parseInt(day.substring(0, 4)), Integer.parseInt(day.substring(5, 7)) - 1, Integer.parseInt(day.substring(8, 10)), 0, 0, 0);
        return cal;
    }

    /**
     * 当前时间几小时相差多少时间
     * @param hour
     * @return
     * @since 2017年8月18日
     */
    public static Date getAddHourTime(int hour) {
        Calendar dalendar = Calendar.getInstance();
        dalendar.add(Calendar.HOUR_OF_DAY, hour);
        return dalendar.getTime();
    }

    /**
     * 将Calendar转换为日期字符串（字符串的格式：2018-04-21）
     * @param cale
     * @return
     */
    public static String getDateString(Calendar cale) {
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) + 1;
        int day = cale.get(Calendar.DAY_OF_MONTH);
        return year + "-" + (month < 10 ? "0" + month : month + "") + "-" + (day < 10 ? "0" + day : day + "");
    }

    /**
     * Calendar转为指定格式的日期字符串
     * @param cale
     * @param format
     * @return
     */
    public static String getDateString(Calendar cale, String format) {
        return dateToString(cale.getTime(), format);
    }

    /**
     *计算两个日期之间相差的时间
     * @param sDate
     * @param eDate
     * @return
     * @throws Exception
     */
    public static long substract(String sDate, String eDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = null;
        try {
            d1 = sdf.parse(sDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date d2 = null;
        try {
            d2 = sdf.parse(eDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (d2.getTime() - d1.getTime() + 1000000) / (3600 * 24 * 1000);
    }

    /**
     * 返回当前日期, 格式：yyyy-mm-dd 使用方法： Date.getToday();
     * @return 2018-04-21
     */
    public static String getToday() {
        Calendar rightNow = Calendar.getInstance();
        int year = rightNow.get(Calendar.YEAR);
        int month = rightNow.get(Calendar.MONTH) + 1;
        int day = rightNow.get(Calendar.DAY_OF_MONTH);
        return year + "-" + (month < 10 ? "0" + month : month + "") + "-" + (day < 10 ? "0" + day : day + "");
    }

    /**
     * 字符串的日期格式的计算
     * @param smdate 较大时结果为负数
     * @param bdate 较大时结果为正数
     * @return int
     * @throws ParseException
     */
    public static int daysBetween(String smdate, String bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 获取yyyy-MM-dd格式日期的所在星期数
     * 例如2018-04-21得到的结果是7
     * @param dateStr
     * @return
     */
    public static int getWeekDay(String dateStr) {
        int week = -1;
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = ft.parse(dateStr);
            if (date != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                week = cal.get(Calendar.DAY_OF_WEEK);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return week;
    }

    /* 将20180421转化为2016-04-21
        * @param day
        * @return
     */
    public static String intToDay(long day) {
        String dayStr = String.valueOf(day);
        return new StringBuilder().append(dayStr.substring(0, 4)).append("-").append(dayStr.substring(4, 6)).append("-").append(dayStr.substring(6, 8)).toString();
    }

    public static long dayToInt(String day) {
        return Long.parseLong(day.replaceAll("-", ""));
    }


    /**
     * 判断某一日期是星期几，星期天为第7天
     * @param day
     * @return
     */
    public static String getDayOfWeekCh(String day) {
        int dayInt;
        Calendar cal = Calendar.getInstance();
        cal.set(Integer.parseInt(day.substring(0, 4)), Integer.parseInt(day.substring(5, 7)) - 1, Integer.parseInt(day.substring(8, 10)), 0, 0, 0);
        dayInt = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayInt == 0) {
            dayInt = 7;
        }
        return dayInt + "";
    }

    /**
     * 日期解析
     * @param source
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String source, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(source);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取指定年月份的第一天
     * @param year
     * @param month
     * @return
     */
    public static String monthFirstDay(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String firstDayOfMonth = sdf.format(cal.getTime());
        return firstDayOfMonth;
    }

    /**
     * 获取指定年月份的最后一天
     * @param year
     * @param month
     * @return
     */
    public static String monthLastDay(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最小天数
        int firstDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());
        return lastDayOfMonth;
    }


    /**
     * Date类型转LocalDate类型
     * @param date
     * @return
     */
    public static LocalDate dateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localPriceDate = instant.atZone(zoneId).toLocalDate();
        return localPriceDate;
    }

    /**
     * LocalDate类型转Date类型
     * @param localDate
     * @return Date
     */
    public static Date localDateToDate(LocalDate localDate) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
        Date date = Date.from(zdt.toInstant());
        return date;
    }
}