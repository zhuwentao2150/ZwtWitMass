package zhuwentao.com.zwtwitmass.utils.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import zhuwentao.com.zwtwitmass.utils.LogUtil;

/**
 * MD5加密
 * Created by zhuwentao on 2017-05-04.
 */
public class MD5 {

    private static final String TAG = "MD5";

    /**
     * 获得MD5加密字符串
     * @param strSrc 需要加密的字符串
     * @return 加密后的字符串
     */
    public static String getMD5Str(String strSrc) {
        MessageDigest md = null;
        String md5Str = null;

        byte[] bt = strSrc.getBytes();
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(bt);
            md5Str = HexString.bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            LogUtil.et(TAG, e, e.getMessage());
        }
        return md5Str;
    }
}
