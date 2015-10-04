package com.bankrate.activity;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.bankrate.R;
import com.bankrate.customlib.slidingmenu.BaseActivity;
import com.bankrate.customlib.slidingmenu.SlidingMenu;
import com.bankrate.fragment.AboutAppFragment;
import com.bankrate.wbservices.GeneralServices;
import com.google.gson.JsonArray;

/**
 * @Description: activity man hinh chinh
 * @author:truonglt2
 * @since:Feb 7, 2014 4:23:45 PM
 * @version: 1.0
 * @since: 1.0
 * 
 */
public class MainActivity extends BaseActivity implements OnClickListener {
	public static final String TAG = MainActivity.class.getSimpleName();
	public FragmentManager fragmentManager;
	private ImageView mImgOpenLeftNavigation;
	// Fragment
	public AboutAppFragment aboutFragment;
	Toast toastExit;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		setValue();
		setEvent();
		// SharedPreferences pre = getSharedPreferences(name, mode)
		// Load dash board first
		aboutFragment = AboutAppFragment.newInstance();
		setFragmentContent(aboutFragment, AboutAppFragment.TAG);
		new DownloadFileFromURL().execute();
	}

	/**
	 * Background Async Task to download file
	 * */
	class DownloadFileFromURL extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Bar Dialog
		 * */
		ProgressDialog pDialog;

		@SuppressWarnings("deprecation")
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(MainActivity.this);
			pDialog.setMessage("Downloading file. Please wait...");
			pDialog.setIndeterminate(false);

			pDialog.setMax(100);
			pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			pDialog.setCancelable(true);
			pDialog.setCanceledOnTouchOutside(true);
			pDialog.setIcon(R.drawable.ic_launcher);

			pDialog.show();
		}

		/**
		 * Downloading file in background thread
		 * */
		@Override
		protected String doInBackground(String... f_url) {
			GeneralServices service = new GeneralServices();
			String str = service.getResultBankRate();
			Log.d(TAG, str);
			JSONObject jobj =  service.getResultFetchInforUser("test hang", "test hang 1",
					"test hang 2");
			Log.d(TAG, jobj.toString());
			return null;
		}

		/**
		 * Updating progress bar
		 * */
		protected void onProgressUpdate(String... progress) {
			// setting progress percentage
			pDialog.setProgress(Integer.parseInt(progress[0]));
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		@SuppressWarnings("deprecation")
		@Override
		protected void onPostExecute(String file_url) {
			// dismiss the dialog after the file was downloaded
			pDialog.dismiss();
			// Displaying downloaded image into image view
		}

	}

	/**
	 * This method is used to set fragment content
	 * 
	 * @param fragment
	 * @param tag
	 */
	public void setFragmentContent(Fragment fragment, String tag) {
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		fragmentTransaction.replace(R.id.frame_main, fragment, tag);
		fragmentTransaction.commit();
		slidingMenu.showContent();
	}

	/**
	 * khoi tao cac thanh phan
	 * 
	 * @author: truonglt2
	 * @return: MainActivity
	 * @throws:
	 */
	@SuppressLint("ShowToast")
	@Override
	public void init() {
		toastExit = Toast.makeText(this, R.string.message_quit_app,
				Toast.LENGTH_SHORT);
		fragmentManager = getSupportFragmentManager();
		// Image view
		mImgOpenLeftNavigation = (ImageView) findViewById(R.id.img_menu);
	}

	/**
	 * set gia tri cho cac thanh phan
	 * 
	 * @author: truonglt2
	 * @return: MainActivity
	 * @throws:
	 */
	@Override
	public void setValue() {

	}

	/**
	 * set su kien cho cac thanh phan
	 * 
	 * @author: truonglt2
	 * @return: MainActivity
	 * @throws:
	 */
	@Override
	public void setEvent() {
		// Image view
		mImgOpenLeftNavigation.setOnClickListener(this);
	}

	/**
	 * xu ly onclick
	 * 
	 * @author: truonglt2
	 * @param v
	 * @return: MainActivity
	 * @throws:
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_menu:
			slidingMenu.setMode(SlidingMenu.LEFT);
			toggle();
			break;
		default:
			break;
		}
	}

	/**
	 * check trang thai hoat dong cua service
	 * 
	 * @author: truonglt2
	 * @param acti
	 * @param serviceClassName
	 * @return
	 * @return: boolean
	 * @throws:
	 */
	public static boolean isServiceRunning(Activity acti,
			String serviceClassName) {
		final ActivityManager activityManager = (ActivityManager) acti
				.getSystemService(Context.ACTIVITY_SERVICE);
		final List<RunningServiceInfo> services = activityManager
				.getRunningServices(Integer.MAX_VALUE);
		for (RunningServiceInfo runningServiceInfo : services) {
			if (runningServiceInfo.service.getClassName().equals(
					serviceClassName)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * xu ly phim bat
	 * 
	 * @author: truonglt2
	 * @return: MainActivity
	 * @throws:
	 */
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if (toastExit.getView().isShown()) {
			finish();
		} else {
			toastExit.show();
		}
		super.onBackPressed();
	}

	/**
	 * nhan broadcast
	 * 
	 * @author: truonglt2
	 * @param action
	 * @param bundle
	 * @return: MainActivity
	 * @throws:
	 */
	@Override
	public void receiveBroadcast(int action, Bundle bundle) {
		// TODO Auto-generated method stub
		super.receiveBroadcast(action, bundle);
	}
}
