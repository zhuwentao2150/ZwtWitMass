package zhuwentao.com.zwtwitmass.utils;

import java.text.DecimalFormat;

/**
 * 格式化数据
 * Created by zhuwentao on 2017-04-28.
 */
public class FormatUtil {
    private FormatUtil() {
        // 不被实例化
    }

    /** 不带小数点 满三位数用逗号隔开 */
    public static final String MONEY_FORMAT_COMMA_0 = "###,###";

    /** 带两位小数点 满三位数用逗号隔开 */
    public static final String MONEY_FORMAT_COMMA_2 = "###,##0.00";

    /** 带两位小数点 */
    public static final String MONEY_FORMAT_COMMA_3 = "##0.00";

    /** 带四位小数点 满三位数用逗号隔开 */
    public static final String MONEY_FORMAT_COMMA_4 = "##0.0000";

    /** 带两位小数点，不用逗号隔开 */
    public static final String MONEY_FORMAT_COMMA_5 = "#######0.00";

    /**
     * DOUBLE转成format对应格式的字符串
     *
     * @param number double型数字
     * @param format 转换格式
     * @return 转换格式的字符串
     */
    public static String formatDouble(Double number, String format) {
        if (number == null) {
            number = 0d;
        }
        DecimalFormat df = new DecimalFormat(format);
        return df.format(number);
    }

    /**
     * Integer转成format对应格式的字符串
     *
     * @param number Integer型数字
     * @param format 转换格式
     * @return 转换格式的字符串
     */
    public static String formatInteger(Integer number, String format) {
        if (number == null) {
            number = 0;
        }
        DecimalFormat df = new DecimalFormat(format);
        return df.format(number);
    }

    /**
     * float转成format对应格式的字符串
     *
     * @param number double型数字
     * @param format 转换格式
     * @return 转换格式的字符串
     */
    public static String formatFloat(Float number, String format) {
        if (number == null) {
            number = 0f;
        }
        DecimalFormat df = new DecimalFormat(format);
        return df.format(number);
    }
}
