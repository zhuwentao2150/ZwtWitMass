package zhuwentao.com.zwtwitmass.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 返回一些测试用的数据
 * Created by zhuwentao on 2016-08-25.
 */
public class DataUtil {

    /**
     * 返回List集合测试数据
     * @param number
     * @return
     */
    public static List<String> getListStringData(int number) {
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            String str = "list测试数据" + i;
            datas.add(str);
        }
        return datas;
    }

    /**
     * 返回String数组测试数据
     * @param number
     * @return
     */
    public static String[] getArrayStringData(int number) {
        String[] datas = new String[number];
        for (int i = 0; i < number; i++) {
            datas[i] = "Array测试数据" + i;
        }
        return datas;
    }


}
