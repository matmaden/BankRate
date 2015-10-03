package com.bankrate.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bankrate.R;
import com.bankrate.common.Common;
import com.bankrate.dto.Book;

/** 
  * @Description: lop infor book fragment
  * @author:truonglt2
  * @since:Feb 7, 2014 4:27:03 PM
  * @version: 1.0
  * @since: 1.0
  * 
  */
public class InforBookFragment extends Fragment {
	public static final String TAG = InforBookFragment.class.getSimpleName(); // This is tag to show log
	public static InforBookFragment newInstance(Book book) {
		InforBookFragment f = new InforBookFragment();
		Bundle args = new Bundle();
		args.putSerializable("Book", book);
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
	*  @return: InforBookFragment
	*  @throws: 
	*/
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.infor_book_fragment, null);
		Book currentBook = (Book)getArguments().getSerializable("Book"); 
		
		ImageView imageBook = (ImageView) layout.findViewById(R.id.img_Book);
		TextView titleBook = (TextView) layout.findViewById(R.id.tvtTitleBook);
		TextView tvtAuthor = (TextView) layout.findViewById(R.id.tvtAuthor);
		TextView tvtDescription = (TextView) layout.findViewById(R.id.tvtDescription);
		TextView tvtPrice = (TextView) layout.findViewById(R.id.tvtPrice);
		RatingBar ratingStarbook = (RatingBar)layout.findViewById(R.id.rating_starbook);
		
		titleBook.setText(currentBook.getTitle());
		tvtAuthor.setText(currentBook.getAuthor());
		tvtPrice.setText(currentBook.getPrice());
		tvtDescription.setText(currentBook.getDescription());
		ratingStarbook.setRating(Float.parseFloat(currentBook.getRate()));
		Common.imageLoad.DisplayImage(currentBook.getUrlImage(), imageBook, 200, 250);
		
		return layout;
	}
	

}
