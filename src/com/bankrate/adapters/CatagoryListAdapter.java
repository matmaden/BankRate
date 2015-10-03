package com.bankrate.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bankrate.R;
import com.bankrate.dto.Catagory;

/**
 * @Description: adapter cua catagory
 * @author:truonglt2
 * @since:Feb 7, 2014 3:49:34 PM
 * @version: 1.0
 * @since: 1.0
 * 
 */
public class CatagoryListAdapter extends BaseAdapter {

	ArrayList<Catagory> arr = new ArrayList<Catagory>();
	LayoutInflater inflater = null;

	/**
	 * @param context
	 */
	public CatagoryListAdapter(Context context) {
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public Catagory get(int pos) {
		return arr.get(pos);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MoredealsViewHolder viewHolder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_catagory, null);
			viewHolder = new MoredealsViewHolder();
			viewHolder.imageCatagory = (ImageView) convertView
					.findViewById(R.id.img_catagory);
			viewHolder.nameCatagory = (TextView) convertView
					.findViewById(R.id.tvtTitleCatagory);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (MoredealsViewHolder) convertView.getTag();
		}

		viewHolder.nameCatagory.setText(arr.get(position).getTitle());
		try {
			// int idItem = Integer.parseInt(arr.get(position).getIdCatagory());
			switch (position) {
			case 0:
				viewHolder.imageCatagory
						.setImageResource(R.drawable.icon_family);
				break;
			case 1:
				viewHolder.imageCatagory
						.setImageResource(R.drawable.icon_logistics);
				break;
			case 2:
				viewHolder.imageCatagory
						.setImageResource(R.drawable.icon_fileshare);
				break;
			case 3:
				viewHolder.imageCatagory
						.setImageResource(R.drawable.icon_launch);
				break;
			case 4:
				viewHolder.imageCatagory
						.setImageResource(R.drawable.icon_radio);
				break;
			case 5:
				viewHolder.imageCatagory
						.setImageResource(R.drawable.icon_thumbnail);
				break;
			case 6:
				viewHolder.imageCatagory
						.setImageResource(R.drawable.icon_language_skills);
				break;
			case 7:
				viewHolder.imageCatagory
						.setImageResource(R.drawable.icon_computer);
				break;
			case 8:
				viewHolder.imageCatagory
						.setImageResource(R.drawable.icon_users_mixed_gender_race);
				break;
			case 9:
				viewHolder.imageCatagory
						.setImageResource(R.drawable.icon_editcopy);
				break;
			case 10:
				viewHolder.imageCatagory
						.setImageResource(R.drawable.icon_display_comics);
				break;
			case 11:
				viewHolder.imageCatagory
						.setImageResource(R.drawable.icon_address_book);
				break;
			case 12:
				viewHolder.imageCatagory
						.setImageResource(R.drawable.icon_book_audio_run);
				break;
			case 13:
				viewHolder.imageCatagory
						.setImageResource(R.drawable.icon_start_here);
				break;
			case 14:
				viewHolder.imageCatagory
						.setImageResource(R.drawable.icon_newspaper_new);
				break;
			case 15:
				viewHolder.imageCatagory
						.setImageResource(R.drawable.icon_stock_new_chart_next_graph);
				break;
			case 16:
				viewHolder.imageCatagory
						.setImageResource(R.drawable.icon_medical_case);
				break;
			case 17:
				viewHolder.imageCatagory
						.setImageResource(R.drawable.icon_misc_box);
				break;
			case 18:
				viewHolder.imageCatagory
						.setImageResource(R.drawable.icon_music);
				break;
			default:
				viewHolder.imageCatagory
						.setImageResource(R.drawable.icon_address_book);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return convertView;
	}

	/**
	 * them item
	 * 
	 * @author: truonglt2
	 * @param item
	 * @return: void
	 * @throws:
	 */
	public void addItem(Catagory item) {
		arr.add(item);
	}

	/**
	 * xoa toan bo du lieu
	 * 
	 * @author: truonglt2
	 * @return: void
	 * @throws:
	 */
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

	/**
	 * get item cua adapter
	 * 
	 * @author: truonglt2
	 * @param position
	 * @return
	 * @return: CatagoryListAdapter
	 * @throws:
	 */
	@Override
	public Object getItem(int position) {
		return (Catagory) arr.get(position);
	}

	/**
	 * @author:truonglt2
	 * @since:Feb 7, 2014 3:10:10 PM
	 * @Description: lop holder template
	 */
	class MoredealsViewHolder {
		public TextView nameCatagory;
		public ImageView imageCatagory;
	}
}