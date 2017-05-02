package zhuwentao.com.zwtwitmass.uimodule.custom;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import zhuwentao.com.zwtwitmass.R;

/**
 * 自定义Dialog
 * Created by zhuwentao on 2017-05-02.
 */
public class CustomDialog extends Dialog{

    private LinearLayout parentLl;

    /**
     * 提示
     */
    protected TextView hintTv;

    /**
     * 两个按钮：左边按钮
     */
    protected Button doubleLeftBtn;

    /**
     * 两个按钮：右边按钮
     */
    protected Button doubleRightBtn;

    /**
     * 一根横线
     */
    protected View lineView;

    /**
     * 键盘控制器
     */
    private InputMethodManager imm;

    public CustomDialog(Context context) {
        super(context, R.style.CustomDialog);
        // 取得键盘控制器
        imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setCancelable(false);  // 是否可以撤销
        setContentView(R.layout.dialog_custom_view);
        parentLl = (LinearLayout) findViewById(R.id.ll_common_dialog_layout);
        hintTv = (TextView) findViewById(R.id.tv_common_dialog_hint);
        lineView = findViewById(R.id.line_dialog);
        doubleLeftBtn = (Button) findViewById(R.id.btn_common_dialog_double_left);
        doubleRightBtn = (Button) findViewById(R.id.btn_common_dialog_double_right);
        // 点击输入框之外的地方，键盘隐藏
        parentLl.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                hideKeyboard(v);
            }
        });
    }

    /**
     * 设置右键文字和点击事件 此时singleButton不可见
     *
     * @param rightStrId 文字
     * @param clickListener 点击事件
     */
    public void setRightButton(int rightStrId, View.OnClickListener clickListener) {
        doubleRightBtn.setOnClickListener(clickListener);
        doubleRightBtn.setText(rightStrId);
    }

    /**
     * 设置左键键文字和点击事件
     *
     * @param leftStrId 文字
     * @param clickListener 点击事件
     */
    public void setLeftButton(int leftStrId, View.OnClickListener clickListener) {
        doubleLeftBtn.setOnClickListener(clickListener);
        doubleLeftBtn.setText(leftStrId);
    }

    /**
     * 设置提示内容
     *
     * @param str 内容
     */
    public void setHintText(String str) {
        hintTv.setText(str);
        hintTv.setVisibility(View.VISIBLE);
    }

    /**
     * 两个按钮 设置文字
     *
     * @param leftStrId 左按钮文字
     * @param rightStrId 右按钮文字
     */
    public void setBtnText(int leftStrId, int rightStrId) {
        doubleLeftBtn.setText(leftStrId);
        doubleRightBtn.setText(rightStrId);
    }

    /**
     * 设置线不可见
     */
    public void setNoLine() {
        lineView.setVisibility(View.GONE);
    }

    /**
     * 隐藏键盘
     *
     * @param v 编辑框
     */
    public void hideKeyboard(View v) {
        if (imm.isActive()) {
            // 如果键盘为激活状态，则隐藏键盘
            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
        }
    }

    /**
     * 展示键盘
     *
     * @param et 编辑框
     */
    public void showKeyboard(EditText et) {
        et.requestFocus();
        et.setSelectAllOnFocus(true);
        et.selectAll();
        // 显示键盘
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }
}
