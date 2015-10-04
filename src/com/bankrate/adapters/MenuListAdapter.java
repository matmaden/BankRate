package com.bankrate.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bankrate.R;
import com.bankrate.dto.MenuContent;

/**
 * @Description: adapter menu
 * @author:truonglt2
 * @since:Feb 7, 2014 3:51:10 PM
 * @version: 1.0
 * @since: 1.0
 * 
 */
public class MenuListAdapter extends BaseAdapter {

	ArrayList<MenuContent> arr = new ArrayList<MenuContent>();
	LayoutInflater inflater = null;

	/**
	 * @param context
	 */
	public MenuListAdapter(Context context) {
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	/**
	 * lay vi tri menuContent
	 * 
	 * @author: truonglt2
	 * @param pos
	 * @return
	 * @return: MenuContent
	 * @throws:
	 */
	public MenuContent get(int pos) {
		return arr.get(pos);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MenuViewHolder viewHolder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_menu_content, null);
			viewHolder = new MenuViewHolder();
			viewHolder.nameCatagory = (TextView) convertView
					.findViewById(R.id.tvtTitleMenu);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (MenuViewHolder) convertView.getTag();
		}
		viewHolder.nameCatagory.setText(arr.get(position).getTitle());
		return convertView;
	}

	/**
	 * them item vao vi tri
	 * 
	 * @author: truonglt2
	 * @param item
	 * @return: void
	 * @throws:
	 */
	public void addItem(MenuContent item) {
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
		return (MenuContent) arr.get(position);
	}

	/**
	 * @author:truonglt2
	 * @since:Feb 7, 2014 3:12:00 PM
	 * @Description: lop temp
	 */
	class MenuViewHolder {
		public TextView nameCatagory;
	}
}