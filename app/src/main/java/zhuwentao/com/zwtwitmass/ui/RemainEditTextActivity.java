package zhuwentao.com.zwtwitmass.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import zhuwentao.com.zwtwitmass.R;


/**
 * 监听用户输入数量
 * Created by zhuwentao on 2017-04-22.
 */
public class RemainEditTextActivity extends AppCompatActivity {

    /** 输入框 */
    private EditText mTextContentEdt;

    /** 剩余字数 */
    private TextView mTextRemainTv;

    /** 已输入字数 */
    private TextView mTextNowSumTv;

    /** 改变前文字 */
    private TextView mTextBeforeTv;

    /** 改变后文字 */
    private TextView mTextAfterTv;

    /** beforeTextChanged方法：添加文字的位置 */
    private TextView mTextBeforeStartTv;

    /** beforeTextChanged方法：被替代个数 */
    private TextView mTextBeforeAfterTv;

    /** beforeTextChanged方法：替代字符数 */
    private TextView mTextBeforeCountTv;

    /** onTextChanged方法：添加文字的位置 */
    private TextView mTextOnStartTv;

    /** onTextChanged方法：被替代个数 */
    private TextView mTextOnBeforeTv;

    /** onTextChanged方法：替代字符数 */
    private TextView mTextOnCountTv;

    /** 总可输入字数 */
    private int textRemainAll = 50;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
        initListener();
    }

    /**
     * 初始化界面
     */
    private void initUI() {
        setContentView(R.layout.act_remain_edittext);
        mTextContentEdt = (EditText) findViewById(R.id.edt_text_content);
        mTextRemainTv = (TextView) findViewById(R.id.tv_text_remain);
        mTextNowSumTv = (TextView) findViewById(R.id.tv_text_now_sum);
        mTextBeforeTv = (TextView) findViewById(R.id.tv_text_before);
        mTextAfterTv = (TextView) findViewById(R.id.tv_text_after);

        mTextBeforeStartTv = (TextView) findViewById(R.id.tv_text_before_start);
        mTextBeforeCountTv = (TextView) findViewById(R.id.tv_text_before_count);
        mTextBeforeAfterTv = (TextView) findViewById(R.id.tv_text_before_after);

        mTextOnStartTv = (TextView) findViewById(R.id.tv_text_on_start);
        mTextOnCountTv = (TextView) findViewById(R.id.tv_text_on_count);
        mTextOnBeforeTv = (TextView) findViewById(R.id.tv_text_on_before);
    }

    /**
     * 初始化监听
     */
    private void initListener() {
        mTextContentEdt.addTextChangedListener(new MyTextWatcher());
    }

    /**
     * 创建自己的TextWatcher监听类
     */
    private class MyTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // 改变之前的文字内容
            mTextBeforeTv.setText(s.toString());
            // 添加的文字位置（第一个字的位置为0）
            mTextBeforeStartTv.setText(String.valueOf(start));
            // 替换字符数
            mTextBeforeAfterTv.setText(String.valueOf(after));
            // 被替换字符数
            mTextBeforeCountTv.setText(String.valueOf(count));
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // 已经输入的字数
            mTextNowSumTv.setText(String.valueOf(s.length()));
            // 剩余可输入字数
            mTextRemainTv.setText(String.valueOf(textRemainAll - s.length()));
            // 添加的文字位置（第一个字的位置为0）
            mTextOnStartTv.setText(String.valueOf(start));
            // 被替换字符数
            mTextOnBeforeTv.setText(String.valueOf(before));
            // 替换字符数
            mTextOnCountTv.setText(String.valueOf(count));
        }

        @Override
        public void afterTextChanged(Editable s) {
            // 改变之后的文字内容
            mTextAfterTv.setText(s.toString());
        }
    }
}
