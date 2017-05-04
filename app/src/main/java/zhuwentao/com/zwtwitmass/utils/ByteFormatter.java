package zhuwentao.com.zwtwitmass.utils;

import java.text.DecimalFormat;

/**
 * 字节转换KB、MB、GB、TB
 * Created by zhuwentao on 2017-05-04.
 */
public class ByteFormatter {
    private static final double KB_1 = 1024;
    private static final double MB_1 = KB_1 * 1024;
    private static final double GB_1 = MB_1 * 1024;
    private static final double TB_1 = GB_1 * 1024;

    /**
     * 把double型转化成Bit字符串
     */
    public static String formatBit(double len) {

        if (len<=0) {
            return "0B";

        } else if (len < KB_1) {

            return ((int) len + "B");

        } else if (len < MB_1) {
            Double a = len / KB_1;
            DecimalFormat df = new DecimalFormat("#0.0");

            return (df.format(a) + "KB");

        } else if (len < GB_1) {

            Double a = len / MB_1;
            DecimalFormat df = new DecimalFormat("#0.0");

            return (df.format(a) + "MB");

        } else if (len < TB_1)  {

            Double a = len / GB_1;
            DecimalFormat df = new DecimalFormat("#0.0");

            return (df.format(a) + "GB");

        }else {
            Double a = len / TB_1;
            DecimalFormat df = new DecimalFormat("#0.0");

            return (df.format(a) + "TB");
        }

    }

}
