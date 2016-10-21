package com.gree.hwb.customizeview.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gree.hwb.customizeview.R;

import java.util.List;

/**
 * Created by Administrator on 2016/10/12.
 */

public class MyAdapter extends ArrayAdapter<String>
{
	public MyAdapter(Context context, int textViewResourceId, List<String> objects)
	{
		super(context, textViewResourceId, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View view;
		if(convertView == null)
		{
			view = LayoutInflater.from(getContext()).inflate(R.layout.my_list_view_item, null);
		}
		else
		{
			view = convertView;
		}
		TextView textView = (TextView) view.findViewById(R.id.text_view);
		textView.setText(getItem(position));
		return view;
	}
}
