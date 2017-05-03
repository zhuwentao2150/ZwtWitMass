package zhuwentao.com.zwtwitmass.utils;

import android.content.ContentResolver;
import android.content.Context;
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

}
