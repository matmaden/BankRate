package com.bankrate.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.bankrate.R;
import com.bankrate.activity.MainActivity;
import com.bankrate.adapters.MenuListAdapter;
import com.bankrate.common.ActionEvent;
import com.bankrate.common.ActionEventConstant;
import com.bankrate.common.Common;
import com.bankrate.common.ModelEvent;
import com.bankrate.controller.UserController;
import com.bankrate.dto.MenuContent;

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
	MenuListAdapter adapterMenu;
	ListView lvMenus;

	/**
	 * Khoi tao cac thanh phan
	 * 
	 * @author: truonglt2
	 * @return
	 * @return: LeftNavigationFragment
	 * @throws:
	 */
	public LeftNavigationFragment newInstance() {
		LeftNavigationFragment f = new LeftNavigationFragment();
		// Supply index input as an argument.
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
	 * @return: LeftNavigationFragment
	 * @throws:
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.left_navigation, null);
		init(layout);
		return layout;
	}

	/**
	 * xu ly cac su kien click
	 * 
	 * @author: truonglt2
	 * @param v
	 * @return: LeftNavigationFragment
	 * @throws:
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
		lvMenus = (ListView) layout.findViewById(R.id.listMenuLeft);

		// Creating an Adapter to add items to the listview mDrawerList
		adapterMenu = new MenuListAdapter(getActivity());

		// Setting the adapter on mDrawerList
		lvMenus.setAdapter(adapterMenu);
		lvMenus.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long id) {
				MenuContent item = (MenuContent) adapterMenu.getItem(position);// filldeal(adapterPro.get(position).getPromotionId());
				goScreenWithItemSelected(item);
			}
		});
		// new ProgressTaskListMoreDeal().execute();
		getDataShowList();
	}

	void goScreenWithItemSelected(MenuContent item) {
		switch (item.getIdMenu()) {
		case Common.SCREEN_INFOR_RATE: {
			Fragment fragmentNew = StoreDetailsFragment.newInstance();
			((MainActivity) getActivity()).setFragmentContent(fragmentNew,
					StoreDetailsFragment.TAG);
			break;
		}
		case Common.SCREEN_INFOR_LOAN: {
			Fragment fragmentNew = ListBookOfCatagoryFragment.newInstance("","");
			((MainActivity) getActivity()).setFragmentContent(fragmentNew,
					ListBookOfCatagoryFragment.TAG);
			break;
		}

		default:
			break;
		}
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
		e.action = ActionEventConstant.GET_DATA_LIST_MENU;
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
		ActionEvent act = modelEvent.getActionEvent();
		switch (act.action) {
		case ActionEventConstant.GET_DATA_LIST_MENU: {
			adapterMenu.clearAll();
			ArrayList<MenuContent> arrMenu = (ArrayList<MenuContent>) modelEvent
					.getModelData();
			for (int i = 0; i < arrMenu.size(); i++) {
				adapterMenu.addItem(arrMenu.get(i));
			}
			adapterMenu.notifyDataSetChanged();
			lvMenus.setAdapter(adapterMenu);
		}
			break;
		default:
			break;
		}
	}

	/**
	 * huy fragment
	 * 
	 * @author: truonglt2
	 * @return: LeftNavigationFragment
	 * @throws:
	 */
	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}
