package com.bankrate.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bankrate.R;
import com.bankrate.adapters.BookListAdapter;
import com.bankrate.common.ActionEvent;
import com.bankrate.common.ActionEventConstant;
import com.bankrate.common.ModelEvent;
import com.bankrate.controller.UserController;
import com.bankrate.dto.Book;

/** 
  * @Description: lop List Book Of Catagory Fragment
  * @author:truonglt2
  * @since:Feb 7, 2014 4:45:04 PM
  * @version: 1.0
  * @since: 1.0
  * 
  */
public class ListBookOfCatagoryFragment extends BaseFragment implements
		OnClickListener {

	BookListAdapter adapterBook;
	ListView lvBook;
	TextView textCatagoryListBook;
	ImageView imgCatagoryListBook;
	public static ListBookOfCatagoryFragment newInstance(String idCatagory,String nameCatagory) {
		ListBookOfCatagoryFragment f = new ListBookOfCatagoryFragment();
		// Supply index input as an argument.
		Bundle args = new Bundle();
		args.putString("idCatagory", idCatagory);
		args.putString("nameCatagory", nameCatagory);
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
	*  @return: ListBookOfCatagoryFragment
	*  @throws: 
	*/
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.list_book_of_catagory_layout, null);
		init(layout);
		return layout;
	}



	/**
	*  xu ly cac su kien click view
	*  @author: truonglt2
	*  @param v
	*  @return: ListBookOfCatagoryFragment
	*  @throws: 
	*/
	@Override
	public void onClick(View v) {
	}

	/**
	 * Khoi tao cac thanh phan cua view
	 * 
	 * @author: truonglt2
	 * @param layout
	 * @return: BaseFragment
	 * @throws:
	 */
	@Override
	protected void init(View layout) {
		lvBook = (ListView) layout.findViewById(R.id.listBook);
		textCatagoryListBook =(TextView)layout.findViewById(R.id.text_catagory_list_book);
		imgCatagoryListBook = (ImageView)layout.findViewById(R.id.img_catagory_list_book);
		adapterBook = new BookListAdapter(getActivity());
		lvBook.setAdapter(adapterBook);
		lvBook.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long id) {
				
				//change activity
				Book itemBook = (Book) adapterBook.getItem(position);
				System.out.println(itemBook.getIdBook());
				changeViewDetailActivity(itemBook);
			}
		});
		// new ProgressTaskListMoreDeal().execute();
		String idCatagory = getArguments().getString("idCatagory");
		String name=getArguments().getString("nameCatagory");
		textCatagoryListBook.setText(""+name);
		try {
			int id  = Integer.parseInt(idCatagory);
			switch (id) {
			case 1:
				imgCatagoryListBook.setImageResource(R.drawable.icon_family);
				break;
			case 2:
				imgCatagoryListBook.setImageResource(R.drawable.icon_logistics);
				break;
			case 3:
				imgCatagoryListBook.setImageResource(R.drawable.icon_fileshare);
				break;
			case 4:
				imgCatagoryListBook.setImageResource(R.drawable.icon_launch);
				break;
			case 5:
				imgCatagoryListBook.setImageResource(R.drawable.icon_radio);
				break;
			case 6:
				imgCatagoryListBook.setImageResource(R.drawable.icon_thumbnail);
				break;
			case 7:
				imgCatagoryListBook.setImageResource(R.drawable.icon_language_skills);
				break;
			case 8:
				imgCatagoryListBook.setImageResource(R.drawable.icon_computer);
				break;
			case 9:
				imgCatagoryListBook.setImageResource(R.drawable.icon_users_mixed_gender_race);
				break;
			case 10:
				imgCatagoryListBook.setImageResource(R.drawable.icon_editcopy);
				break;
			case 11:
				imgCatagoryListBook.setImageResource(R.drawable.icon_display_comics);
				break;
			case 12:
				imgCatagoryListBook.setImageResource(R.drawable.icon_address_book);
				break;
			case 13:
				imgCatagoryListBook.setImageResource(R.drawable.icon_book_audio_run);
				break;
			case 14:
				imgCatagoryListBook.setImageResource(R.drawable.icon_start_here);
				break;
			case 15:
				imgCatagoryListBook.setImageResource(R.drawable.icon_newspaper_new);
				break;
			case 16:
				imgCatagoryListBook.setImageResource(R.drawable.icon_stock_new_chart_next_graph);
				break;
			case 17:
				imgCatagoryListBook.setImageResource(R.drawable.icon_medical_case);
				break;
			case 18:
				imgCatagoryListBook.setImageResource(R.drawable.icon_misc_box);
				break;
			case 19:
				imgCatagoryListBook.setImageResource(R.drawable.icon_music);
				break;
			default:
				imgCatagoryListBook.setImageResource(R.drawable.icon_address_book);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		getDataShowList();
	}
	/**
	 * Change activity detail book details
	 * 
	 * @author: truonglt2
	 * @return: void
	 * @throws:
	 */
	private void changeViewDetailActivity(Book book) {
		ActionEvent e = new ActionEvent();
		e.sender = getActivity();
		Bundle bundle = new Bundle();
//		bundle.putString("idBook", value)
		bundle.putSerializable("Book", book);
		e.viewData = bundle;
		e.action = ActionEventConstant.ACTION_CHANGE_VIEW_DETAIL_BOOK_ACTIVTY;
		UserController.getInstance().handleSwitchActivity(e);
	}
	/**
	 * set gia tri cho cac thanh phan
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
	 * Set su kien cho cac thanh phan
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
		e.action = ActionEventConstant.GET_DATA_LIST_BOOK_OF_CATAGORY;
		e.sender = this;
		Bundle bundle = new Bundle();
		String idCatagory = getArguments().getString("idCatagory");
		bundle.putString("idCatagory", idCatagory);
		e.viewData = bundle;
		UserController.getInstance().handleViewEvent(e);
	}

	/**
	 * xu ly du lieu tu ben model tra ve
	 * 
	 * @author: truonglt2
	 * @param modelEvent
	 * @return: BaseFragment
	 * @throws:
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void handleModelViewEvent(ModelEvent modelEvent) {
		// TODO Auto-generated method stub
		ActionEvent act = modelEvent.getActionEvent();
		switch (act.action) {
			case ActionEventConstant.GET_DATA_LIST_BOOK_OF_CATAGORY: {
				adapterBook.clearAll();
				ArrayList<Book> arrMoreDeal = (ArrayList<Book>) modelEvent.getModelData();
				for (int i = 0; i < arrMoreDeal.size(); i++) {
					adapterBook.addItem(arrMoreDeal.get(i));
				}
			}
			adapterBook.notifyDataSetChanged();
			break;
		}
	}
}
