package com.bankrate.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.bankrate.R;
import com.bankrate.activity.DetailsTransactionActivity;
import com.bankrate.activity.MainActivity;
import com.bankrate.activity.WellcomeActivity;
import com.bankrate.common.ActionEvent;
import com.bankrate.common.ActionEventConstant;
import com.bankrate.common.ErrorConstants;
import com.bankrate.common.ModelEvent;
import com.bankrate.customlib.slidingmenu.BaseActivity;
import com.bankrate.fragment.BaseFragment;
import com.bankrate.models.UserModel;
import com.bankrate.utils.ToastMessageUtil;

/** 
  * @Description: lop controller dieu kien
  * @author:truonglt2
  * @since:Feb 7, 2014 3:53:57 PM
  * @version: 1.0
  * @since: 1.0
  * 
  */
public class UserController extends AbstractController {

	static UserController instance;

	protected UserController() {
	}

	/**
	*  khoi tao instance of lop
	*  @author: truonglt2
	*  @return
	*  @return: UserController
	*  @throws: 
	*/
	public static UserController getInstance() {
		if (instance == null) {
			instance = new UserController();
		}
		return instance;
	}

	/**
	*  chuyen doi activity
	*  @author: truonglt2
	*  @param e
	*  @return: UserController
	*  @throws: 
	*/
	@Override
	public void handleSwitchActivity(ActionEvent e){
		Activity base = (Activity) e.sender;
		Intent intent;
		Bundle extras;
		switch(e.action){
			case ActionEventConstant.ACTION_CHANGE_VIEW_WELLCOME:
				intent = new Intent(base, WellcomeActivity.class);
				extras = (Bundle) e.viewData;
				intent.putExtras(extras);
				base.startActivity(intent);
				base.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
			break;
			case ActionEventConstant.ACTION_CHANGE_VIEW_MAIN_ACTIVTY:
				intent = new Intent(base, MainActivity.class);
				extras = (Bundle) e.viewData;
				intent.putExtras(extras);
				base.startActivity(intent);
				base.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
			break;
			case ActionEventConstant.ACTION_CHANGE_VIEW_DETAIL_BOOK_ACTIVTY:
				intent = new Intent(base, DetailsTransactionActivity.class);
				extras = (Bundle) e.viewData;
				intent.putExtras(extras);
				base.startActivity(intent);
			break;
		}
	}
	
	
	/**
	*  xu ly cac event tu view
	*  @author: truonglt2
	*  @param e
	*  @return: UserController
	*  @throws: 
	*/
	public void handleViewEvent(ActionEvent e) {
		switch (e.action) {
			//	get data list catagory
			case ActionEventConstant.GET_DATA_LIST_MENU: 
				UserModel.getInstance().requestData(e);
			// get data list book
			case ActionEventConstant.GET_DATA_LIST_BOOK_OF_CATAGORY:
				UserModel.getInstance().requestData(e);
			// get data list menu of book
			case ActionEventConstant.GET_DATA_LIST_MENU_OF_BOOK:
				UserModel.getInstance().requestData(e);
			// get data search list book
			case ActionEventConstant.GET_DATA_SEARCH_LIST_BOOK:
				UserModel.getInstance().requestData(e);
		default:
			break;
		}
	}

	/**
	*  xu lu cac event model
	*  @author: truonglt2
	*  @param modelEvent
	*  @return: UserController
	*  @throws: 
	*/
	public void handleModelEvent(final ModelEvent modelEvent) {
		if (modelEvent.getModelCode() == ErrorConstants.ERROR_CODE_SUCCESS) {
			final ActionEvent e = modelEvent.getActionEvent();
			if (e.sender != null) {
				if (e.sender instanceof BaseActivity) {
					final BaseActivity sender = (BaseActivity) e.sender;
					if(sender.isFinished) return;
					sender.runOnUiThread(new Runnable() {
						public void run() {
							// TODO Auto-generated method stub
							sender.handleModelViewEvent(modelEvent);
						}
					});
				} else if (e.sender instanceof DetailsTransactionActivity) {
						final DetailsTransactionActivity sender = (DetailsTransactionActivity) e.sender;
						sender.runOnUiThread(new Runnable() {
							public void run() {
								// TODO Auto-generated method stub
								sender.handleModelViewEvent(modelEvent);
							}
						});
				} else if (e.sender instanceof BaseFragment) {
					final BaseFragment sender = (BaseFragment) e.sender;
					if(sender.getActivity() == null){
						return;
					}
					sender.getActivity().runOnUiThread(new Runnable() {
						public void run() {
							// TODO Auto-generated method stub
							sender.handleModelViewEvent(modelEvent);
						}
					});
				}
			
			} else {
				handleErrorModelEvent(modelEvent);
			}
		} else {
			handleErrorModelEvent(modelEvent);
		}
	}

	
	/**
	* 	Xu ly cac su kien loi
	*  @author: truonglt2
	*  @param modelEvent
	*  @return: UserController
	*  @throws: 
	*/
	@Override
	public void handleErrorModelEvent(final ModelEvent modelEvent) {
		if (modelEvent.getModelCode() == ErrorConstants.ERROR_COMMON) {
			final ActionEvent e = modelEvent.getActionEvent();
			if (e.sender != null) {
				if (e.sender instanceof BaseActivity) {
					final BaseActivity sender = (BaseActivity) e.sender;
					if(sender.isFinished) return;
					sender.runOnUiThread(new Runnable() {
						public void run() {
							// TODO Auto-generated method stub
							ToastMessageUtil.showToastShort(sender, "Xảy ra lỗi trong quá trình xử lý dữ liệu.");
							try {
								Log.d("Mã lỗi xảy ra:",""+modelEvent.getModelMessage());
							} catch (Exception e2) {
								e2.printStackTrace();
							}
						}
					});
				} else if (e.sender instanceof DetailsTransactionActivity) {
						final DetailsTransactionActivity sender = (DetailsTransactionActivity) e.sender;
						sender.runOnUiThread(new Runnable() {
							public void run() {
								// TODO Auto-generated method stub
								ToastMessageUtil.showToastShort(sender, "Xảy ra lỗi trong quá trình xử lý dữ liệu.");
								try {
									Log.d("Mã lỗi xảy ra:",""+modelEvent.getModelMessage());
								} catch (Exception e2) {
									e2.printStackTrace();
								}
							}
						});
				} else if (e.sender instanceof BaseFragment) {
					final BaseFragment sender = (BaseFragment) e.sender;
					if(sender.getActivity() == null){
						return;
					}
					sender.getActivity().runOnUiThread(new Runnable() {
						public void run() {
							// TODO Auto-generated method stub
							ToastMessageUtil.showToastShort(sender.getActivity(), "Xảy ra lỗi trong quá trình xử lý dữ liệu.");
							try {
								Log.d("Mã lỗi xảy ra:",""+modelEvent.getModelMessage());
							} catch (Exception e2) {
								e2.printStackTrace();
							}
						}
					});
				}
			}
		}
	}
}
