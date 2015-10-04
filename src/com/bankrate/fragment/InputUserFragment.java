package com.bankrate.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.bankrate.R;
import com.bankrate.common.ModelEvent;

/**
 * @Description: lop gioi thieu ve minh
 * @author:truonglt2
 * @since:Feb 7, 2014 3:45:46 PM
 * @version: 1.0
 * @since: 1.0
 * 
 */
public class InputUserFragment extends BaseFragment implements OnClickListener
		 {
	public static final String TAG = InputUserFragment.class.getSimpleName(); // This
																				// is
																				// tag
																				// to
																				// show
																				// log

	public static InputUserFragment newInstance() {
		InputUserFragment f = new InputUserFragment();
		Bundle args = new Bundle();
		f.setArguments(args);
		return f;
	}

	/**
	 * khoi tao view
	 * 
	 * @author: truonglt2
	 * @param inflater
	 * @param container
	 * @param savedInstanceState
	 * @return
	 * @return: AboutMeFragment
	 * @throws:
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
//		View rootView = inflater.inflate(R.layout.fragment_user_infor,
//				container, false);
		View layout = inflater.inflate(R.layout.fragment_user_infor, null);
		init(layout);
		setValueForMembers();
		setEventForMembers();
		return layout;
	}

	/**
	 * kho tao cac thanh phan giao dien
	 * 
	 * @author: truonglt2
	 * @param layout
	 * @return: AboutMeFragment
	 * @throws:
	 */
	@Override
	protected void init(View layout) {
	}

	/**
	 * set gia tri cho cac thanh phan
	 * 
	 * @author: truonglt2
	 * @return: AboutMeFragment
	 * @throws:
	 */
	@Override
	protected void setValueForMembers() {
		// TODO Auto-generated method stub
	}

	/**
	 * set su kien cho cac thanh phan
	 * 
	 * @author: truonglt2
	 * @return: AboutMeFragment
	 * @throws:
	 */
	@Override
	protected void setEventForMembers() {
		// TODO Auto-generated method stub
	}

	/**
	 * xu ly cac su kien onclick
	 * 
	 * @author: truonglt2
	 * @param v
	 * @return: AboutMeFragment
	 * @throws:
	 */
	@Override
	public void onClick(View v) {

	}


	/**
	 * xu ly su kien cua model
	 * 
	 * @author: truonglt2
	 * @param modelEvent
	 * @return: BaseFragment
	 * @throws:
	 */
	@Override
	public void handleModelViewEvent(ModelEvent modelEvent) {
		// TODO Auto-generated method stub

	}

}
