package com.bankrate.utils;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

/** 
  * @Description: tinh cac chieu dai, rong cua man hinh
  * @author:truonglt2
  * @since:Feb 7, 2014 5:21:58 PM
  * @version: 1.0
  * @since: 1.0
  * 
  */
public class PhysicalScreenUtil {
    /**
     * This method is used to get the width screen of current device
     * 
     * @param mContext
     *            is current context
     * @return Return the width of device screen in pixels
     */
    @SuppressWarnings("deprecation")
    public static int getWidth(Context mContext) {
        int width=0;
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        width = display.getWidth();  // deprecated
        return width;
    }

    /**
     * This method is used to get the height screen of current device
     * 
     * @param mContext
     *            is the current context
     * @return Return the height screen of current device in pixels
     */
    @SuppressWarnings("deprecation")
    public static int getHeight(Context mContext){
        int height=0;
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        height = display.getHeight();  // deprecated
        return height;
    }

    /**
     * This method is used to convert value in dp to pixels
     * 
     * @param mContext
     *            is the current context
     * @param dp
     *            is value need to convert
     * @return Return the value in pixels
     */
    public static int convertDPToPixels(Context mContext, int dp) {
        float density = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * density);
    }
}
