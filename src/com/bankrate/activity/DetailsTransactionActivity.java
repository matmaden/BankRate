package com.bankrate.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.bankrate.R;
import com.bankrate.adapters.MenuListAdapter;
import com.bankrate.common.ActionEvent;
import com.bankrate.common.ActionEventConstant;
import com.bankrate.common.Common;
import com.bankrate.common.ModelEvent;
import com.bankrate.controller.UserController;
import com.bankrate.dto.Book;
import com.bankrate.dto.MenuContent;
import com.bankrate.fragment.DetailBookFragment;
import com.bankrate.fragment.InforBookFragment;

/** 
  * @Description: activity man hinh detail of book
  * @author:truonglt2
  * @since:Feb 7, 2014 4:23:33 PM
  * @version: 1.0
  * @since: 1.0
  * 
  */
public class DetailsTransactionActivity extends Activity {
	// Within which the entire activity is enclosed
	private DrawerLayout mDrawerLayout;

	// ListView represents Navigation Drawer
	private ListView mDrawerList;

	// ActionBarDrawerToggle indicates the presence of Navigation Drawer in the
	// action bar
	private ActionBarDrawerToggle mDrawerToggle;

	// Title of the action bar
	private String mTitle = "";
	// define adapter menu catagory
	MenuListAdapter adapterMenu;
	Book mBook = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_book_activity);
		Common.PACKAGE_NAME = getApplicationContext().getPackageName();
//		mTitle = getString(R.string.app_name);
		
		try {
			mBook = (Book) getIntent().getExtras().getSerializable("Book");
			getActionBar().setTitle(mBook.getTitle());
			mTitle = mBook.getTitle(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		
		// Getting reference to the DrawerLayout
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		mDrawerList = (ListView) findViewById(R.id.drawer_list);

		// Getting reference to the ActionBarDrawerToggle
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			/** Called when drawer is closed */
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
//				invalidateOptionsMenu();

			}

			/** Called when a drawer is opened */
			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mBook.getTitle());
//				invalidateOptionsMenu();
			}

		};

		// Setting DrawerToggle on DrawerLayout
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		// Creating an Adapter to add items to the listview mDrawerList
		adapterMenu = new MenuListAdapter(this);
		
		// Setting the adapter on mDrawerList
		mDrawerList.setAdapter(adapterMenu);
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			// Enabling Home button
			getActionBar().setHomeButtonEnabled(true);
			// Enabling Up navigation
			getActionBar().setDisplayHomeAsUpEnabled(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		// Setting item click listener for the listview mDrawerList
		mDrawerList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// Getting an array of adapter
				MenuContent content = (MenuContent)adapterMenu.getItem(position);
				// Currently selected river
				mTitle = content.getTitle();
				changeContentFragment(content);
				// Closing the drawer
				mDrawerLayout.closeDrawer(mDrawerList);

			}
		});
		try {
			getDataShowList(mBook.getIdBook());
			changeInforBookFragment(mBook);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	*  Change fragment content of book
	*  @author: Truonglt2
	*  @param content
	*  @return: void
	*  @throws: 
	*/
	protected void changeInforBookFragment(Book book) {
		InforBookFragment bFragment = InforBookFragment.newInstance(book);
		// Getting reference to the FragmentManager
		FragmentManager fragmentManager = getFragmentManager();
		// Creating a fragment transaction
		FragmentTransaction ft = fragmentManager.beginTransaction();
		// Adding a fragment to the fragment transaction
		ft.replace(R.id.content_frame, bFragment);
		// Committing the transaction
		ft.commit();
	}
	/**
	*  Change fragment content of book
	*  @author: Truonglt2
	*  @param content
	*  @return: void
	*  @throws: 
	*/
	protected void changeContentFragment(MenuContent content) {
		DetailBookFragment bFragment = DetailBookFragment.newInstance(content);
		// Getting reference to the FragmentManager
		FragmentManager fragmentManager = getFragmentManager();
		// Creating a fragment transaction
		FragmentTransaction ft = fragmentManager.beginTransaction();
		// Adding a fragment to the fragment transaction
		ft.replace(R.id.content_frame, bFragment);
		// Committing the transaction
		ft.commit();
	}
	/**
	 * get data to load list catagory
	 * 
	 * @author: truonglt2
	 * @return
	 * @return: BaseFragment
	 * @throws:
	 */
	protected void getDataShowList(String idBook) {
		ActionEvent e = new ActionEvent();
		e.action = ActionEventConstant.GET_DATA_LIST_MENU_OF_BOOK;
		e.sender = this;
		Bundle bundle = new Bundle();
		bundle.putString("idBook", idBook);
		e.viewData = bundle;
		UserController.getInstance().handleViewEvent(e);
	}
	/**
	*  Xu ly cac su kien tu model tra ve
	*  
	*  @author: Truonglt2
	*  @param modelEvent
	*  @return: void
	*  @throws: 
	*/
	@SuppressWarnings("unchecked")
	public void handleModelViewEvent(ModelEvent modelEvent) {
		ActionEvent act = modelEvent.getActionEvent();
		switch (act.action) {
			case ActionEventConstant.GET_DATA_LIST_MENU_OF_BOOK: {
				ArrayList<MenuContent> arrMoreDeal = (ArrayList<MenuContent>) modelEvent.getModelData();
				for (int i = 0; i < arrMoreDeal.size(); i++) {
					adapterMenu.addItem(arrMoreDeal.get(i));
				}
			}
			adapterMenu.notifyDataSetChanged();
			break;
		}
	}
	/**
	* 	khoi tao trang thai mDrawerToggle cua action bar
	*  @author: truonglt2
	*  @param savedInstanceState
	*  @return: DetailsBookActivity
	*  @throws: 
	*/
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	/**
	*  cai dat cac option cua action bar
	*  @author: truonglt2
	*  @param item
	*  @return
	*  @return: DetailsBookActivity
	*  @throws: 
	*/
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/** Called whenever we call invalidateOptionsMenu() */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// If the drawer is open, hide action items related to the content view
//		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
//
//		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}
	/**
	*  huy activity
	*  @author: truonglt2
	*  @return: DetailsBookActivity
	*  @throws: 
	*/
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	/**
	*  bat su kien phim bat
	*  @author: truonglt2
	*  @return: DetailsBookActivity
	*  @throws: 
	*/
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}
}
