package com.bankrate.fragment;

import android.support.v4.app.Fragment;
import android.view.View;

import com.bankrate.common.ModelEvent;

/** 
  * @Description: lop BaseFragment
  * @author:truonglt2
  * @since:Feb 7, 2014 4:24:20 PM
  * @version: 1.0
  * @since: 1.0
  * 
  */
public abstract class BaseFragment extends Fragment {

	public static final String TAG = "Viettel ITC"; // This is tag to show log
	protected static boolean isDebug = true; // This is used to show log
	protected int mCountPage = 1; // This is use to count the number of page api
	protected boolean mIsHaveNoAPIData = false; // This is a flag variable, this is use to check
										// there is no data more
	/**
	 * This method is used to init all members of fragment
	 */
	protected abstract void init(View layout);

	/**
	 * This method is used to set value for all members of fragment
	 */
	protected abstract void setValueForMembers();

	/**
	 * This method is used to set event for member
	 */
	protected abstract void setEventForMembers();

	/**
	*  xu ly du lieu data model
	*  @author: truonglt2
	*  @param modelEvent
	*  @return: void
	*  @throws: 
	*/
	public abstract void handleModelViewEvent(ModelEvent modelEvent);
}
