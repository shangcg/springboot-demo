package com.shangcg.jvm.gc;



import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * 日期处理工具 joda
 */
public class JodaDateUtil {

    public static final String YYMMDD = "yyyyMMdd";
    public static final String YYMMDD_SLASH = "yyyy/MM/dd";
    public static final String WEEK = "EE";
    public static final String HH = "HH";
    public static final String YYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYMMDD_ = "yyyy-MM-dd";

    public static final String WEB_YYMMDDHHMMSS = "yyyy.MM.dd HH:mm:ss";
    public static final String YY = "yyyy";
    public static final String MM = "MM";
    public static final String WEB_YYMMDD = "yyyy.MM.dd";
    public static final String CN_YYMMDD = "yyyy 年 M 月 d 日";

    private JodaDateUtil() {

    }


    /**
     * java日期转 指定字符串
     * @param date java日期
     * @param pattern  格式
     *
     */
    public static String dateToStr(Date date , String pattern) {
        if(date==null){
            throw new IllegalArgumentException("日期参数不能为空");
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(pattern);
        return new DateTime(date).toString(dateTimeFormatter);
    }


    /**
     * joda日期转 指定字符串
     * @param date java日期
     * @param pattern  格式
     *
     */
    public static String dateToStr(DateTime date , String pattern) {
        if(date==null){
            throw new IllegalArgumentException("日期参数不能为空");
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(pattern);
        return date.toString(dateTimeFormatter);
    }



    /**
     *  指定字符串 转  java日期
     * @param dateStr 字符串 日期
     * @param pattern  格式
     *
     */
    public static Date strToDate(String dateStr , String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(pattern);
        return dateTimeFormatter.parseDateTime(dateStr).toDate();
    }


    /**
     * java 日期转数字 20210101
     * @param date java日期
     */
    public static Integer dateToInteger(Date date) {
        return Integer.parseInt(dateToStr(date,YYMMDD));
    }

    /**
     * joda 日期转数字 20210101
     * @param date java日期
     */
    public static Integer dateToInteger(DateTime date) {
        return Integer.parseInt(dateToStr(date,YYMMDD));
    }



    /**
     * 数字 20210101 转java 日期
     * @param dateInteger 数字 20210101
     */
    public static Date integerToDate(Integer dateInteger) {
        return strToDate(String.valueOf(dateInteger),YYMMDD);
    }


    /**
     *
     * 业务中多有 数字日期 20210101 转为 其他格式字符串的情况
     * 满足他
     * @param dateInteger 20210101
     * @param pattern yyyy.MM.dd
     * @return 2021.01.01
     */
    public static String integerToDateStr(Integer dateInteger, String pattern) {
        return JodaDateUtil.dateToStr(JodaDateUtil.integerToDate(dateInteger) , pattern);
    }


    /**
     *
     * 业务中多有 其他格式字符串 转为  数字日期 20210101 的情况
     * 满足他
     * @param dateStr 2021.01.01
     * @param pattern yyyy.MM.dd
     * @return 20210101
     */
    public static Integer strToDateInteger(String dateStr, String pattern) {
        return JodaDateUtil.dateToInteger(JodaDateUtil.strToDate(dateStr,pattern));
    }


    /**
     * 获取当前周的开始时间
     */
    public static Integer getCurrentWeekBeginDate(){
        return  getTargetDayWeekBeginDate(LocalDate.now().toDate());
    }



    /**
     * 获取指定日期的周的开始时间
     * @param dateStr  20210101
     * @param pattern  DateFormatterConstant
     */
    public static Integer getTargetDayWeekBeginDate(String dateStr, String pattern){
        Date date = strToDate(dateStr, pattern);
        return getTargetDayWeekBeginDate(date);
    }


    /**
     * 获取指定日期的周的开始时间
     * @param date java 日期
     */
    public static Integer getTargetDayWeekBeginDate(Date date){
        DateTime targetDate = new DateTime(date);
        DateTime dateTime = targetDate.minusDays(targetDate.getDayOfWeek() -0);
        return dateToInteger(dateTime);
    }


    /**
     * 获取当前周的开始时间
     */
    public static Integer getCurrentWeekEndDate(){
        return  getTargetDayWeekEndDate(LocalDate.now().toDate());
    }




    /**
     * 获取指定日期的周的开始时间
     * @param dateStr  20210101
     * @param pattern  DateFormatterConstant
     */
    public static Integer getTargetDayWeekEndDate(String dateStr, String pattern){
        Date date = strToDate(dateStr, pattern);
        return getTargetDayWeekEndDate(date);
    }


    /**
     * 获取指定日期的周的开始时间
     * @param date java 日期
     */
    public static Integer getTargetDayWeekEndDate(Date date){
        DateTime targetDate = new DateTime(date);
        DateTime dateTime = targetDate.plusDays(7-targetDate.getDayOfWeek());
        return dateToInteger(dateTime);
    }

    /**
     * 判断某区间是否包含今天
     * @param start 区间开始时间
     * @param end 区间结束时间
     * @return
     */
    public static boolean containsToday(String start, String end){
        DateTimeFormatter formatter = DateTimeFormat.forPattern(YYMMDD);
        DateTime startTime = formatter.parseDateTime(start);
        DateTime endTime = formatter.parseDateTime(end);
        Interval interval = new Interval(startTime, endTime);
        return interval.contains(new DateTime());
    }

    public static void main(String[] args) {
        System.out.println(JodaDateUtil.containsToday("20210610", "20210611"));

    }
}
