
package com.bankrate.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/** 
  * @Description: nhan va show message ve toa do
  * @author:truonglt2
  * @since:Feb 7, 2014 5:14:48 PM
  * @version: 1.0
  * @since: 1.0
  * 
  */
public class MessageReceiver extends BroadcastReceiver {
	Context mContext;
	@Override
	public void onReceive(Context context, Intent intent) {
		mContext = context;
		try {
			double latitude = 0;
			double longtitude = 0;
			Bundle bundle = intent.getExtras();
			if(bundle!=null)
			{
				try {
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				boolean isActive = bundle.getBoolean("isActive"); 
				if(isActive)
				{
					latitude = bundle.getDouble("latitude");
					longtitude = bundle.getDouble("longtitude");
					Toast.makeText(context, "Location saved with latitude:"+latitude+" & longtitude:"+longtitude+" .", Toast.LENGTH_SHORT).show();
				}
				else
				{
					try {
						if(bundle.getBoolean("isDetroyed"))
						{
							Toast.makeText(context, "Service is turn off.", Toast.LENGTH_LONG).show();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					Toast.makeText(context, "Network or service GPS unavailable. Please check service GPS or Network on Setting.", Toast.LENGTH_LONG).show();
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
