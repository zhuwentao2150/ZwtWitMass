package zhuwentao.com.zwtwitmass.ui;

import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;

import zhuwentao.com.zwtwitmass.R;
import zhuwentao.com.zwtwitmass.uimodule.BaseActivity;
import zhuwentao.com.zwtwitmass.utils.FileUtil;
import zhuwentao.com.zwtwitmass.utils.LogUtil;

/**
 * 文件读写
 * Created by zhuwentao on 2017-07-09.
 */
public class FileWRActivity extends BaseActivity{

    private String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/ZWTTest";

    private String fileName = "FileTest2.zwt";

    private int index = 0;

    private Button mFileWBtn;

    private Button mFileRBtn;

    private TextView mShowContentTv;

    private EditText mInputContentEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_filewr);

        initView();

        initUI();

        initListener();
    }

    private void initUI() {

        char cStr = '重';
        String[] strs = PinyinHelper.convertToPinyinArray(cStr);

        for (int i = 0; i < strs.length; i++) {
            Log.e("pinyin1", strs[i]);
        }

        String[] strs1 = PinyinHelper.convertToPinyinArray(cStr, PinyinFormat.WITH_TONE_MARK);
        for (int i = 0; i < strs1.length; i++) {
            Log.e("pinyin2", strs1[i]);
        }

        String[] strs2 = PinyinHelper.convertToPinyinArray(cStr, PinyinFormat.WITH_TONE_NUMBER);
        for (int i = 0; i < strs2.length; i++) {
            Log.e("pinyin3", strs2[i]);
        }

        String[] strs3 = PinyinHelper.convertToPinyinArray(cStr, PinyinFormat.WITHOUT_TONE);
        for (int i = 0; i < strs3.length; i++) {
            Log.e("pinyin4", strs3[i]);
        }

        try {
            String strs4 = PinyinHelper.convertToPinyinString("一二三四", "、");
            Log.e("pinyin5", strs4);
        } catch (PinyinException e) {
            e.printStackTrace();
        }

        try {
            String str5 = PinyinHelper.convertToPinyinString("五六七八", " ", PinyinFormat.WITH_TONE_NUMBER);
            Log.e("pinyin6", str5);
        } catch (PinyinException e) {
            e.printStackTrace();
        }

        boolean isMulti = PinyinHelper.hasMultiPinyin(cStr);
        Log.e("pinyin7", "是否为多音字：" + isMulti);

        try {
            String firstPinyin = PinyinHelper.getShortPinyin("九十");
            Log.e("pinyin8", "九十的首字母：" + firstPinyin);
        } catch (PinyinException e) {
            e.printStackTrace();
        }


    }

    private void initListener() {
        mFileWBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mInputContentEdt.getText().toString();
                try {
                    if (!TextUtils.isEmpty(content)) {
                        // 写入数据
                        FileUtil.saveStrToFile(filePath, fileName, content);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        mFileRBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String stringFile = FileUtil.readFileToStr(filePath, fileName, index);
                    if (TextUtils.isEmpty(stringFile)) {
                        index = 0;
                        return;
                    }
                    mShowContentTv.setText(stringFile);
                    LogUtil.e(stringFile);
                    index++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void initView() {
        mFileWBtn = (Button) findViewById(R.id.btn_filewr_w);
        mFileRBtn = (Button) findViewById(R.id.btn_filewr_r);
        mShowContentTv = (TextView) findViewById(R.id.tv_filewr_show_content);
        mInputContentEdt = (EditText) findViewById(R.id.edt_filewr_content);
    }


}

