package com.bankrate.listener;

import android.view.View;

/**
 * tao inteface lang nghe va xu ly su kien (interface)
 * 
 * @author: truonglt2
 * @version: 1.0
 * @since: 1.0
 */
public interface OnEventControlListener {
	void onEvent(int eventType, View control, Object data);
}
