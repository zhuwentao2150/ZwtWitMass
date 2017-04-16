package zhuwentao.com.zwtwitmass.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * 与键盘相关的工具类
 */
public class KeyboardUtil {
	
	/**
	 * 隐藏键盘
	 * 
	 * @param context	上下文
	 * @param layout	view对象
	 */
	public static void hideVirtualKeyboard(Context context, View layout) {
		if (context == null || layout == null) {
			return;
		}
		
		InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (imm.isActive()) {
			imm.hideSoftInputFromWindow(layout.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}
	
	/**
	 * 显示键盘
	 * 
	 * @param view	view对象
	 */
	public static void showVirtualKeyboard(View view) {
		if (view == null) {
			return;
		}
		
		InputMethodManager imm = (InputMethodManager)view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);			
		// imm.showInputMethodPicker();
		imm.showSoftInputFromInputMethod(view.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
//		imm.toggleSoftInput(InputMethodManager.RESULT_UNCHANGED_SHOWN, InputMethodManager.HIDE_NOT_ALWAYS);  
		
	}
	
	
	/**
	 * 获取键盘状态
	 * 
	 * @param context	上下文
	 * @return		true:键盘激活,false:非激活或者传参为null
	 */
	public static boolean getVirtualKeyboardState(Context context) {
		if (context == null) {
			return false;
		}
		
		InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);		
		return imm.isActive();
	}
}