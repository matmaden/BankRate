package com.bankrate.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bankrate.R;
import com.bankrate.common.Common;
import com.bankrate.dto.Book;

/** 
  * @Description: adapter cua list book
  * @author:truonglt2
  * @since:Feb 7, 2014 3:49:14 PM
  * @version: 1.0
  * @since: 1.0
  * 
  */
public class BookListAdapter extends BaseAdapter {

	ArrayList<Book> arr = new ArrayList<Book>();
	LayoutInflater inflater = null;
	int requestWidth;
	int requestHeight;
	/**
	 * @param context
	 */
	public BookListAdapter(Context context) {
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		int scoreScreen = (int) (Common.sizeScreenWidth / 12);
		requestHeight = (int) ((scoreScreen * 3));
		requestWidth = (int) ((scoreScreen * 3) / (1.6));
	}

	public Book get(int pos) {
		return arr.get(pos);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MoredealsViewHolder viewHolder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_list_book, null);
			viewHolder = new MoredealsViewHolder();
			viewHolder.imageBook = (ImageView) convertView
					.findViewById(R.id.imgBook);
			viewHolder.nameBook = (TextView) convertView
					.findViewById(R.id.tvtTitleBooks);
			viewHolder.Decripstion = (TextView) convertView
					.findViewById(R.id.tvtDecriptionBook);
			viewHolder.priceBook = (TextView) convertView
					.findViewById(R.id.tvtPriceBook);
			viewHolder.ratingStarbook = (RatingBar)convertView.findViewById(R.id.rating_starbook);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (MoredealsViewHolder) convertView.getTag();
		}

		try {
			viewHolder.nameBook.setText(arr.get(position).getTitle());
			viewHolder.Decripstion.setText(arr.get(position).getDescription());
			viewHolder.priceBook.setText(arr.get(position).getPrice());
			viewHolder.ratingStarbook.setRating(Float.parseFloat(arr.get(position).getRate()));
			System.out.println("chuoi url"+arr.get(position).getUrlImage());
			Common.imageLoad.DisplayImage(arr.get(position).getUrlImage(),viewHolder.imageBook, requestWidth, requestHeight);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return convertView;
	}

	public void setSelectedPosition(int pos) {
		notifyDataSetChanged();
	}

	public void addItem(Book item) {
		arr.add(item);
	}

	public void clearAll() {
		arr.clear();
		notifyDataSetChanged();
	}

	public int getCount() {
		return arr.size();
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public Object getItem(int position) {
		return (Book) arr.get(position);
	}

	/** 
	  * @author:truonglt2
	  * @since:Feb 7, 2014 3:09:01 PM
	  * @Description: lop holder temp
	  */
	class MoredealsViewHolder {
		public TextView nameBook;
		public ImageView imageBook;
		public TextView Decripstion;
		public TextView priceBook;
		public RatingBar ratingStarbook;
	}
}