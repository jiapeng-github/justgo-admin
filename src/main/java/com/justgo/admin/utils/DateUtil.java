package com.justgo.admin.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;


public class DateUtil {

    final static String DEFAULT_FORMAT = "yyyyMMdd";
    final static String fmtStr = "yyyy-MM-dd HH:mm:ss";

    final static SimpleDateFormat sdfStr = new SimpleDateFormat("yyyy-MM-dd");
    final static SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String praseDate(Date date, String pattern) {
        if (StringUtils.isEmpty(pattern)) {
            pattern = DEFAULT_FORMAT;
        }
        if (date == null) return "";
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    public static Date toDate(String dateString, String pattern) throws Exception {
        if (StringUtils.isEmpty(pattern)) {
            pattern = DEFAULT_FORMAT;
        }
        DateFormat df = new SimpleDateFormat(pattern);
        Date date;
        date = df.parse(dateString);
        return date;
    }

    public static Date makeUpTime() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, -5);
        return cal.getTime();
    }

    /**
     * @param startDate 开始日期时间
     * @param endDate   结束日期时间
     * @return 获取日期天数
     */
    public static long getDiffDays(Date startDate, Date endDate) {
        try {
            long diff = endDate.getTime() - startDate.getTime();
            long days = diff / (1000 * 60 * 60 * 24);
            return days;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * @param startDate 开始日期时间
     * @param endDate   结束日期时间
     * @return 获取时间差（秒）
     */
    public static long getDiffMins(Date startDate, Date endDate) {
        try {
            long diff = endDate.getTime() - startDate.getTime();
            long days = diff / 1000;
            return days;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static Date getQueryDateBefore(Date date) throws ParseException {
        if (null == date) return null;
        String str = sdfStr.format(date);
        String strBefore = str + " 00:00:00";
        return sdfDate.parse(strBefore);
    }

    public static Date getQueryDateAfter(Date date) throws ParseException {
        if (null == date) return null;
        String str = sdfStr.format(date);
        String strAfter = str + " 23:59:59";
        return sdfDate.parse(strAfter);
    }

    public static String getNow() {
        SimpleDateFormat sdf = new SimpleDateFormat(fmtStr);
        return sdf.format(new Date());
    }

    public static String subStrFormatTime(String value) {
        if (value == null || value == "") {
            return "";
        }
        if (value.length() == 14) {
            //20160618163611
            return value.substring(0, 4) + '-' + value.substring(4, 6) + '-' + value.substring(6, 8) + ' ' + value.substring(8, 10) + ':'
                    + value.substring(10, 12) + ':' + value.substring(12);
        } else if (value.length() == 8) {
            return value.substring(0, 4) + '-' + value.substring(4, 6) + '-' + value.substring(4, 8);
        }
        return value;
    }

    public static Date stringToDate(String date) {

        try {
            return sdfDate.parse(date);
        } catch (ParseException e) {

            e.printStackTrace();
        }
        return null;

    }

    /**
     * 获取一个月的最后一天。 <br>
     * 例如20030111,即一月份的最后一天是20030131
     *
     * @param date - 指定日期(yyyyMMdd)
     * @return String - 返回计算后的日期
     */
    public static String getLastDayOfMonth(String date) {
        if (date.length() != 8)
            return "";
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(4, 6));
        int day = 0;
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
                || month == 10 || month == 12) {
            day = 31;
        } else if (month == 2) {
            if (((year % 4) == 0) && ((year % 100) != 0)) {
                day = 29;
            } else {
                day = 28;
            }
        } else {
            day = 30;
        }

        String newDate = "" + year;
        if (month < 10) {
            newDate += "0" + month + day;
        } else {
            newDate += "" + month + day;
        }
        return newDate;
    }

    /**
     * 计算当前系统前一天0点的时间，00:00:00
     * @return
     */
    public static Date getLastDayBeginTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 计算当前系统前一天0点的时间，23:59:59
     * @return
     */
    public static Date getLastDayLastTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * get Calendar of given year
     * @param year
     * @return
     */
    private static Calendar getCalendarFormYear(int year){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.YEAR, year);
        return cal;
    }

    /**
     * 以星期一为起始周期
     * 得到某年某周的第一天
     * get start date of given week no of a year
     * @param year
     * @param weekNo
     * @return
     */
    public static String getStartDayOfWeekNo(int year,int weekNo){
        Calendar cal = getCalendarFormYear(year);
        cal.set(Calendar.WEEK_OF_YEAR, weekNo);
        return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" +
                cal.get(Calendar.DAY_OF_MONTH);

    }

    /**
     * get the end day of given week no of a year.
     * 得到某年某周的最后一天
     * @param year
     * @param weekNo
     * @return
     */
    public static String getEndDayOfWeekNo(int year,int weekNo){
        Calendar cal = getCalendarFormYear(year);
        cal.set(Calendar.WEEK_OF_YEAR, weekNo);
        cal.add(Calendar.DAY_OF_WEEK, 6);
        return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" +
                cal.get(Calendar.DAY_OF_MONTH);
    }

    /**根据日期2017-12-28 获取年周 返回格式： 2017-52*/
    /*public static String getWeekNoByDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cl = Calendar.getInstance();
        cl.setFirstDayOfWeek(Calendar.MONDAY);
        cl.setTime(sdf.parse(date));
        int week = cl.get(Calendar.WEEK_OF_YEAR);
        String weekForCal = String.valueOf(week);
        cl.add(Calendar.DAY_OF_MONTH, -7);
        int year = cl.get(Calendar.YEAR);
        if(week<10){
            weekForCal = "0"+week;
        }
        if(week<cl.get(Calendar.WEEK_OF_YEAR)){
            year+=1;
        }
        return year+"-"+weekForCal;
    }*/
    public static String getWeekNoByDate(String date) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateFormat.parse(date));
        calendar.setMinimalDaysInFirstWeek(4);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        int year = calendar.get(Calendar.YEAR);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        String weekForCal = String.valueOf(week);
        if(week<10){
            weekForCal = "0"+week;
        }
        return year+"-"+weekForCal;
    }

    /**
     * 生成系统跟踪号  17位时间戳 + 3位随机数
     * @return
     */
    public static String getRandomTraceId(){
        String id="";
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String temp = sf.format(new Date());
        int random=(int) ((Math.random()*9+1)*100);
        id=temp+random;
        return id;
    }

    public static void main(String[] args) {
        try {
            //        53
            System.out.println(getWeekNoByDate("2015-12-28"));
            //        48
            System.out.println(getWeekNoByDate("2017-11-27"));

            //        1
            System.out.println(getWeekNoByDate("2018-1-7"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
