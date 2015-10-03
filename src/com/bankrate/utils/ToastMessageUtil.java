package com.bankrate.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.bankrate.R;
import com.bankrate.common.ActionEventConstant;
import com.bankrate.listener.OnEventControlListener;

/** 
  * @author:truonglt2
  * @since:Jan 21, 2014 4:46:26 PM
  * @Description: 
  */
public class ToastMessageUtil {
	/**
	 * This method is used to show the message
	 * 
	 * @param context
	 *            is the current context
	 * @param message
	 *            is the message you need to show on you activity
	 * @author thangtc
	 */
	static Toast obj;

	public static final void showToastShort(Context context, String message) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 
	 * 
	 * @author: truong.le
	 * @datetime: Nov 12, 2013 9:55:11 AM
	 * @return: void
	 * @param context
	 * @param messageTODO
	 * 
	 */
	public static final void showToastLong(Context context, String message) {
		Toast.makeText(context, message, Toast.LENGTH_LONG).show();
	}

	/**
	 * show dialog confirm 2 button. Default is label OK and label Cancel
	 * 
	 * if click ok will do something then dismiss dialog
	 * 
	 * @author: truong.le
	 * @param context
	 * @param title
	 * @param message
	 * @param textButton
	 * @return: void
	 * @throws:
	 */
	DoSomeThingListener mlistener;

	public interface DoSomeThingListener {
		public void onDoSomeThingListener();
	}

	/**
	 * 
	 * 
	 * @author: truong.le
	 * @datetime: Nov 8, 2013 4:16:30 PM
	 * @return: void
	 * @param context
	 * @param title
	 * @param message
	 * @param textButtonLeft
	 * @param textButtonRight
	 * @param listenerTODO
	 * 
	 */
	public void showMessageConfirm(Context context, String title,
			String message, String textButtonLeft, String textButtonRight,
			DoSomeThingListener listener) {
		this.mlistener = listener;
		LinearLayout titleView = new LinearLayout(context);
		titleView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				50));
		titleView.setGravity(Gravity.CENTER);
		TextView textTitle = new TextView(context);
		textTitle.setBackgroundColor(Color.TRANSPARENT);
		textTitle.setText(title);
		textTitle.setTypeface(Typeface.DEFAULT_BOLD);
		textTitle.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
		textTitle.setTextColor(context.getResources().getColor(R.color.white));
		titleView.addView(textTitle);

		LinearLayout contentView = new LinearLayout(context);
		contentView.setGravity(Gravity.CENTER);
		TextView textContent = new TextView(context);
		textContent.setBackgroundColor(Color.TRANSPARENT);
		textContent.setText(message);
		textContent.setPadding(5, 0, 5, 0);
		textContent.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
		textContent
				.setTextColor(context.getResources().getColor(R.color.white));
		contentView.addView(textContent);
		if (textButtonLeft.equals("")) {
			textButtonLeft = context.getResources().getString(R.string.OK);
		}
		if (textButtonRight.equals("")) {
			textButtonRight = context.getResources().getString(R.string.cancel);
		}

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setCustomTitle(titleView)
				.setView(contentView)
				.setCancelable(false)
				.setPositiveButton(textButtonLeft,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								mlistener.onDoSomeThingListener();
							}
						})
				.setNegativeButton(textButtonRight,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						});
		builder.show();
	}

	/**
	 * show dialog confirm 1 button. Default is text OK Click ok to dismiss
	 * dialog
	 * 
	 * @author: truong.le
	 * @param context
	 * @param title
	 * @param message
	 * @param textButton
	 * @return: void
	 * @throws:
	 */
	public static void showMessage(Context context, String title,
			String message, String textButton) {
		LinearLayout titleView = new LinearLayout(context);
		titleView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				50));
		titleView.setGravity(Gravity.CENTER);
		TextView textTitle = new TextView(context);
		textTitle.setBackgroundColor(Color.TRANSPARENT);
		if (title.equals("")) {
			title = context.getResources().getString(R.string.message);
		}
		textTitle.setText(title);
		textTitle.setTypeface(Typeface.DEFAULT_BOLD);
		textTitle.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
		textTitle.setTextColor(context.getResources().getColor(R.color.white));
		titleView.addView(textTitle);

		LinearLayout contentView = new LinearLayout(context);
		contentView.setGravity(Gravity.CENTER);
		TextView textContent = new TextView(context);
		textContent.setBackgroundColor(Color.TRANSPARENT);
		textContent.setText(message);
		textContent.setPadding(5, 0, 5, 0);
		textContent.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
		textContent.setGravity(Gravity.CENTER);
		textContent
				.setTextColor(context.getResources().getColor(R.color.white));
		contentView.addView(textContent);
		if (textButton.equals("")) {
			textButton = context.getResources().getString(R.string.OK);
		}
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setCustomTitle(titleView)
				.setView(contentView)
				.setCancelable(false)
				.setPositiveButton(textButton,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.dismiss();
							}
						});
		builder.show();
	}

	/**
	 * 
	 * 
	 * @author: truong.le
	 * @datetime: Nov 12, 2013 9:55:35 AM
	 * @return: Dialog
	 * @param context
	 * @param title
	 * @param message
	 * @returnTODO
	 * 
	 */
	public static Dialog dialogWarning(Context context, String title,
			String message) {
		// Use the Builder class for convenient dialog construction
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title).setMessage(message)
				.setPositiveButton(context.getResources().getString(R.string.OK), new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.dismiss();
					}
				});
		// Create the AlertDialog object and return it
		return builder.create();
	}
	
	static DoGetDataListener dlistener;
	public interface DoGetDataListener {
		public void onGetDataListener(int position, String textData);
	}
	
	/**
	*
	* 
	* @author: truong.le
	* @datetime: Nov 18, 2013 4:49:24 PM
	* @return: Dialog
	* @param context
	* @param title
	* @param arrayChar
	* @param dosomeThing
	* @returnTODO
	* 
	*/
	static int itemchosed = -1;
	public static Dialog onCreateDialogSingleChoice(Context context, String title,
			String[] arrayChar, DoGetDataListener dosomeThing) {
		dlistener = dosomeThing;
		
		// Initialize the Alert Dialog
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		// Source of the data in the DIalog
		final String[] array = arrayChar;

		// Set the dialog title
		builder.setTitle(title)
				// Specify the list array, the items to be selected by default
				// (null for none),
				// and the listener through which to receive callbacks when
				// items are selected
				.setSingleChoiceItems(array, itemchosed,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								itemchosed = which;
							}
						})

				// Set the action buttons
				.setPositiveButton(
						context.getResources().getString(R.string.OK),
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								// User clicked OK, so save the result somewhere
								// or return them to the component that opened
								// the dialog
								System.out.println("getData:"+itemchosed+"/"+ array[itemchosed]);
								dlistener.onGetDataListener(itemchosed,array[itemchosed]);
							}
						})
				.setNegativeButton(
						context.getResources().getString(R.string.cancel),
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								dialog.dismiss();
							}
						});

		return builder.create();
	}
	public static Dialog showDialogUpdateoOrSave(final Context context, String title, String message,final OnEventControlListener listener) {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				context);
