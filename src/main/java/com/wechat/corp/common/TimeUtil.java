package com.wechat.corp.common;

import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TimeUtil {

    public static final String YYYY_MM_DD_FORMAT = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_MM_SS_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final DateFormat YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
    public static final DateFormat YYYY_MM_DD_HH_MM_SS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    /**
     * 根据年和周获取时间
     * @return
     */
    public static Map<String, String> getWeekRelatedByTime(String year, String week){
        Map<String, String> result = new HashMap<>();

        //获取周次
        int weekIndex = -1;
        String yearIndex = null;
        if (StringUtils.isBlank(year) && StringUtils.isBlank(week)) {
            weekIndex = TimeUtil.getCurrentWeek();
            yearIndex = TimeUtil.getNoLineYYYYMM(new Date()).substring(0, 4);
        } else {
            weekIndex = Integer.parseInt(week);
            yearIndex = year;
        }

        //获取当前周第一天
        Calendar firstDay = TimeUtil.getFirstDayOfWeek(yearIndex , weekIndex + "");
        String startDate = TimeUtil.getTimeYYYYMMDD(firstDay.getTime());
        String endDate = TimeUtil.YYYY_MM_DD.format(TimeUtil.getEndDayOfWeek(firstDay).getTimeInMillis());

        result.put("weekIndex", weekIndex + "");
        result.put("startDate", startDate);
        result.put("endDate", endDate);
        return result;
    }

    /**
     * 获取当前周相关属性
     * @return
     */
    public static Map<String, String> getWeekRelated(){
        return getWeekRelatedByTime(null,null);
    }

    /**
     * 获取当前周
     * @return
     */
    public static int getCurrentWeek(){
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setTime(new Date());
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取当前周的第一天
     * @param year
     * @param week
     * @return
     */
    public static Calendar getFirstDayOfWeek(String year, String week){
        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.DAY_OF_MONTH, 1);

        int i = 1;
        while(cal.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY){
            cal.set(Calendar.DAY_OF_MONTH, i++);
        }

        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.YEAR, Integer.valueOf(year));
        cal.set(Calendar.WEEK_OF_YEAR, Integer.valueOf(week));
        return cal;
    }

    /**
     * 获取本周最后一天
     * @param cal
     * @return
     */
    public static Calendar getEndDayOfWeek(Calendar cal){
        Calendar cal2 = Calendar.getInstance();
        cal2.setTimeInMillis(cal.getTime().getTime()+518400000l);
        return cal2;
    }

    /**
     * 获取数据库系统时间
     * @return
     * @throws Exception
     */
    public static java.sql.Timestamp getSysdate() throws Exception {
    	Date date = new Date();
		return  new Timestamp(date.getTime());
    }

    /**
     * 在一个时间上加上对应的年
     * @param ti long
     * @param i int
     * @throws Exception
     * @return Date
     */
    public static Date addOrMinusYear(long ti, int i) throws Exception {
        Date rtn = null;
        GregorianCalendar cal = new GregorianCalendar();
        Date date = new Date(ti);
        cal.setTime(date);
        cal.add(GregorianCalendar.YEAR, i);
        rtn = cal.getTime();
        return rtn;
    }

    /**
     * 在一个时间上加上对应的月份数
     * @param ti long
     * @param i int
     * @throws Exception
     * @return Date
     */
    public static Date addOrMinusMonth(long ti, int i) throws Exception {
        Date rtn = null;
        GregorianCalendar cal = new GregorianCalendar();
        Date date = new Date(ti);
        cal.setTime(date);
        cal.add(GregorianCalendar.MONTH, i);
        rtn = cal.getTime();
        return rtn;
    }
    /**
     * 在一个时间上加上或减去周
     * @param ti long
     * @param i int
     * @return Date
     */
    public static Date addOrMinusWeek(long ti, int i) {
        Date rtn = null;
        GregorianCalendar cal = new GregorianCalendar();
        Date date = new Date(ti);
        cal.setTime(date);
        cal.add(GregorianCalendar.WEEK_OF_YEAR, i);
        rtn = cal.getTime();
        return rtn;
    }

    /**
     * 在一个时间上加上或减去天数
     * @param ti long
     * @param i int
     * @return Date
     */
    public static Date addOrMinusDays(long ti, int i) {
        Date rtn = null;
        GregorianCalendar cal = new GregorianCalendar();
        Date date = new Date(ti);
        cal.setTime(date);
        cal.add(GregorianCalendar.DAY_OF_MONTH, i);
        rtn = cal.getTime();
        return rtn;
    }
    /**
     * 在一个时间上加上或减去小时
     * @param ti long
     * @param i int
     * @return Date
     */
    public static Date addOrMinusHours(long ti, int i) {
        Date rtn = null;
        GregorianCalendar cal = new GregorianCalendar();
        Date date = new Date(ti);
        cal.setTime(date);
        cal.add(GregorianCalendar.HOUR, i);
        rtn = cal.getTime();
        return rtn;
    }
    /**
     * 在一个时间上加上或减去分钟
     * @param ti long
     * @param i int
     * @return Date
     */
    public static Date addOrMinusMinutes(long ti, int i) {
        Date rtn = null;
        GregorianCalendar cal = new GregorianCalendar();
        Date date = new Date(ti);
        cal.setTime(date);
        cal.add(GregorianCalendar.MINUTE, i);
        rtn = cal.getTime();
        return rtn;
    }
    /**
     * 在一个时间上加上或减去秒数
     * @param ti long
     * @param i int
     * @return Date
     */
    public static Date addOrMinusSecond(long ti, int i) {
        Date rtn = null;
        GregorianCalendar cal = new GregorianCalendar();
        Date date = new Date(ti);
        cal.setTime(date);
        cal.add(GregorianCalendar.SECOND, i);
        rtn = cal.getTime();
        return rtn;
    }

    /**
     * 根据指定的日期获取下个月的第一天的时间
     * @param date
     * @return
     * @author shaosm
     */
    public static Timestamp getDateOfNextMonthFirstDay(Date date) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.set(Calendar.DAY_OF_MONTH,1);
        rightNow.set(Calendar.HOUR_OF_DAY,0);
        rightNow.set(Calendar.MILLISECOND,0);
        rightNow.set(Calendar.SECOND,0);
        rightNow.set(Calendar.MINUTE,0);
        rightNow.set(Calendar.MONTH,rightNow.get(Calendar.MONTH)+1);
        return new Timestamp(rightNow.getTimeInMillis());
    }

    /**
     * 根据指定的日期获取上个月的第一天的时间
     *
     * @param date
     * @return
     */
    public static Timestamp getDateOfPreMonthFirstDay(Date date) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.set(Calendar.DAY_OF_MONTH,1);
        rightNow.set(Calendar.HOUR_OF_DAY,0);
        rightNow.set(Calendar.MILLISECOND,0);
        rightNow.set(Calendar.SECOND,0);
        rightNow.set(Calendar.MINUTE,0);
        rightNow.set(Calendar.MONTH,rightNow.get(Calendar.MONTH)-1);
        return new Timestamp(rightNow.getTimeInMillis());
    }

    /**
     * 将带有时间类型的日期转换成不带时间的日期
     *
     * @param date
     * @return
     */
    public static Timestamp formatDateTimeToDate(Date date) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.set(Calendar.HOUR_OF_DAY,0);
        rightNow.set(Calendar.MILLISECOND,0);
        rightNow.set(Calendar.SECOND,0);
        rightNow.set(Calendar.MINUTE,0);
        return new Timestamp(rightNow.getTimeInMillis());
    }

    /**
     *
     * @Function getDateOfCurrentMonthBeginDay
     * @Description 根据指定日期获取该月的开始时间点
     *
     * @param date
     * @return
     *
     * @version v1.0.0
     * @author lilong
     * @date 2013-1-26 上午10:30:16
     */
    public static Timestamp getDateOfCurrentMonthBeginDay(Date date) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.set(Calendar.DAY_OF_MONTH,rightNow.getActualMinimum(Calendar.DAY_OF_MONTH));
        rightNow.set(Calendar.HOUR_OF_DAY,00);
        rightNow.set(Calendar.MILLISECOND,00);
        rightNow.set(Calendar.SECOND,00);
        rightNow.set(Calendar.MINUTE,00);
        rightNow.set(Calendar.MONTH,rightNow.get(Calendar.MONTH));
        return new Timestamp(rightNow.getTimeInMillis());
    }
    /**
     * 根据指定日期获取该月的最后一天的最后时间点
     *
     * @param date
     * @return
     */
    public static Timestamp getDateOfCurrentMonthEndDay(Date date) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.set(Calendar.DAY_OF_MONTH,rightNow.getActualMaximum(Calendar.DAY_OF_MONTH));
        rightNow.set(Calendar.HOUR_OF_DAY,23);
        rightNow.set(Calendar.MILLISECOND,59);
        rightNow.set(Calendar.SECOND,59);
        rightNow.set(Calendar.MINUTE,59);
        rightNow.set(Calendar.MONTH,rightNow.get(Calendar.MONTH));
        return new Timestamp(rightNow.getTimeInMillis());
    }

    /**
     * 根据指定日期获取当天的最后的时间点
     *
     * @param date
     * @return
     */
    public static Timestamp getLastDate(Date date) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.set(Calendar.HOUR_OF_DAY, 23);
        rightNow.set(Calendar.MILLISECOND, 59);
        rightNow.set(Calendar.SECOND, 59);
        rightNow.set(Calendar.MINUTE, 59);
        rightNow.set(Calendar.MONTH, rightNow.get(Calendar.MONTH));
        return new Timestamp(rightNow.getTimeInMillis());
    }

    /**
     * 根据指定日期获取前一天的最后的时间点
     *
     * @param date
     * @return
     */
    public static Timestamp getPreLastDate(Date date) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.set(Calendar.DAY_OF_MONTH,rightNow.get(Calendar.DAY_OF_MONTH)-1);
        rightNow.set(Calendar.HOUR_OF_DAY, 23);
        rightNow.set(Calendar.MILLISECOND, 59);
        rightNow.set(Calendar.SECOND, 59);
        rightNow.set(Calendar.MINUTE, 59);
        rightNow.set(Calendar.MONTH, rightNow.get(Calendar.MONTH));
        return new Timestamp(rightNow.getTimeInMillis());
    }


    /**
     * 根据指定日期获取前一天的最后的时间点
     *
     * @param date
     * @return
     */
    public static Timestamp getPreLastTime(Date date) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.set(Calendar.DAY_OF_MONTH,rightNow.get(Calendar.DAY_OF_MONTH)-1);
        rightNow.set(Calendar.HOUR_OF_DAY, 23);
        rightNow.set(Calendar.MILLISECOND, 59);
        rightNow.set(Calendar.SECOND, 59);
        rightNow.set(Calendar.MINUTE, 59);
        rightNow.set(Calendar.MONTH, rightNow.get(Calendar.MONTH));
        return new Timestamp(rightNow.getTimeInMillis());
    }


    /**
     * 根据指定日期获取前一天的开始的时间点
     *
     * @param date
     * @return
     */
    public static Timestamp getPreStartTime(Date date) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.set(Calendar.DAY_OF_MONTH,rightNow.get(Calendar.DAY_OF_MONTH)-2);
        rightNow.set(Calendar.HOUR_OF_DAY, 23);
        rightNow.set(Calendar.MILLISECOND, 59);
        rightNow.set(Calendar.SECOND, 59);
        rightNow.set(Calendar.MINUTE, 59);
        rightNow.set(Calendar.MONTH, rightNow.get(Calendar.MONTH));
        return new Timestamp(rightNow.getTimeInMillis());
    }

    /**
     * 根据指定日期获取下一天的开始的时间点
     *
     * @param date
     * @return
     */
    public static Timestamp getPreDateZeroForDate(Date date) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.set(Calendar.DAY_OF_MONTH,rightNow.get(Calendar.DAY_OF_MONTH)-1);
        rightNow.set(Calendar.HOUR_OF_DAY, 0);
        rightNow.set(Calendar.MILLISECOND, 0);
        rightNow.set(Calendar.SECOND, 0);
        rightNow.set(Calendar.MINUTE, 0);
        rightNow.set(Calendar.MONTH, rightNow.get(Calendar.MONTH));
        return new Timestamp(rightNow.getTimeInMillis());
    }
    /**
     * 根据指定日期获取下一天的开始的时间点
     *
     * @param date
     * @return
     */
    public static Timestamp getNextDay(Date date) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.set(Calendar.DAY_OF_MONTH,rightNow.get(Calendar.DAY_OF_MONTH)+1);
        rightNow.set(Calendar.HOUR_OF_DAY, 0);
        rightNow.set(Calendar.MILLISECOND, 0);
        rightNow.set(Calendar.SECOND, 0);
        rightNow.set(Calendar.MINUTE, 0);
        rightNow.set(Calendar.MONTH, rightNow.get(Calendar.MONTH));
        return new Timestamp(rightNow.getTimeInMillis());
    }

    /**
     * 将时间格式化为YYYY-MM-DD
     * @param date
     * @return
     */
    public static String getYYYYMMDD(Date date){
        if(date==null)
            return null;
        DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        return dateformat.format(date);
    }


    /**
     * 将时间格式化为YYYY-MM-DD
     * @param date
     * @return
     */
    public static String getNoLineYYYYMM(Date date){
        if(date==null)
            return null;
        DateFormat dateformat = new SimpleDateFormat("yyyyMM");
        return dateformat.format(date);
    }
    /**
     * 将时间格式化为YYYY-MM-DD
     * @param date
     * @return
     */
    public static String getNoLineYYYYMMDD(Date date){
        if(date==null)
            return null;
        DateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
        return dateformat.format(date);
    }
    /**
     * 将时间格式化为YYYY-MM-DD
     * @param date
     * @return
     * @author wenhl
     */
    public static String getTimeYYYYMMDD(Date date){
        if(date==null)
            return null;
        DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        return dateformat.format(date);
    }
    /**
     * 将时间格式化为YYYY-MM-DD-HH-MM-SS
     * @param date
     * @return
     */
    public static String getNoLineYYYYMMDDHHMISS(Date date){
        if(date==null)
            return null;
        DateFormat dateformat = new SimpleDateFormat("yyyyMMddHH24MMSS");
        return dateformat.format(date);
    }
    /**
     * 将时间格式化为yyyyMMdd HH:mm:ss
     * @param date
     * @return
     */
    public static String getNoLineYYYYMMDDHHMMSS(Date date){
        if(date==null)
            return null;
        DateFormat dateformat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        return dateformat.format(date);
    }
    /**
     *
    * 将时间格式化为yyyy-MM-dd HH:mm:ss
    *
     */
    public static String dateToStr(Timestamp dateDate) {
    	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	  String dateString = formatter.format(dateDate);
    	  return dateString;
    	 }

    /**
     * 处理计费月时间
     * @param beginDate
     * @param endDate
     * @return
     */
    public static Timestamp getBillMonthDate(Date beginDate, Date endDate){
        if(null==beginDate){
            return null;
        }
        //获取结束时间的月底时间
        Timestamp monthEndDate = new Timestamp(TimeUtil.addOrMinusDays(TimeUtil.getDateOfNextMonthFirstDay(endDate).getTime(), -1).getTime());
        return new Timestamp(monthEndDate.getTime());
    }

    /**
     * 将指定的日期取整
     * @param date
     * @return
     * @author shaosm
     */
    public static Timestamp getTruncDate(Date date) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.set(Calendar.HOUR_OF_DAY,0);
        rightNow.set(Calendar.MILLISECOND,0);
        rightNow.set(Calendar.SECOND,0);
        rightNow.set(Calendar.MINUTE,0);
        return new Timestamp(rightNow.getTimeInMillis());
    }
    /**
     * 根据指定的日期获取月的第一天的时间
     *
     * @param date
     * @return
     */
    public static Timestamp getDateOfMonthFirstDay(Date date) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.set(Calendar.DAY_OF_MONTH,1);
        rightNow.set(Calendar.HOUR_OF_DAY,0);
        rightNow.set(Calendar.MILLISECOND,0);
        rightNow.set(Calendar.SECOND,0);
        rightNow.set(Calendar.MINUTE,0);
        rightNow.set(Calendar.MONTH,rightNow.get(Calendar.MONTH));
        return new Timestamp(rightNow.getTimeInMillis());
    }
    /**
     * 获取当前时间的最后时间点
     */
    /**
     * 根据指定日期获取最后时间点
     *
     * @param date
     * @return
     */
    public static Timestamp getDateOfCurrentEndDay(Date date) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.set(Calendar.HOUR_OF_DAY,23);
        rightNow.set(Calendar.MILLISECOND,59);
        rightNow.set(Calendar.SECOND,59);
        rightNow.set(Calendar.MINUTE,59);
        rightNow.set(Calendar.MONTH,rightNow.get(Calendar.MONTH));
        return new Timestamp(rightNow.getTimeInMillis());
    }


    public static Timestamp get2099LastTime() throws Exception {
        return getLastTimeOfYear(2099);
    }

    public static Timestamp getLastTimeOfYear(int year)throws Exception {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return new Timestamp(format.parse(year + "-12-31 23:59:59").getTime());
    }

    /* 得到某一日期的下个月的开始日期
      param dateString 格式yyyyMMdd
      return 8位日期值 */
    public static String getBeginningDateOfLastMonth(String dateString) {
        String lastYear = getLastYear(dateString);//该日期下年年份
        String lastMonth = getLastMonth(dateString);//改日期下月月份
        if("01".equals(lastMonth)){
            return lastYear+"-"+lastMonth+"-01 00:00:00";
        }else{
            return dateString.substring(0,5)+lastMonth+"-01 "+"00:00:00";
        }
    }


    //得到某个日期下一年的年份
    public static String getLastYear(String dateString){
        String currentYear = dateString.substring(0, 4);//该日期的所在年份
        return String.valueOf(Integer.parseInt(currentYear)+1);
    }
    //得到某个日期下月的月份
    public static String getLastMonth(String dateString){
        String currentMonth = dateString.substring(5, 7);//改日期所在月份
        if("12".equals(currentMonth)){
            currentMonth = "01";
        }else if(Integer.parseInt(currentMonth)>10){
            currentMonth = String.valueOf(Integer.parseInt(currentMonth)+1);
        }else{
            currentMonth = "0"+ String.valueOf(Integer.parseInt(currentMonth)+ 1);
        }
        return currentMonth;
    }


    /**
     * 获取两个时间之间的相差月份数
     * @throws java.text.ParseException
     */
    public static int getMonthSpace(String date1, String date2) throws ParseException {
        int result = 0;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(sdf.parse(date1));
        c2.setTime(sdf.parse(date2));

        if(c1.get(Calendar.YEAR)==c2.get(Calendar.YEAR)) {
            result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
        } else {
            result = 12*(c2.get(Calendar.YEAR)-c1.get(Calendar.YEAR))+c2.get(Calendar.MONTH)-c1.get(Calendar.MONTH);
        }
        return result == 0 ? 0 : Math.abs(result);
    }
    /**
     * 获取两个时间之间的相差月份数
     * @throws ParseException
     */
    public static int getMonthSpace(Timestamp date1, Timestamp date2) throws ParseException {
        int result = 0;

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);

        if(c1.get(Calendar.YEAR)==c2.get(Calendar.YEAR)) {
            result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
        } else {
            result = 12*(c2.get(Calendar.YEAR)-c1.get(Calendar.YEAR))+c2.get(Calendar.MONTH)-c1.get(Calendar.MONTH);
        }
        return result == 0 ? 0 : Math.abs(result);
    }
    
    /**
     * 月份相加
     * @Function: DateAddMonth
     *
     * @param time
     * @param month
     * @return
     *
     * @version: v1.0.0
     * @author: wangxw3
     * @date: 2012-10-28 上午10:50:09
     */
    public static Timestamp timeAddMonth(Timestamp time, int month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.add(Calendar.MONTH, month);
        return new Timestamp(cal.getTimeInMillis());
    }
    
    /**
     * 
     * @Function changeDateFormat
     * @Description  时间格式转换
     *
     * @param dateString 要转换的初始值
     * @param formate1  初始值格式
     * @param formate2  转换后的格式
     * @return
     * @throws ParseException
     *
     * @version v1.0.0
     * @author lilong
     */
    public static String changeDateFormat(String dateString, String formate1, String formate2) throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat(formate1);
		Date date = null;
		if (dateString == null || dateString.trim().equals(""))
		{
			date = new Date();
		} else
		{
			date = sdf.parse(dateString);
		}
		SimpleDateFormat sdf2 = new SimpleDateFormat(formate2);
		return sdf2.format(date);

	}
    /**
	 * @Function: 			parseTime
	 * @Description:		解析时间字符串 		
	 *
	 * @param timeStr
	 * @param pattern
	 * @return
	 * @throws ParseException
	 * @version: v1.0.0
	 * @author: zhaowei7
	 * @date: 2013-7-6
	 *
	 * Modification History:
	 * Date         		    Author          	 Version            
	 *-------------------------------------------------------*
	 * 2013-7-6       		zhaowei7             v1.0.0
	 */
	public static Timestamp parseTime(String timeStr, String pattern) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return new Timestamp(sdf.parse(timeStr).getTime());
	}
	
	public static String formatTimeLongToStr(long time, String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Timestamp transTime = new Timestamp(time);
		return sdf.format(transTime);
	}

    /**
     * 获取指定前后几天的月日
     * @param num
     * @return
     */
    public static String getMonthAndDay(int num) {
        DateFormat dateformat = new SimpleDateFormat("MM月dd日");
        Date curDate = new Date();
        return dateformat.format(TimeUtil.addOrMinusDays(curDate.getTime(), num));
    }
}
