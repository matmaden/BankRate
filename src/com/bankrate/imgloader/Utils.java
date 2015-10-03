package com.bankrate.imgloader;

import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/** 
  * @Description: lop util cua imageloader
  * @author:truonglt2
  * @since:Feb 7, 2014 5:07:37 PM
  * @version: 1.0
  * @since: 1.0
  * 
  */
public class Utils {
	/**
	*  Copy Stream
	*  @author: truonglt2
	*  @param is
	*  @param os
	*  @return: void
	*  @throws: 
	*/
	public static void CopyStream(InputStream is, OutputStream os) {
		final int buffer_size = 1024;
		try {
			byte[] bytes = new byte[buffer_size];
			for (;;) {
				int count = is.read(bytes, 0, buffer_size);
				if (count == -1)
					break;
				os.write(bytes, 0, count);
			}
		} catch (Exception ex) {
		}
	}

	/**
	*  check Connection
	*  @author: truonglt2
	*  @param ctx
	*  @return
	*  @return: boolean
	*  @throws: 
	*/
	public static boolean checkConn(Context ctx) {
		ConnectivityManager conMgr = (ConnectivityManager) ctx
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo i = conMgr.getActiveNetworkInfo();
		if (i == null)
			return false;
		if (!i.isConnected())
			return false;
		if (!i.isAvailable())
			return false;
		return true;
	}


}