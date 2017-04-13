package zhuwentao.com.zwtwitmass.UImodule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.text.Collator;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import zhuwentao.com.zwtwitmass.R;
import zhuwentao.com.zwtwitmass.UImodule.custom.autotextview.AutofitTextView;
import zhuwentao.com.zwtwitmass.Utils.TextPinyinUtil;

/**
 * 各种TextView
 */
public class TextViewActivity extends AppCompatActivity {

    /**
     * 能动态改变字体大小
     */
    private AutofitTextView mAutotv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);
        mAutotv = (AutofitTextView) findViewById(R.id.output_autofit);

        // 汉字转拼音
        String str = "阿里巴巴";
        String strPull = TextPinyinUtil.getInstance().getPinyin(str);
        mAutotv.setText(strPull);

        // 对汉字进行排序
        Comparator<Object> com= Collator.getInstance(java.util.Locale.CHINA);
        String[] newArray={"中山","华山","西门子","阿里巴巴","阳江","南京","武汉","北京","安阳","北方","电控系统","朝阳"};
        List<String> list = Arrays.asList(newArray);
        Collections.sort(list, com);
        for(String i:list){
            Log.e("排序", "排序后：" + i);
        }



    }
}
