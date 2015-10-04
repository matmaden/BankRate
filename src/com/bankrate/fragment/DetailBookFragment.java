package com.bankrate.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.bankrate.R;
import com.bankrate.dto.MenuContent;

/** 
  * @Description: lop Detail Book Fragment
  * @author:truonglt2
  * @since:Feb 7, 2014 4:25:08 PM
  * @version: 1.0
  * @since: 1.0
  * 
  */
public class DetailBookFragment extends Fragment {
	public static final String TAG = DetailBookFragment.class.getSimpleName(); // This is tag to show log
	MenuContent mContent;//bien content
	/**
	*  khoi tao instance cua lop
	*  @author: truonglt2
	*  @param content
	*  @return
	*  @return: DetailBookFragment
	*  @throws: 
	*/
	public static DetailBookFragment newInstance(MenuContent content) {
		DetailBookFragment f = new DetailBookFragment();
		Bundle args = new Bundle();
		args.putSerializable("Content", content);
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
	*  @return: DetailBookFragment
	*  @throws: 
	*/
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.content_book_fragment, null);
		init(layout);
		return layout;
	}
	
	/**
	*  init cac thanh phan giao dien
	*  @author: truonglt2
	*  @param layout
	*  @return: void
	*  @throws: 
	*/
	@SuppressLint("SetJavaScriptEnabled")
	protected void init(View layout) {
		mContent = (MenuContent) getArguments().getSerializable("Content");
		WebView webView = (WebView)layout.findViewById(R.id.webView); 
		webView.getSettings().setJavaScriptEnabled(true);
	}

}
