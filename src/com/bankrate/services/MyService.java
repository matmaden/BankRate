package com.bankrate.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.bankrate.utils.DateTimeUtil;

/**
 * @Description: lop service check location
 * @author:truonglt2
 * @since:Feb 7, 2014 5:15:50 PM
 * @version: 1.0
 * @since: 1.0
 * 
 */
public class MyService extends Service {
	private static final String TAG = "BOOMBOOMTESTGPS";
	private LocationManager mLocationManager = null;
	// private static final int LOCATION_INTERVAL = 3*60*1000;
	// private static final float LOCATION_DISTANCE = 10f;
	public static String BROADCAST_ACTION = "com.viettel.services.MessageReceiver";
	MessageReceiver messageReceiver;

	/**
	 * @Description: lang nghe va xac dinh vi tri cua nguoi dung
	 * @author:truonglt2
	 * @since:Feb 7, 2014 5:18:06 PM
	 * @version: 1.0
	 * @since: 1.0
	 * 
	 */
	private class LocationListener implements android.location.LocationListener {
		Location mLastLocation;

		public LocationListener(String provider) {
			Log.d(TAG, "LocationListener " + provider);
			mLastLocation = new Location(provider);
		}

		@Override
		public void onLocationChanged(Location location) {
			Log.d(TAG, "onLocationChanged: " + location);

			if (DateTimeUtil.isTimeValidate() && location != null) {
				double longtitude = location.getLongitude();
				double latitude = location.getLatitude();

				Intent intent = new Intent(BROADCAST_ACTION);
				Bundle bundle = new Bundle();
				bundle.putBoolean("isActive", false);
				bundle.putDouble("longtitude", longtitude);
				bundle.putDouble("latitude", latitude);
				intent.putExtras(bundle);
				sendBroadcast(intent, "com.viettel.permission.BROADCAST");
				// sendBroadcast(intent);
				mLastLocation.set(location);
			} else {
				Intent intent = new Intent(BROADCAST_ACTION);
				Bundle bundle = new Bundle();
				bundle.putBoolean("isActive", true);
				intent.putExtras(bundle);
				sendBroadcast(intent, "com.viettel.permission.BROADCAST");
			}

		}

		@Override
		public void onProviderDisabled(String provider) {
			Log.d(TAG, "onProviderDisabled: " + provider);
		}

		@Override
		public void onProviderEnabled(String provider) {
			Log.d(TAG, "onProviderEnabled: " + provider);
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			Log.d(TAG, "onStatusChanged: " + provider);
		}
	}

	LocationListener[] mLocationListeners = new LocationListener[] {
			new LocationListener(LocationManager.GPS_PROVIDER),
			new LocationListener(LocationManager.NETWORK_PROVIDER) };

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	/**
	 * thuc hien on Start Command voi co START_STICKY
	 * 
	 * @author: truonglt2
	 * @param intent
	 * @param flags
	 * @param startId
	 * @return
	 * @return: MyService
	 * @throws:
	 */
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(TAG, "onStartCommand");
		super.onStartCommand(intent, flags, startId);
		return START_STICKY;
	}

	/**
	 * ham onCreate service
	 * 
	 * @author: truonglt2
	 * @return: MyService
	 * @throws:
	 */
	@Override
	public void onCreate() {
		Log.d(TAG, "onCreate");
		initializeLocationManager();
		try {
			/*
			 * mLocationManager.requestLocationUpdates(
			 * LocationManager.NETWORK_PROVIDER, LOCATION_INTERVAL,
			 * LOCATION_DISTANCE, mLocationListeners[1]);
			 */
			mLocationManager.requestLocationUpdates(
					LocationManager.NETWORK_PROVIDER, 3000, 0,
					mLocationListeners[0]);
		} catch (java.lang.SecurityException ex) {
			Log.i(TAG, "fail to request location update, ignore", ex);
		} catch (IllegalArgumentException ex) {
			Log.d(TAG, "network provider does not exist, " + ex.getMessage());
		}
		try {
			Log.d(TAG, "over here 1");
			/*
			 * mLocationManager.requestLocationUpdates(
			 * LocationManager.GPS_PROVIDER, LOCATION_INTERVAL,
			 * LOCATION_DISTANCE, mLocationListeners[0]);
			 */
			mLocationManager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER, 3000, 0,
					mLocationListeners[0]);
		} catch (java.lang.SecurityException ex) {
			Log.i(TAG, "fail to request location update, ignore", ex);
		} catch (IllegalArgumentException ex) {
			Log.d(TAG, "gps provider does not exist " + ex.getMessage());
		}
		try {
			Log.d(TAG, "over here 2");
			messageReceiver = new MessageReceiver();
			registerReceiver(messageReceiver,
					new IntentFilter(BROADCAST_ACTION));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ham detroy service
	 * 
	 * @author: truonglt2
	 * @return: MyService
	 * @throws:
	 */
	@Override
	public void onDestroy() {
		Log.d(TAG, "onDestroy hehe");
		super.onDestroy();
		Intent intent = new Intent(BROADCAST_ACTION);
		Bundle bundle = new Bundle();
		bundle.putBoolean("isActive", false);
		bundle.putBoolean("isDetroyed", true);
		intent.putExtras(bundle);
		sendBroadcast(intent, "com.viettel.permission.BROADCAST");
		unregisterReceiver(messageReceiver);
		if (mLocationManager != null) {
			for (int i = 0; i < mLocationListeners.length; i++) {
				try {
					mLocationManager.removeUpdates(mLocationListeners[i]);
				} catch (Exception ex) {
					Log.i(TAG, "fail to remove location listners, ignore", ex);
				}
			}
		}
	}

	/**
	 * initialize object LocationManager
	 * 
	 * @author: truonglt2
	 * @return: void
	 * @throws:
	 */
	private void initializeLocationManager() {
		Log.d(TAG, "initializeLocationManager");
		if (mLocationManager == null) {
			mLocationManager = (LocationManager) getApplicationContext()
					.getSystemService(Context.LOCATION_SERVICE);
		}
	}

	public static String LOG_TAG = MyService.class.getName();

}