package com.bankrate.fragment;

import java.util.ArrayList;

import android.app.Service;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bankrate.R;
import com.bankrate.adapters.BookListAdapter;
import com.bankrate.common.ActionEvent;
import com.bankrate.common.ActionEventConstant;
import com.bankrate.common.ModelEvent;
import com.bankrate.controller.UserController;
import com.bankrate.dto.Book;

/** 
  * @Description: This fragment is search navigation
  * @author:truonglt2
  * @since:Feb 7, 2014 4:51:01 PM
  * @version: 1.0
  * @since: 1.0
  * 
  */
public class SearchNavigationFragment extends BaseFragment implements OnClickListener {

    // mEditTextSearch is edit text search node
    private EditText mEditTextSearch;

    // Progress bar
    // mProgressBar is progress bar for first load
    private ProgressBar mProgressBar;
    // mProgressBarLoadMore is progress bar for load more
    private ProgressBar mProgressBarLoadMore;

    // List view of result node
    private ListView mListViewSearch;


    // mKeyWord is key word that you type to search
    private String mKeyWord = "";

    // footer is footer of list view
    private View footer;

    // Image view
    private ImageView mImageViewClearTextSearch;

    private TextView mTxtSearchFor;
    Context mContext;
    BookListAdapter adapterBook;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Get parent activity
//        mContext = getActivity();
        // set layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_search_navigation, null);
        init(layout);
        setValueForMembers();
        setEventForMembers();
        return layout;
    }

    @Override
    protected void init(View layout) {
    	mContext = getActivity();
        // Edit text
        mEditTextSearch = (EditText) layout.findViewById(R.id.right_navigation_search_view);
        // Init list view
        mListViewSearch = (ListView) layout.findViewById(R.id.list_view_search);
        // Image view
        mImageViewClearTextSearch = (ImageView) layout.findViewById(R.id.image_view_clear_text_search);

        // init a footer for list view
        footer = ((LayoutInflater)getActivity().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE)).inflate(
                        R.layout.footer_list_view, null, false);
        footer.setBackgroundColor(getResources().getColor(R.color.white));
        // progress bar for frist load
        mProgressBar = (ProgressBar) layout.findViewById(R.id.progress_bar_search);
        // Textview
        mTxtSearchFor = (TextView) layout.findViewById(R.id.txt_search_for);
        // progress bar for load more
        mProgressBarLoadMore = (ProgressBar) footer.findViewById(R.id.progress_bar_load_more);

		adapterBook = new BookListAdapter(getActivity());
		mListViewSearch.setAdapter(adapterBook);
		
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
	 * get research data search to load list book
	 * 
	 * @author: truonglt2
	 * @return
	 * @return: BaseFragment
	 * @throws:
	 */
	protected void getDataSearchShowList(String text) {
		ActionEvent e = new ActionEvent();
		e.action = ActionEventConstant.GET_DATA_SEARCH_LIST_BOOK;
		e.sender = this;
		Bundle bundle = new Bundle();
//		String idCatagory = getArguments().getString("idCatagory");
		bundle.putString("textSearch", text);
		e.viewData = bundle;
		UserController.getInstance().handleViewEvent(e);
	}
    @Override
    protected void setValueForMembers() {
        // add footer load more for list view
        footer.setBackgroundColor(getResources().getColor(R.color.color_black));
        // Add footer for list view
        mListViewSearch.addFooterView(footer);
        // Set adapter for list view
//        mListViewSearch.setAdapter(mSearchAdapter);

        // First hide progress bar
        mProgressBar.setVisibility(View.GONE);
        mProgressBarLoadMore.setVisibility(View.GONE);
    }

    /**
    *  set event cho cac thanh phan
    *  @author: truonglt2
    *  @return: SearchNavigationFragment
    *  @throws: 
    */
    @Override
    protected void setEventForMembers() {
//    	if (mEditTextSearch.requestFocus()) {
//    		showKeyBoard();
//    	}
        mEditTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                    int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            	String keyWord = mEditTextSearch.getText().toString().trim();
                if (mKeyWord.length() >= 3) {
                	getDataSearchShowList(keyWord);
                } else {
                    mTxtSearchFor.setVisibility(View.VISIBLE);
                    mTxtSearchFor.setText("Search Book...");
                }
            	//ToastMessageUtil.showToastShort(mContext, "Xin bạn thông cảm. Hiện chức năng này chưa được hỗ trợ. Chúng tôi sẽ hỗ trợ trong thời gian nhanh nhất có thể. ^^");
            }
        });

        mEditTextSearch.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getKeyCode() == KeyEvent.KEYCODE_SEARCH)
                        || (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    String keyWord = mEditTextSearch.getText().toString().trim();
                	getDataSearchShowList(keyWord);
//                	ToastMessageUtil.showToastShort(mContext, "Xin bạn thông cảm. Hiện chức năng này chưa được hỗ trợ. Chúng tôi sẽ hỗ trợ trong thời gian nhanh nhất có thể. ^^");
                }
                return false;
            }
        });

        // set on scroll listener for list view
        mListViewSearch.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // when list view is fling then don't load image
                hideSoftKeyboard();
            }
            
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                    int visibleItemCount, int totalItemCount) {
                boolean isBottom = (firstVisibleItem + visibleItemCount) == totalItemCount;
                // load more
                if (isBottom) {}
            }
        });

        mListViewSearch.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long id) {
				//change activity
				Book itemBook = (Book) adapterBook.getItem(position);
				System.out.println(itemBook.getIdBook());
				changeViewDetailActivity(itemBook);
			}
		});

        mImageViewClearTextSearch.setOnClickListener(this);
    }

    @Override
    public void onResume() {
    	super.onResume();
    }

    @Override
    public void onPause() {
    	super.onPause();
    }


    /**
    *  xu ly su kien click
    *  @author: truonglt2
    *  @param v
    *  @return: void
    *  @throws: 
    */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.image_view_clear_text_search) {
            // Clear search text
            mEditTextSearch.setText("");
        }
    }

    /**
     * This method is used to hide soft keyboard
     * @author truonglt2
     */
    private void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity()
                .getSystemService(Service.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEditTextSearch.getWindowToken(), 0);
    }


	/**
	*  xu ly cac su kien cua model tra ve
	*  @author: truonglt2
	*  @param modelEvent
	*  @return: BaseFragment
	*  @throws: 
	*/
	@Override
	public void handleModelViewEvent(ModelEvent modelEvent) {
		// TODO Auto-generated method stub
		ActionEvent act = modelEvent.getActionEvent();
		switch (act.action) {
			case ActionEventConstant.GET_DATA_SEARCH_LIST_BOOK: {
				adapterBook.clearAll();
				@SuppressWarnings("unchecked")
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
