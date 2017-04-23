package zhuwentao.com.zwtwitmass.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 校验工具
 * Created by zhuwentao on 2017-04-23.
 */
public class ValidateUtil {

    private ValidateUtil() {
        // 防止被实例化
    }

    /**
     * 字符串是否符合正则表达式的规则
     *
     * @param text 匹配文本
     * @param format 匹配规则
     * @return true 匹配成功 flase 匹配失败
     */
    private static boolean isMatches(String text, String format) {
        Pattern pattern = Pattern.compile(format);
        Matcher m = pattern.matcher(text);
        return m.matches();
    }

    /**
     * 匹配邮箱
     */
    public static boolean isMail(String money) {
        String regex = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        return isMatches(money, regex);
    }

    /**
     * 判断手机格式是否正确
     */
    public static boolean isPhoneNum(String mobile) {
        String format = "^((13[0-9])|(14[0-9])|(15[^4,\\D])|(17[0-9])|(18[0-9]))\\d{8}$";
        return isMatches(mobile, format);
    }

    /**
     * 匹配帐号类型是否正确（只能输入大小写字母和数字，最大不超过20个字符）
     */
    public static boolean isAccount(String str) {
        String format = "[a-zA-Z0-9]{0,20}";
        return isMatches(str, format);
    }

    /**
     * 匹配金额是否符合要求（99999999.99）
     */
    public static boolean isMoney(String money) {
        String regex = "(^[1-9][0-9]{0,7}(\\.[0-9]{0,2})?)|(^0(\\.[0-9]{0,2})?)";
        return isMatches(money, regex);
    }


}
