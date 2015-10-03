package com.bankrate.asysn;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.Toast;

import com.bankrate.R;

/**
 * lop down load file (interface)
 * 
 * @author: truonglt2
 * @version: 1.0
 * @since: 1.0
 */
public class DownloadFile extends AsyncTask<String, String, String> {
	/**
	 * khoi tao contructor
	 * 
	 * @author: truonglt2
	 * @version: 1.0
	 * @since: 1.0
	 */
	public DownloadFile(Context context) {
		mContext = context;
	}

	CharSequence contentText;
	Context mContext;
	CharSequence contentTitle;
	PendingIntent contentIntent;
	int HELLO_ID = 1;
	long time;
	int icon;
	CharSequence tickerText;
	File file;
	NotificationManager notificationManager;
	Notification notification;
	private static String file_url = "http://api.androidhive.info/progressdialog/hive.jpg";

	/**
	 * ham thuc hien down notification
	 * 
	 * @author: truonglt2
	 * @return: void
	 * @throws:
	 */
	@SuppressWarnings("deprecation")
	public void downloadNotification() {
		String ns = Context.NOTIFICATION_SERVICE;
		notificationManager = (NotificationManager) mContext
				.getSystemService(ns);

		icon = R.drawable.ic_launcher;
		// the text that appears first on the status bar
		tickerText = "Downloading...";
		time = System.currentTimeMillis();

		notification = new Notification(icon, tickerText, time);

		// the bold font
		contentTitle = "Your download is in progress";
		// the text that needs to change
		contentText = "0% complete";
		Intent notificationIntent = new Intent(Intent.ACTION_VIEW);
		notificationIntent.setType("audio/*");
		contentIntent = PendingIntent.getActivity(mContext, 0,
				notificationIntent, 0);

		notification.setLatestEventInfo(mContext, contentTitle, contentText,
				contentIntent);
		notificationManager.notify(HELLO_ID, notification);

	}

	@Override
	protected void onPreExecute() {
		// execute the status bar notification
		downloadNotification();

		super.onPreExecute();
	}

	@Override
	protected String doInBackground(String... url) {
		int count;
		try {
			URL url2 = new URL(file_url);
			HttpURLConnection connection = (HttpURLConnection) url2
					.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			connection.connect();

			int lengthOfFile = connection.getContentLength();

			// make the stop drop rave folder
			File sdrFolder = new File(Environment.getExternalStorageDirectory()
					+ "/StopDropRave");
			boolean success = false;

			if (!sdrFolder.exists()) {
				success = sdrFolder.mkdir();
			}
			if (!success) {
				String PATH = Environment.getExternalStorageDirectory()
						+ "/StopDropRave/";
				file = new File(PATH);
				file.mkdirs();

			} else {
				String PATH = Environment.getExternalStorageDirectory()
						+ "/StopDropRave/";
				file = new File(PATH);
				file.mkdirs();
			}

			String[] path = url2.getPath().split("/");
			String mp3 = path[path.length - 1];
			String mp31 = mp3.replace("%20", " ");
			String sdrMp3 = mp31.replace("%28", "(");
			String sdrMp31 = sdrMp3.replace("%29", ")");
			String sdrMp32 = sdrMp31.replace("%27", "'");

			File outputFile = new File(file, sdrMp32);
			FileOutputStream fos = new FileOutputStream(outputFile);

			InputStream input = connection.getInputStream();

			byte[] data = new byte[1024];
			long total = 0;
			while ((count = input.read(data)) != -1) {
				total += count;
				publishProgress("" + (int) (total * 100 / lengthOfFile));
				fos.write(data, 0, count);
			}
			fos.close();
			input.close();

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onProgressUpdate(String... progress) {
		contentText = Integer.parseInt(progress[0]) + "% complete";
		notification.setLatestEventInfo(mContext, contentTitle, contentText,
				contentIntent);
		notificationManager.notify(HELLO_ID, notification);
		super.onProgressUpdate(progress);
	}

	@Override
	protected void onPostExecute(String result) {
		// mProgressDialog.dismiss();
		if (result != null)
			Toast.makeText(mContext, "Download error: " + result,
					Toast.LENGTH_LONG).show();
		else
			Toast.makeText(mContext, "File downloaded", Toast.LENGTH_SHORT)
					.show();
	}
}
