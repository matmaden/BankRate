package com.bankrate.customlib.slidingmenu;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.bankrate.R;
import com.bankrate.common.ActionEventConstant;
import com.bankrate.common.Common;
import com.bankrate.common.Constants;
import com.bankrate.common.ModelEvent;
import com.bankrate.fragment.LeftNavigationFragment;
import com.bankrate.fragment.SearchNavigationFragment;
import com.bankrate.listener.OnEventControlListener;
import com.bankrate.utils.ToastMessageUtil;

/**
 * @Description: lop activity co so
 * @author:truonglt2
 * @since:Feb 7, 2014 3:54:50 PM
 * @version: 1.0
 * @since: 1.0
 * 
 */
public class BaseActivity extends SlidingFragmentActivity implements
		OnEventControlListener {
	private static final String TAG = BaseActivity.class.getSimpleName();
	// chuoi action cua cac broadcast message
	public static final String GGYOULOOK_ACTION = "viettel.com.bookstore.BROADCAST";

	public LeftNavigationFragment mLeftNavigationFragment;
	public SearchNavigationFragment mSearchNavigationFragment;
	public SlidingMenu slidingMenu;
	// kiem tra activity da finish hay chua
	public boolean isFinished = false;
	MyTimerTask timerTask;
	Timer timer = new Timer();
	public static final int TIME_DELAY = 1000;
	// public static final int TIME_PERIOD = 1000 * 60 * 5;
	public static final int TIME_PERIOD = 1000 * 30;
	Dialog mDialog;
	// Progress dialog type (0 - for Horizontal progress bar)
	// File url to download
	private static String file_url = "http://api.androidhive.info/progressdialog/hive.jpg";

	BroadcastReceiver receiver = new BroadcastReceiver() {
		/**
		 * ham nhan receive broadcast
		 * 
		 * @author: truonglt2
		 * @param context
		 * @param intent
		 * @return:
		 * @throws:
		 */
		@Override
		public void onReceive(Context context, Intent intent) {
			int action = intent.getExtras().getInt(Constants.ACTION_BROADCAST);
			int hasCode = intent.getExtras().getInt(
					Constants.HASHCODE_BROADCAST);
			if (hasCode != BaseActivity.this.hashCode()) {
				receiveBroadcast(action, intent.getExtras());
			}
		}
	};

	/**
	 * khoi tao activity
	 * 
	 * @author: truonglt2
	 * @param savedInstanceState
	 * @return: BaseActivity
	 * @throws:
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			IntentFilter filter = new IntentFilter(GGYOULOOK_ACTION);
			registerReceiver(receiver, filter);
		} catch (Exception e) {
		}

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		// this.broadcast = broadcast;

		if (Common.hasHoneycomb() == false) {
			getSupportActionBar().hide();
		}
		slidingMenu = getSlidingMenu();
		slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
		slidingMenu.setShadowDrawable(R.drawable.shadow);
		slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		slidingMenu.setFadeDegree(0.0f);
		slidingMenu.setMode(SlidingMenu.LEFT);
		slidingMenu.setBehindScrollScale(0);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);

		// set the Behind View
		setBehindContentView(R.layout.menu_frame);
//		slidingMenu.setSecondaryMenu(R.layout.menu_frame_two);
//		slidingMenu.setSecondaryShadowDrawable(R.drawable.shadowright);
		// Search navigation
//		mSearchNavigationFragment = new SearchNavigationFragment();
//		getSupportFragmentManager().beginTransaction()
//				.replace(R.id.menu_frame_two, mSearchNavigationFragment)
//				.commit();
		mLeftNavigationFragment = new LeftNavigationFragment().newInstance();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.menu_frame, mLeftNavigationFragment).commit();

//		mDialog = ToastMessageUtil
//				.showDialogUpdateoOrSave(
//						BaseActivity.this,
//						"Thông báo",
//						"Hiện có 15 đầu sách mới. Bạn có muốn update những nội dung sách này về không?",
//						BaseActivity.this);
//		showDialogWithTimeTask();

	}

	/**
	 * Nhan cac broadcast
	 * 
	 * @author: truonglt2
	 * @param action
	 * @param bundle
	 * @return: void
	 * @throws:
	 */
	public void receiveBroadcast(int action, Bundle bundle) {
	}

	/**
	 * huy activity
	 * 
	 * @author: truonglt2
	 * @return: BaseActivity
	 * @throws:
	 */
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		unregisterReceiver(receiver);
		System.gc();
		System.runFinalization();
		super.onDestroy();
	}

	/**
	 * ket thuc activity
	 * 
	 * @author: truonglt2
	 * @return: BaseActivity
	 * @throws:
	 */
	@Override
	public void finish() {
		isFinished = true;
		super.finish();
	}

	/**
	 * finish activity
	 * 
	 * @author: truonglt2
	 * @param requestCode
	 * @return: BaseActivity
	 * @throws:
	 */
	@Override
	public void finishActivity(int requestCode) {
		isFinished = true;
		super.finishActivity(requestCode);
	}

	/**
	 * dung activity
	 * 
	 * @author: truonglt2
	 * @return: BaseActivity
	 * @throws:
	 */
	@Override
	protected void onPause() {
		super.onPause();
		Log.i(TAG, "onPause");
	}

	/**
	 * resume trang thai cua activity
	 * 
	 * @author: truonglt2
	 * @return: BaseActivity
	 * @throws:
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	/**
	 * xu ly cac option selected
	 * 
	 * @author: truonglt2
	 * @param item
	 * @return
	 * @return: BaseActivity
	 * @throws:
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			toggle();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Mo ta chuc nang cua ham
	 * 
	 * @author: truonglt2
	 * @param menu
	 * @return
	 * @return: BaseActivity
	 * @throws:
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// getSupportMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * This method is used to init all variable members
	 * 
	 * @author: truonglt2
	 * @return: void
	 * @throws:
	 */
	public void init() {

	}

	/**
	 * This method is used to set value for members
	 * 
	 * @author: truonglt2
	 * @return: void
	 * @throws:
	 */
	public void setValue() {

	}

	/**
	 * This method is used to set event for members
	 * 
	 * @author: truonglt2
	 * @return: void
	 * @throws:
	 */
	public void setEvent() {

	}

	/*
	 * public void onBackPressed() { Log.d("CDA", "onBackPressed Called");
	 * super.onBackPressed(); }
	 */

	/**
	 * xu ly su kien tu model tra ve view
	 * 
	 * @author: truonglt2
	 * @param modelEvent
	 * @return: void
	 * @throws:
	 */
	public void handleModelViewEvent(ModelEvent modelEvent) {
		System.out.println("co di qua day");
	}

	/**
	 * xu ly loi su kien tu model tra ve view
	 * 
	 * @author: truonglt2
	 * @param modelEvent
	 * @return: void
	 * @throws:
	 */
	public void handleErrorModelViewEvent(ModelEvent modelEvent) {
		switch (modelEvent.getModelCode()) {
		/*
		 * case ErrorConstants.ERROR_COMMON: //
		 * if(modelEvent.getActionEvent().action != //
		 * ActionEventConstant.ACTION_SYN_SYNDATA){
		 * showDialog("Lỗi trong quá trình xử lý"); // } break;
		 */
		}

	}

	/**
	 * @author: truonglt2
	 * @since:Feb 7, 2014 1:25:51 AM
	 * @Description: thread chay dialog
	 */
	public class MyTimerTask extends TimerTask {
		public MyTimerTask() {
		}

		@Override
		public void run() {
			runThreadDialog();
		}
	}

	/**
	 * khoi tao timestask de show dialog
	 * 
	 * @author: truonglt2
	 * @return: void
	 * @throws:
	 */
	void showDialogWithTimeTask() {
		timerTask = new MyTimerTask();
		timer.scheduleAtFixedRate(timerTask, TIME_DELAY, TIME_PERIOD);
	}

	/**
	 * thread hien thi dialog
	 * 
	 * @author: truonglt2
	 * @return: void
	 * @throws:
	 */
	void runThreadDialog() {
		new Thread() {
			public void run() {
				runOnUiThread(new Runnable() {
					public void run() {
						try {
							if (!mDialog.isShowing()) {
								mDialog.show();
							}
						} catch (Exception e) {
							stopTask();
							e.printStackTrace();
						}
					}
				});
			}
		}.start();
	}

	/**
	 * xu ly su kien phat sinh tu cac view
	 * 
	 * @author: truonglt2
	 * @param eventType
	 * @param control
	 * @param data
	 * @return: BaseActivity
	 * @throws:
	 */
	@Override
	public void onEvent(int eventType, View control, Object data) {
		// TODO Auto-generated method stub
		switch (eventType) {
		case ActionEventConstant.ACTION_DOWN_LOAD:// down load book
			if (mDialog.isShowing())
				mDialog.dismiss();
			new DownloadFileFromURL().execute(file_url);
			break;
		case ActionEventConstant.ACTION_CANCEL_DOWNLOAD:// cancel download
			stopTask();
			break;
		case ActionEventConstant.ACTION_LATER_DOWNLOAD:// dismiss dialog and
														// show dialog after 1
														// minute
			if (mDialog.isShowing())
				mDialog.dismiss();
			break;

		default:
			break;
		}
	}

	/**
	 * Mo ta chuc nang stop timeTask
	 * 
	 * @author: truonglt2
	 * @return: void
	 * @throws:
	 */
	public void stopTask() {
		if (timerTask != null) {
			Log.d("TIMER", "timer download canceled");
			timerTask.cancel();
		}
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
			pDialog = new ProgressDialog(BaseActivity.this);
			pDialog.setMessage("Downloading file. Please wait...");
			pDialog.setIndeterminate(false);

			pDialog.setMax(100);
			pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			pDialog.setCancelable(true);
			pDialog.setCanceledOnTouchOutside(true);
			pDialog.setIcon(R.drawable.ic_launcher);

			pDialog.setButton("Ẩn đi", new OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					arg0.dismiss();
				}
			});
			pDialog.setButton("Ẩn đi", new OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					arg0.dismiss();
				}
			});
			pDialog.show();
		}

		/**
		 * Downloading file in background thread
		 * */
		@Override
		protected String doInBackground(String... f_url) {
			int count;
			try {
				URL url = new URL(f_url[0]);
				URLConnection conection = url.openConnection();
				conection.connect();
				// getting file length
				int lenghtOfFile = conection.getContentLength();

				// input stream to read file - with 8k buffer
				InputStream input = new BufferedInputStream(url.openStream(),
						8192);

				// Output stream to write file
				OutputStream output = new FileOutputStream(
						"/sdcard/downloadedfile.jpg");

				byte data[] = new byte[1024];

				long total = 0;

				while ((count = input.read(data)) != -1) {
					total += count;
					// publishing the progress....
					// After this onProgressUpdate will be called
					pDialog.setProgress((int) ((total * 100) / lenghtOfFile));

					// writing data to file
					output.write(data, 0, count);
				}

				// flushing output
				output.flush();

				// closing streams
				output.close();
				input.close();

			} catch (Exception e) {
				Log.e("Error: ", e.getMessage());
			}

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
			// Reading image path from sdcard
			String imagePath = Environment.getExternalStorageDirectory()
					.toString() + "/downloadedfile.jpg";
			// setting downloaded into image view
			showNotification();
		}

	}

	/**
	 * show notification khi download xong
	 * 
	 * @author: truonglt2
	 * @return: void
	 * @throws:
	 */
	private void showNotification() {
		// TODO Auto-generated method stub
		Uri soundUri = RingtoneManager
				.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		Intent intent = new Intent(BaseActivity.this, BaseActivity.class);
		PendingIntent pIntent = PendingIntent.getActivity(BaseActivity.this, 0,
				intent, 0);
		Notification mNotification = new Notification.Builder(BaseActivity.this)
				.setContentTitle("Thông báo!!!")
				.setContentText(
						"Việc cập nhật thông tin sách của bạn đã hoàn thành.")
				.setSmallIcon(R.drawable.ic_launcher).setContentIntent(pIntent)
				.setSound(soundUri)
				/*
				 * .addAction(R.drawable.ic_launcher, "Quy định công ty",
				 * pIntent) .addAction(0, "Skip", pIntent)
				 */
				.build();
		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		// Neu muon an notify sau khi click vao no
		mNotification.flags |= Notification.FLAG_AUTO_CANCEL;
		notificationManager.notify(0, mNotification);
	}
}
