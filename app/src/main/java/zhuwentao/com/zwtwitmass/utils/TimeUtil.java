package zhuwentao.com.zwtwitmass.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 时间工具
 * Created by zhuwentao on 2017-05-03.
 */
public class TimeUtil {

    private static final String TAG = TimeUtil.class.getSimpleName();

    /*默认日期格式*/
    public static final String DATE_FORMAT_DEFAULT = "yyyy/MM/dd";

    /**
     * 相隔天数
     *
     * @param startDateStr 开始时间
     * @param endDateStr   结束时间
     * @return long 计算结果（相隔天数）
     */
    public static long getDistanceDay(String startDateStr, String endDateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat( DATE_FORMAT_DEFAULT, Locale.getDefault() );
        try {
            Date startDate = sdf.parse( startDateStr );
            Date endDate = sdf.parse( endDateStr );
            return (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);
        } catch (ParseException e) {
            Log.e(TAG, "getDistanceDay() " + e.getMessage(), e );
        }
        return 0;
    }

    /**
     * 系统是否是24小时制
     * 通过查询系统设置获得结果
     *
     * @param ctx 上下文
     * @return 是否是24小时制
     */
    public static boolean is24(Context ctx) {
        ContentResolver cv = ctx.getContentResolver();
        String strTimeFormat = android.provider.Settings.System.getString( cv, android.provider.Settings.System.TIME_12_24 );
        return strTimeFormat != null && strTimeFormat.equals( "24" );
    }

    /**
     * 取得指定时间离现在是多少时间以前
     * 与当前系统时间计算得到距当前时间有多久
     *
     * @param date 已有的指定时间
     * @return 时间段描述
     */
    public static String getAgoTimeString(Date date) {
        /*
         * //LogUtil.d( TAG, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.getDefault()).format(new Date()) + "要显示的时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.getDefault()) .format(date.getTime()));
         */
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime( date );
        Date agoTime = cal.getTime();
        long mTime = now.getTime() - agoTime.getTime();
        String str = "";
        long sTime = mTime / 1000;
        long minute = 60;
        long hour = 60 * 60;
        long day = 24 * 60 * 60;
        if (sTime < minute) {
            str = "刚刚";
        } else if (sTime >= minute && sTime < hour) {
            long timeValue = sTime / minute;
            if (timeValue <= 0) {
                timeValue = 1;
            }
            str = timeValue + "分钟前";
        } else if (sTime >= hour && sTime < day) {
            long timeValue = sTime / hour;
            if (timeValue <= 0) {
                timeValue = 1;
            }
            str = timeValue + "小时前";
        } else {
            long timeValue = sTime / day;
            if (timeValue <= 1) {
                str = "昨天";
            } else {
                str = new SimpleDateFormat( "MM-dd", Locale.getDefault() ).format( date );
            }
        }
        return str;
    }

    /**
     * 判断与当前系统时间间隔是昨天还是前天或者是其他
     *
     * @param ccTime           时间参数
     * @param simpleDateFormat 日期时间格式
     * @return boolean 时间日期是否是昨天/前天
     */
    private static boolean isYesterdayOrBefore(long ccTime, SimpleDateFormat simpleDateFormat, StringBuffer time) {
        Calendar calendar = Calendar.getInstance();
        calendar.add( Calendar.DAY_OF_MONTH, -1 );
        if (simpleDateFormat.format( calendar.getTime() ).equals( simpleDateFormat.format( ccTime ) )) {
            /*
             * LogUtil.d(TAG, "昨天----------------" + new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault()) .format(calendar.getTime()));
             */
            time.append( "昨天" );
            return true;
        } else {
            calendar.clear( Calendar.DAY_OF_MONTH );
            calendar.add( Calendar.DAY_OF_MONTH, -1 );
            if (simpleDateFormat.format( calendar.getTime() ).equals( simpleDateFormat.format( ccTime ) )) {
                /*
                 * LogUtil.d(TAG, "前天_________-----" + new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault()) .format(calendar.getTime()));
                 */
                time.append( "前天" );
                return true;
            }
        }
        return false;
    }

    /**
     * 判断当前日期是否过期
     *
     * @param dateStr 截至日期
     * @return true:已过期，false:未过期。
     */
    public static boolean isTodayOverDue(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat( DATE_FORMAT_DEFAULT, Locale.getDefault() );
        try {
            String todayDateStr = sdf.format( new Date() );
            Date todayDate = sdf.parse( todayDateStr );
            Date comparedDateDate = sdf.parse( dateStr );
            return todayDate.getTime() > comparedDateDate.getTime();
        } catch (ParseException e) {
            Log.e( TAG, "isTodayOverDue() " + e.getMessage(), e );
        }
        return false;
    }

    /**
     * 删除时间格式后面的毫秒
     *
     * @param time 时间 格式：yyyy-MM-dd HH:mm:ss.SSS
     * @return 删除毫秒的时间 格式：yyyy-MM-dd HH:mm:ss
     */
    public static String delMillisecond(String time) {
        String newTime = time;
        if (!TextUtils.isEmpty( newTime )) {
            int index = newTime.indexOf( "." );
            if (index > 0) {
                newTime = newTime.substring( 0, index );
            }
        }
        return newTime;
    }

    /**
     * 将秒转成时分秒格式
     *
     * @param time 时间（ms）
     * @return 返回 时：分 ：秒
     */
    public static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "00:00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = "00:" + unitFormat( minute ) + ":" + unitFormat( second );
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "00:00:00";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat( hour ) + ":" + unitFormat( minute ) + ":" + unitFormat( second );
            }
        }
        return timeStr;
    }

    /**
     * 将整数转成字符串
     *
     * @param i 时间
     * @return 格式化后的时间
     */
    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString( i );
        else
            retStr = "" + i;
        return retStr;
    }

}
