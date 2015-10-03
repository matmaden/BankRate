package com.bankrate.utils;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

/**
 * Chua cac ham util ve keyboard
 * 
 * @author: Truonglt2
 * @version: 1.0
 * @since: 1.0
 */
public class GeneralsUtils {
	static boolean keyboardVisible = false;
	public static void turnOffKeyboard(Activity activity) {
		InputMethodManager mInputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
		//if (mInputMethodManager.isAcceptingText())
		if (mInputMethodManager.isActive()|| mInputMethodManager.isAcceptingText())
		{
			//"Software Keyboard was shown"
			try {
				mInputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
		{
			//"Software Keyboard was not shown"
			
		}
	}
	/**
	*
	* FOrce hide Keyboard
	* @author: truonglt2
	* @datetime: Dec 16, 2013 5:06:44 PM
	* @return: void
	* @param activityTODO
	* 
	*/
	public static void forceHideKeyboard(Activity activity) {
		if (activity != null && activity.getCurrentFocus() != null) {
			InputMethodManager inputManager = (InputMethodManager) activity
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			inputManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
					InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	/**
	*hide keyboard when before user show keyboard use Toggle.
	* 
	* @author: truonglt2
	* @datetime: Dec 16, 2013 5:06:31 PM
	* @return: void
	* @param activityTODO
	* 
	*/
	public static void forceHideKeyboardUseToggle(Activity activity) {
		try {
			if (activity != null && activity.getCurrentFocus() != null) {
				if (keyboardVisible) {
					InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
					if (imm != null) {
						imm.toggleSoftInput(0, 0);
					}
					keyboardVisible = false;
				} else {
					InputMethodManager inputManager = (InputMethodManager) activity
							.getSystemService(Context.INPUT_METHOD_SERVICE);
					inputManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
							InputMethodManager.HIDE_NOT_ALWAYS);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	*
	* show keyboard use ToogleSoftInput
	* @author: truonglt2
	* @datetime: Dec 16, 2013 5:06:15 PM
	* @return: void
	* @param activityTODO
	* 
	*/
	public void showKeyboardUseToggle(Activity activity) {
		if (activity != null && activity.getCurrentFocus() != null) {
			InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
			keyboardVisible = true;
		}
	}
}
