package zhuwentao.com.zwtwitmass.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import zhuwentao.com.zwtwitmass.R;
import zhuwentao.com.zwtwitmass.uimodule.BaseActivity;
import zhuwentao.com.zwtwitmass.utils.ValidateUtil;

public class VerifyActivity extends BaseActivity {

    private EditText mEditText;

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_verify);
        initUI();
        initListener();
    }

    private void initUI(){
        mEditText = (EditText) findViewById(R.id.edt_verify_input);
        mButton = (Button) findViewById(R.id.btn_verify_commit);
    }

    private void initListener(){
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = mEditText.getText().toString();
                if (ValidateUtil.isMoney(inputText)){
                    showToast("输入正确！");
                }else{
                    showToast("输入错误！");
                }
            }
        });
    }
}
