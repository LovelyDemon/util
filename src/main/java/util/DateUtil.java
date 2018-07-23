package util;

import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author 蝈蝈
 * @since 0.1.0
 */
public class DateUtil {

    /**
     * 获得某天最小时间 yyyy-MM-dd 00:00:00
     *
     * @param date 日期
     * @return Date
     */
    public static Date getStartOfDay(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        //防止mysql自动加一秒,毫秒设为0
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获得某天最大时间 yyyy-MM-dd 23:59:59
     *
     * @param date 日期
     * @return Date
     */
    public static Date getEndOfDay(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        //防止mysql自动加一秒,毫秒设为0
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

}
