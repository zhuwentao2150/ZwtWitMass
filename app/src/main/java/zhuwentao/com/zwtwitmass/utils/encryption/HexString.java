package zhuwentao.com.zwtwitmass.utils.encryption;

/**
 * 十六进制转换
 * Created by zhuwentao on 2017-05-04.
 */
public class HexString {

    private static final char[] hexCode = "0123456789ABCDEF".toCharArray();

    /**
     * 字节转化成十六进制
     * @param data 传入的字节
     * @return 十六进制字符串
     */
    public static String bytes2Hex(byte[] data) {
        StringBuilder r = new StringBuilder(data.length * 2);
        for (byte b : data) {
            r.append(hexCode[(b >> 4) & 0xF]);
            r.append(hexCode[(b & 0xF)]);
        }
        return r.toString();
    }

    /**
     * 十六进制转化成十进制数值
     * @param ch 传入的char
     * @return
     */
    private static int hexToBin(char ch) {
        if ('0' <= ch && ch <= '9')
            return ch - '0';
        if ('A' <= ch && ch <= 'F')
            return ch - 'A' + 10;
        if ('a' <= ch && ch <= 'f')
            return ch - 'a' + 10;
        return -1;
    }

    /**
     * 十六进制转化成字节
     * @param s 十六进制字符串
     * @return 字节数组
     */
    public static byte[] hex2Bytes(String s) {
        final int len = s.length();

        // "111" is not a valid hex encoding.
        if (len % 2 != 0)
            throw new IllegalArgumentException("hexBinary needs to be even-length: " + s);

        byte[] out = new byte[len / 2];

        for (int i = 0; i < len; i += 2) {
            int h = hexToBin(s.charAt(i));
            int l = hexToBin(s.charAt(i + 1));
            if (h == -1 || l == -1)
                throw new IllegalArgumentException("contains illegal character for hexBinary: " + s);

            out[i / 2] = (byte) (h * 16 + l);
        }

        return out;
    }
}
