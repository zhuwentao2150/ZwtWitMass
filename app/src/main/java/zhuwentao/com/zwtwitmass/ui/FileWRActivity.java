package zhuwentao.com.zwtwitmass.ui;

import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