//		alertDialog.setTitle("Lưu file ...");
		alertDialog.setTitle(title);
//		alertDialog.setMessage("Bạn có thực sự muốn lưu file này không ?");
		alertDialog.setMessage(message);
		alertDialog.setIcon(R.drawable.ic_launcher);
		alertDialog.setPositiveButton("Có",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
//						Toast.makeText(context,"Bạn đã chọn YES", Toast.LENGTH_SHORT).show();
						listener.onEvent(ActionEventConstant.ACTION_DOWN_LOAD, null, null);
					}
				});
		alertDialog.setNeutralButton("Không",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
//						Toast.makeText(context,"Bạn đã chọn NO", Toast.LENGTH_SHORT).show();
						listener.onEvent(ActionEventConstant.ACTION_CANCEL_DOWNLOAD, null, null);
					}
				});
		alertDialog.setNegativeButton("Để sau",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
//						Toast.makeText(context,"You clicked on Cancel", Toast.LENGTH_SHORT).show();
						listener.onEvent(ActionEventConstant.ACTION_LATER_DOWNLOAD, null, null);
					}
				});
		// Create the AlertDialog object and return it
		return alertDialog.create();
//		alertDialog.show();
	}
	public static void showDialogUpdate(final Context mContext) {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
		alertDialog.setTitle("Thông báo");
		alertDialog
				.setMessage("Có 15 đầu sách mới. Bạn có muốn update nội dung thông tin này");
		alertDialog.setIcon(R.drawable.ic_launcher);
		alertDialog.setPositiveButton("Có",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(mContext,
								"Bạn đã chọn Yes", Toast.LENGTH_SHORT).show();
					}
				});
		alertDialog.setNegativeButton("Không",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(mContext,
								"Bạn đã chọn No", Toast.LENGTH_SHORT).show();
						dialog.cancel();
					}
				});

		alertDialog.show();
	}
}
