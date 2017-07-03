package zhuwentao.com.zwtwitmass.uimodule;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import zhuwentao.com.zwtwitmass.R;
import zhuwentao.com.zwtwitmass.utils.ToastUtil;

/**
 * 基类Activity
 *
 */
public class BaseActivity extends AppCompatActivity {

	/**
	 * 内容布局
	 */
	private LinearLayout contentLl;

	/**
	 * 上下文
	 */
	public Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(R.layout.common_main);
		init();
		setContentLayout(layoutResID);
	}

	/**
	 * 初始化
	 */
	private void init() {
		context = this;
		initCommonView();
	}

	/**
	 * 初始化公共View
	 */
	public void initCommonView() {
		contentLl = (LinearLayout) findViewById(R.id.common_ll_content);
	}

	/**
	 * 添加内容布局文件
	 * 
	 * @param layoutResID
	 *            内容布局文件的资源ID
	 */
	private void setContentLayout(int layoutResID) {
		LayoutInflater inflater = (LayoutInflater) getSystemService(Service.LAYOUT_INFLATER_SERVICE);
		// 需填充的内容的视图
		View contentView = inflater.inflate(layoutResID, null);
		setContentLayout(contentView);
	}

	/**
	 * 添加内容布局文件
	 * 
	 * @param contentView
	 *            内容布局
	 */
	private void setContentLayout(View contentView) {

		// 将内容填充进父视图
		contentLl.addView(contentView);
		// 内容视图的参数
		android.view.ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
		// 设置宽高均为MATCH_PARENT
		layoutParams.height = android.view.ViewGroup.LayoutParams.MATCH_PARENT;
		layoutParams.width = android.view.ViewGroup.LayoutParams.MATCH_PARENT;
		// 将内容视图的参数设置进去
		contentView.setLayoutParams(layoutParams);
	}

	/**
	 * Activity页面跳转
	 */
	public void gotoActivity(Class<?> act) {
		Intent intent = new Intent(context, act);
		startActivity(intent);
	}

	/**
	 * 显示Toast
	 * 
	 * @param content
	 */
	public void showToast(String content) {
		ToastUtil.showToast(context, content);
	}

	/**
	 * 显示Toast
	 * 
	 * @param resourceId
	 */
	public void showToast(int resourceId) {
		ToastUtil.showToast(context, resourceId);
	}

}
