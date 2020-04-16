package cn.edu.aqtc.im.util;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @Description: 时间工具类
 * @ClassName: DateUtils
 * @Author: zhangjj
 * @Date: 2019-08-27
 */
//@Slf4j
public class DateUtils {

    public  static Date getBeforeDate(Integer days){
        Calendar calendar=new GregorianCalendar();
        calendar.add(Calendar.DATE, Math.negateExact(days));
        return calendar.getTime();
    }

    public static long getTimeState(){
        DateTime dt3 = new DateTime(new Date());
        return dt3.getMillis();
    }

    /**
    * @Description: 今天零点字符串
    * @Param: []
    * @return: java.lang.String
    * @Author: zhangjj
    * @Date: 2020-03-30
    */
    public static String getTodayTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new DateTime().withTimeAtStartOfDay().toLocalDateTime().toDate());
    }
    /** 
    * @Description: 获取当前时间
    * @Param: [] 
    * @return: java.lang.String 
    * @Author: zhangjj
    * @Date: 2020-03-30 
    */ 
    public static String getCurrentTimeStr(){
        return new DateTime().toString("yyyy-MM-dd HH:mm:ss");
    }

    public static DateTime getCurrentTime(){
        return new DateTime();
    }

    public static boolean isStockOpening(){
        DateTime now = new DateTime();
        int currentHourMin = now.getHourOfDay()*100+now.getMinuteOfHour();
        if(currentHourMin>930 && currentHourMin<1500){
            return true;
        }
        return false;
    }

    public static String getTodayDateStr(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new DateTime().withTimeAtStartOfDay().toLocalDateTime().toDate());
    }

    public static String getDateToString(long time) {
        SimpleDateFormat sf =null;
        Date d = new Date(time);
        sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(d);
    }

    public static String getDateToString(Date date) {
        SimpleDateFormat sf = null;
        sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(date);
    }

    public static void main(String[] args) {
        //System.out.println(DateFormatUtils.format(getBeforeDate(142),"yyyy-MM-dd HH:mm:ss"));
        System.out.println(isStockOpening());
    }
}
