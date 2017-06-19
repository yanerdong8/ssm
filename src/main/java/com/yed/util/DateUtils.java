package com.yed.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 时间工具自定义标签函数用
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    private static Log logger = LogFactory.getLog(DateUtils.class);

    public static Date getMMYYDD() throws ParseException {
        Date dateNow = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = dateFormat.format(dateNow);
        Date udate = dateFormat.parse(dateNowStr);
        //Date date = new java.text.SimpleDateFormat("yyyy-MM-dd").parse("1998-5-4");
        return udate;
    }

    public static Date getDateHaveHourMin() throws ParseException {
        Date dateNow = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        String dateNowStr = dateFormat.format(dateNow) + " 00:00:00";
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date udate = dateFormat1.parse(dateNowStr);
        return udate;
    }

    public static Date getDateHaveHourMax() throws ParseException {
        Date dateNow = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = dateFormat.format(dateNow) + " 23:59:59";
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date udate = dateFormat1.parse(dateNowStr);
        return udate;
    }

    /**
     * getTime 获取当前日期时间
     *
     * @return
     */
    public static Date getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    /**
     * legal holiday  such as lundar 0101-0107  solar 0501-0507,1001-1007
     *
     * @param date
     * @return
     * @throws Exception
     */
    public static boolean isHoliday(Date date) throws Exception {
        String str = DateUtils.formatDate(date, "MMdd");
        GregorianCalendar cal = DateUtils.dateConvCalendar(date);
        String lunarStr = LunarCalendar.solarToLundar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
                cal.get(Calendar.DATE));
        logger.debug("\r\n\t " + cal.get(Calendar.YEAR) + (cal.get(Calendar.MONTH) + 1) +
                cal.get(Calendar.DATE));
        logger.debug(lunarStr);
        lunarStr = StringUtils.substring(lunarStr, 4);
        ArrayList<String> chunjie = new ArrayList<String>();
        ArrayList<String> list = new ArrayList<String>();
        list.add("0101");
        list.add("0102");
        list.add("0103");    //元旦1-3
        for (int i = 1; i <= 7; i++) {
            list.add("050" + i);           //五一
            list.add("100" + i);           //十一
            chunjie.add("010" + i);        //农历春节
        }
        if (list.indexOf(str) != -1 || chunjie.indexOf(lunarStr) != -1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * get day of week such as (1,2...7)
     *
     * @param date
     * @return
     */
    public static int dayOfWeek(Date date) {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(date);
        int x = aCalendar.get(Calendar.DAY_OF_WEEK);
        switch (x) {
            case Calendar.MONDAY:
                x = 1;
                break;
            case Calendar.TUESDAY:
                x = 2;
                break;
            case Calendar.WEDNESDAY:
                x = 3;
                break;
            case Calendar.THURSDAY:
                x = 4;
                break;
            case Calendar.FRIDAY:
                x = 5;
                break;
            case Calendar.SATURDAY:
                x = 6;
                break;
            case Calendar.SUNDAY:
                x = 7;
                break;
        }
        return x;
    }

    /**
     * get all date(Date) list between begin date and end date
     *
     * @param beginDate
     * @param endDate
     * @param dateFormat
     * @return
     * @throws Exception
     */
    public static ArrayList getDateList(Object beginDate, Object endDate,
                                        String dateFormat) throws Exception {
        ArrayList list = new ArrayList();
        int count = DateUtils.getDiffDays(beginDate, endDate, dateFormat);
        Date date;
        if (count > 0) {
            date = DateUtils.objectConvDate(beginDate, dateFormat);
        } else {
            date = DateUtils.objectConvDate(endDate, dateFormat);
        }
        for (int i = 0; i < count + 1; i++) {
            list.add(date);
            logger.debug(DateUtils.formatDate(date, dateFormat));
            date = DateUtils.addDay(date, 1);
        }
        return list;
    }


    public static Date addMinute(Date date, int num) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(cal.MINUTE, num);
        return cal.getTime();
    }

    public static Date addSecond(Date date, int num) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(cal.SECOND, num);
        return cal.getTime();
    }

    public static Date addHour(Date date, int num) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(cal.HOUR, num);
        return cal.getTime();
    }

    public static Date addDay(Date date, int num) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(cal.DATE, num);
        return cal.getTime();
    }

    public static Date addMonth(Date date, int num) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(cal.MONTH, num);
        return cal.getTime();
    }

    public static Date addYear(Date date, int num) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(cal.YEAR, num);
        return cal.getTime();
    }

    /**
     * object conver to date
     *
     * @param date   can be String,Date,GregorianCalendar
     * @param format
     * @return
     * @throws Exception
     */
    public static Date objectConvDate(Object date, String format) {
        Date dateA = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (date instanceof String) {
            try {
                dateA = sdf.parse((String) date);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (date instanceof Date) {
            String source = sdf.format(date);
            try {
                dateA = sdf.parse(source);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (date instanceof GregorianCalendar) {
            dateA = ((GregorianCalendar) date).getTime();
        } else {
            throw new java.lang.IllegalArgumentException("无效的参数");
        }
        return dateA;
    }

    /**
     * java.util.Date Convert java.util.GregorianCalendar
     *
     * @param date java.util.Date
     * @return GregorianCalendar
     */
    public static GregorianCalendar dateConvCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        GregorianCalendar cal = new GregorianCalendar(
                calendar.get(Calendar.YEAR) + 1900, calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));
        return cal;
    }

    /**
     * get today before or after day
     *
     * @param count >0 after day,<0 berfor day
     * @return
     */
    public static GregorianCalendar getCurAfterDayCal(int count) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.add(Calendar.DATE, count);
        return cal;
    }

    /**
     * format Calendar
     *
     * @param cal        GregorianCalendar,Date
     * @param dateFormat 日期格式:缺省为E yyyy年MM月dd日常用格式yyyyMMdd HH:mm:ss
     * @return
     * @throws Exception
     */
    public static String formatDate(Object date, String dateFormat) {
        if (date == null) return "";
        GregorianCalendar cal = DateUtils.dateConvCalendar(DateUtils.objectConvDate(date, dateFormat));
        String str = dateFormat == null ? "E yyyy年MM月dd日" : dateFormat;
        SimpleDateFormat format = new SimpleDateFormat(str);
        String curdate = format.format(cal.getTime());
        return curdate;
    }

    /**
     * get day between date1 and date2
     *
     * @param beginDate can be String,Date,GregorianCalendar type
     * @param endDate   can be String,Date,GregorianCalendar type
     * @param format    if String must be set date format
     * @return
     * @throws Exception
     */
    public static int getDiffDays(Object beginDate, Object endDate, String format) throws Exception {
        Object[] objs = new Object[2];
        objs[0] = beginDate;
        objs[1] = endDate;
        Date[] tmpDate = new Date[2];
        for (int i = 0; i < objs.length; i++) {
            tmpDate[i] = DateUtils.objectConvDate(objs[i], format);
            logger.debug(tmpDate[i]);
        }
        long lBeginTime = tmpDate[0].getTime();
        long lEndTime = tmpDate[1].getTime();
        int iDay = (int) ((lEndTime - lBeginTime) / 86400000);
        return iDay;
    }

    public static Date getDateByString(String str) throws ParseException {
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        date = format1.parse(str);
        return date;
    }

    public static Date getMonthByString(String str) throws ParseException {
        DateFormat format1 = new SimpleDateFormat("yyyy-MM");
        Date date = null;
        date = format1.parse(str);
        return date;
    }

    public static Date getDateTimeByString(String str) throws ParseException {
        DateFormat format0 = new SimpleDateFormat("yyyyMMddHHmmss");
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        DateFormat format3 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format0.parse(str);
        } catch (Exception e0) {
            try {
                date = format1.parse(str);
            } catch (Exception e) {
                try {
                    date = format2.parse(str);
                } catch (Exception e1) {
                    try {
                        date = format3.parse(str);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }

        return date;
    }

    /**
     * 过滤Date对象，使其按照规则保留数据
     *
     * @param date
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date filterDate(Date date, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String source = sdf.format(date);
        return sdf.parse(source);
    }

    private static String[] parsePatterns = {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm"};

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    /**
     * 日期型字符串转化为日期 格式
     * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" }
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取过去的天数
     *
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }


    public static Date getDateStart(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date getDateEnd(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 判断字符串是否是日期
     *
     * @param timeString
     * @return
     */
    public static boolean isDate(String timeString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        try {
            format.parse(timeString);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 格式化时间
     *
     * @param timestamp
     * @return
     */
    public static String dateFormat(Date timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(timestamp);
    }

    /**
     * 获取系统时间Timestamp
     *
     * @return
     */
    public static Timestamp getSysTimestamp() {
        return new Timestamp(new Date().getTime());
    }

    /**
     * 获取系统时间Date
     *
     * @return
     */
    public static Date getSysDate() {
        return new Date();
    }

    /**
     * 生成时间随机数
     *
     * @return
     */
    public static String getDateRandom() {
        String s = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        return s;
    }

    /**
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
//		System.out.println(formatDate(parseDate("2010/3/6")));
//		System.out.println(getDate("yyyy年MM月dd日 E"));
//		long time = new Date().getTime()-parseDate("2012-11-19").getTime();
//		System.out.println(time/(24*60*60*1000));

        System.out.println(getCurrentTime());
        System.out.println(getTime());
    }
}