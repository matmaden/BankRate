package com.bankrate.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.bankrate.R;
import com.bankrate.common.ModelEvent;

/** 
  * @Description: lop Store Details Fragment
  * @author:truonglt2
  * @since:Feb 7, 2014 4:56:22 PM
  * @version: 1.0
  * @since: 1.0
  * 
  */
public class StoreDetailsFragment extends BaseFragment implements
		OnClickListener {
	public static final String TAG = StoreDetailsFragment.class
			.getSimpleName(); // This is tag to show log

	/**
	*  khoi tao instance cho fragment
	*  @author: Truonglt2
	*  @return
	*  @return: StoreDetailsFragment
	*  @throws: 
	*/
	public static Fragment newInstance() {
		StoreDetailsFragment f = new StoreDetailsFragment();
		// Supply index input as an argument.
		Bundle args = new Bundle();
		f.setArguments(args);
		return f;
	}

	/**
	*  create view
	*  @author: Truonglt2
	*  @param inflater
	*  @param container
	*  @param savedInstanceState
	*  @return
	*  @return: StoreDetailsFragment
	*  @throws: 
	*/
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.store_detail_fragment, null);
		init(layout);
		setValueForMembers();
		setEventForMembers();
		return layout;
	}

	/**
	*  cai dat cac thanh phan giao dien
	*  @author: ADMIN
	*  @param layout
	*  @return: StoreDetailsFragment
	*  @throws: 
	*/
	@Override
	protected void init(View layout) {

	}

	/**
	*  set gia tri cho cac thanh phan
	*  @author: Truonglt2
	*  @return: StoreDetailsFragment
	*  @throws: 
	*/
	@Override
	protected void setValueForMembers() {
		// TODO Auto-generated method stub

	}

	/**
	*  set su kien cho cac thanh phan
	*  @author: Truonglt2
	*  @return: StoreDetailsFragment
	*  @throws: 
	*/
	@Override
	protected void setEventForMembers() {
	}

	/**
	*  xu ly onlick
	*  @author: Truonglt2
	*  @param view
	*  @return: StoreDetailsFragment
	*  @throws: 
	*/
	@Override
	public void onClick(View v) {

	}

	/**
	*  xu ly su kien ben model tra ve
	*  @author: truonglt2
	*  @param modelEvent
	*  @return: BaseFragment
	*  @throws: 
	*/
	@Override
	public void handleModelViewEvent(ModelEvent modelEvent) {
		// TODO Auto-generated method stub
		
	}

}
