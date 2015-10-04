package com.bankrate.common;

import android.os.Build;

import com.bankrate.dto.User;
import com.bankrate.imgloader.ImageLoader;

/** 
  * @Description: define cac gia tri cuc bo
  * @author:truonglt2
  * @since:Feb 7, 2014 3:52:08 PM
  * @version: 1.0
  * @since: 1.0
  * 
  */
public class Common {
	public static final int SCREEN_INFOR_RATE=1;
	public static final int SCREEN_INFOR_LOAN=2;
	
	public static String AUTHORIZE_STRING="";
	// OWNER USER INFOMATION
	public static User USER_INFOMATION;
	// This is used to load image normal in application
	public static boolean checkLogined = false;
	public static ImageLoader imageLoad;
    public static User userInfo;
    
    public static int sizeScreenWidth=0;
    public static int sizeScreenHeight=0;
    public static String PACKAGE_NAME="com.viettel";
    public static boolean hasHoneycomb() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }
}
