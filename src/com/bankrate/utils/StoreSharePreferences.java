package com.bankrate.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

/**
 * @Description: lop Store SharePreferences luu tru cac gia tri can thiet vao
 *               SharePreferences
 * @author:truonglt2
 * @since:Feb 7, 2014 5:21:15 PM
 * @version: 1.0
 * @since: 1.0
 * 
 */
public class StoreSharePreferences {

	/**
	 * 
	 * @decription:
	 * @author: Truong.Le
	 * @datetime: Nov 14, 2013 11:21:53 AM
	 * @param
	 */
	private final static String NAME_PREF = "BANK_RATE";
	Context mContext;
	SharedPreferences sharedPreferences;

	public StoreSharePreferences(Context context) {
		this.mContext = context;
	}

	/**
	 * load gia tri Boolean value
	 * 
	 * @author: truonglt2
	 * @param keyValue
	 * @return
	 * @return: boolean
	 * @throws:
	 */
	public boolean loadBooleandSavedPreferences(String keyValue) {
		sharedPreferences = mContext.getSharedPreferences(NAME_PREF,
				Context.MODE_PRIVATE);
		boolean value = sharedPreferences.getBoolean(keyValue, false);
		return value;
	}

	/**
	 * save gia tri boolean value
	 * 
	 * @author: truonglt2
	 * @param key
	 * @param value
	 * @return: void
	 * @throws:
	 */
	public void saveBooleanPreferences(String key, boolean value) {
		sharedPreferences = mContext.getSharedPreferences(NAME_PREF,
				Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	/**
	 * load gia tri String value
	 * 
	 * @author: truonglt2
	 * @param keyValue
	 * @return
	 * @return: String
	 * @throws:
	 */
	public String loadStringSavedPreferences(String keyValue) {
		sharedPreferences = mContext.getSharedPreferences(NAME_PREF,
				Context.MODE_PRIVATE);
		String value = sharedPreferences.getString(keyValue, "");
		return value;
	}

	/**
	 * load gia tri int value
	 * 
	 * @author: truonglt2
	 * @param keyValue
	 * @return
	 * @return: int
	 * @throws:
	 */
	public int loadIntegerSavedPreferences(String keyValue) {
		sharedPreferences = mContext.getSharedPreferences(NAME_PREF,
				Context.MODE_PRIVATE);
		int value = sharedPreferences.getInt(keyValue, 0);
		return value;
	}

	/**
	 * load gia tri float value
	 * 
	 * @author: truonglt2
	 * @param keyValue
	 * @return
	 * @return: float
	 * @throws:
	 */
	public float loadFloatSavedPreferences(String keyValue) {
		sharedPreferences = mContext.getSharedPreferences(NAME_PREF,
				Context.MODE_PRIVATE);
		float value = sharedPreferences.getFloat(keyValue, 0);
		return value;
	}

	public void saveFloatPreferences(String key, float value) {
		sharedPreferences = mContext.getSharedPreferences(NAME_PREF,
				Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.putFloat(key, value);
		editor.commit();
	}

	/**
	 * save gia tri String value
	 * 
	 * @author: truonglt2
	 * @param key
	 * @param value
	 * @return: void
	 * @throws:
	 */
	public void saveStringPreferences(String key, String value) {
		sharedPreferences = mContext.getSharedPreferences(NAME_PREF,
				Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}

}
