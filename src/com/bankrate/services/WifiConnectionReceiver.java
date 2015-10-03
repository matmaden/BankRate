
package com.bankrate.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

/** 
  * @Description: check internet connectioon
  * @author:truonglt2
  * @since:Feb 7, 2014 5:18:32 PM
  * @version: 1.0
  * @since: 1.0
  * 
  */
public class WifiConnectionReceiver extends BroadcastReceiver {
	Context mContext;
	@Override
	public void onReceive(Context context, Intent intent) {
		mContext = context;
		checkNetworkStatus();
	}
	/**
	*  check Status network
	*  @author: truonglt2
	*  @return: void
	*  @throws: 
	*/
	public void  checkNetworkStatus(){
	    final ConnectivityManager connMgr = (ConnectivityManager)
	    		mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

	     final android.net.NetworkInfo wifi =
	     connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
	     
	     if( wifi.isAvailable() )
	     {
	    	 Toast.makeText(mContext, "Wifi" , Toast.LENGTH_SHORT).show();
	     }
	    /* else if( mobile.isAvailable() )
	     {
	    	 Toast.makeText(mContext, "Mobile 3G " , Toast.LENGTH_LONG).show();
	     }*/
	     else
	     {
	    	 Toast.makeText(mContext, "No Network " , Toast.LENGTH_SHORT).show();
	     }

	}
	/*private String getNetworkStateString(NetworkInfo.State state) {
		String stateString = "Unknown";

		switch (state) {
		case CONNECTED:
			stateString = "Connected";
			break;
		case CONNECTING:
			stateString = "Connecting";
			break;
		case DISCONNECTED:
			stateString = "Disconnected";
			break;
		case DISCONNECTING:
			stateString = "Disconnecting";
			break;
		case SUSPENDED:
			stateString = "Suspended";
			break;
		default:
			stateString = "Unknown";
			break;
		}

		return stateString;
	}*/
}
