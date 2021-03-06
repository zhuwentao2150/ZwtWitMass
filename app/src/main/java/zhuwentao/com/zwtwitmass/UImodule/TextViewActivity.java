package zhuwentao.com.zwtwitmass.uimodule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

import java.text.Collator;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import zhuwentao.com.zwtwitmass.R;
import zhuwentao.com.zwtwitmass.uimodule.custom.autotextview.AutofitTextView;
import zhuwentao.com.zwtwitmass.utils.TextPinyinUtil;

/**
 * 各种TextView
 */
public class TextViewActivity extends AppCompatActivity {

    /**
     * 能动态改变字体大小
     */
    private AutofitTextView mAutotv;
    private EditText mInputEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);
        mAutotv = (AutofitTextView) findViewById(R.id.output_autofit);
        mInputEdt = (EditText) findViewById(R.id.edt_autofit_text);

        // 汉字转拼音
        String str = "重庆";
        String strPull = TextPinyinUtil.getInstance().getPinyin(str);

        // 输出单个字符的所有拼音
        char cStr = '略';
        String[] cStrHY = PinyinHelper.toHanyuPinyinStringArray(cStr);
        for (int i = 0; i < cStrHY.length; i++) {
            Log.i("pinyin", cStrHY[i]);
        }


        // 拼音格式输出
//        PinyinHelper.toTongyongPinyinStringArray();
//        PinyinHelper.toWadeGilesPinyinStringArray();
//        PinyinHelper.toYalePinyinStringArray();
//        PinyinHelper.toGwoyeuRomatzyhStringArray();
//        PinyinHelper.toMPS2PinyinStringArray();
//        PinyinHelper.toHanyuPinyinStringArray();
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        try {
            String[] cStrFormatHY = PinyinHelper.toHanyuPinyinStringArray(cStr, format);
            for (int i = 0; i < cStrFormatHY.length; i++) {
                Log.e("pinyinFormat", cStrFormatHY[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        mAutotv.setText(strPull);

        // 对汉字进行排序
        Comparator<Object> com = Collator.getInstance(java.util.Locale.CHINA);
        String[] newArray = {"中山", "华山", "西门子", "阿里巴巴", "阳江", "南京", "重庆", "北京", "安阳", "北方", "电控系统", "朝阳"};
        List<String> list = Arrays.asList(newArray);
        Collections.sort(list, com);
//        for(String i:list){
//            Log.e("排序", "排序后：" + i);
//        }


        mInputEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mAutotv.setText(s.toString());
            }
        });

    }
}
