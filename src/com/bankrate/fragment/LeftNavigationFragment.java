package com.bankrate.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.bankrate.R;
import com.bankrate.activity.MainActivity;
import com.bankrate.adapters.CatagoryListAdapter;
import com.bankrate.common.ActionEvent;
import com.bankrate.common.ActionEventConstant;
import com.bankrate.common.ModelEvent;
import com.bankrate.controller.UserController;
import com.bankrate.dto.Catagory;

/** 
  * @Description: lop menu ben trai
  * @author:truonglt2
  * @since:Feb 7, 2014 4:27:56 PM
  * @version: 1.0
  * @since: 1.0
  * 
  */
public class LeftNavigationFragment extends BaseFragment implements
		OnClickListener {

	// private static String KEY_ID_USER = "";
	CatagoryListAdapter adapterCatagory;
	ListView lvDeals;

	/**
	*   Khoi tao cac thanh phan
	*  @author: truonglt2
	*  @return
	*  @return: LeftNavigationFragment
	*  @throws: 
	*/
	public LeftNavigationFragment newInstance() {
		LeftNavigationFragment f = new LeftNavigationFragment();
		// Supply index input as an argument.
		Bundle args = new Bundle();
		f.setArguments(args);
		return f;
	}

	/**
	*  khoi tao view
	*  @author: truonglt2
	*  @param inflater
	*  @param container
	*  @param savedInstanceState
	*  @return
	*  @return: LeftNavigationFragment
	*  @throws: 
	*/
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.left_navigation, null);
		init(layout);
		return layout;
	}



	/**
	*  xu ly cac su kien click
	*  @author: truonglt2
	*  @param v
	*  @return: LeftNavigationFragment
	*  @throws: 
	*/
	@Override
	public void onClick(View v) {
	}

	/**
	 * init cac thanh phan giao dien
	 * 
	 * @author: truonglt2
	 * @param layout
	 * @return: BaseFragment
	 * @throws:
	 */
	@Override
	protected void init(View layout) {
		lvDeals = (ListView) layout.findViewById(R.id.listMenuLeft);

		adapterCatagory = new CatagoryListAdapter(getActivity());
		lvDeals.setAdapter(adapterCatagory);
		lvDeals.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
				Catagory item = (Catagory) adapterCatagory.getItem(position);// filldeal(adapterPro.get(position).getPromotionId());
				Fragment fragmentNew = ListBookOfCatagoryFragment.newInstance(item.getIdCatagory(), item.getTitle());
				((MainActivity) getActivity()).setFragmentContent(fragmentNew,ListBookOfCatagoryFragment.TAG);
				System.out.println(item.getIdCatagory());
			}
		});
		// new ProgressTaskListMoreDeal().execute();
		getDataShowList();
	}

	/**
	 * cai dat gia tri cho cac thanh phan
	 * 
	 * @author: truonglt2
	 * @return: BaseFragment
	 * @throws:
	 */
	@Override
	protected void setValueForMembers() {
		// TODO Auto-generated method stub

	}

	/**
	 * xu ly event cac thanh phan
	 * 
	 * @author: truonglt2
	 * @return: BaseFragment
	 * @throws:
	 */
	@Override
	protected void setEventForMembers() {
		// TODO Auto-generated method stub

	}

	/**
	 * get data to load list catagory
	 * 
	 * @author: truonglt2
	 * @return
	 * @return: BaseFragment
	 * @throws:
	 */
	protected void getDataShowList() {
		ActionEvent e = new ActionEvent();
		e.action = ActionEventConstant.GET_DATA_LIST_CATAGORY;
		e.sender = this;
		e.viewData = new Bundle();
		UserController.getInstance().handleViewEvent(e);
	}

	/**
	 * xu ly cac su kien tu model tra ve
	 * 
	 * @author: truonglt2
	 * @param modelEvent
	 * @return: BaseFragment
	 * @throws:
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void handleModelViewEvent(ModelEvent modelEvent) {
		Log.e(TAG,"qua day lan");
		// TODO Auto-generated method stub
		ActionEvent act = modelEvent.getActionEvent();
		switch (act.action) {
			case ActionEventConstant.GET_DATA_LIST_CATAGORY: {
					adapterCatagory.clearAll();
					ArrayList<Catagory> arrMoreDeal = (ArrayList<Catagory>) modelEvent.getModelData();
					for (int i = 0; i < arrMoreDeal.size(); i++) {
						adapterCatagory.addItem(arrMoreDeal.get(i));
					}
				}
				adapterCatagory.notifyDataSetChanged();
				break;
			default:
				break;
		}
	}
	/**
	*  huy fragment
	*  @author: truonglt2
	*  @return: LeftNavigationFragment
	*  @throws: 
	*/
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
