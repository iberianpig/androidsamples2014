package com.example.listitemcustomsample;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<SampleData> {

	private LayoutInflater mInflater;

	public CustomAdapter(Context context, List<SampleData> objects) {
		super(context, 0, objects);
		mInflater = (LayoutInflater)context.getSystemService(
									Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//return super.getView(position, convertView, parent);
		ViewHolder holder;
		if(convertView == null) {
			convertView = mInflater.inflate(R.layout.sample_row, parent, false);
			holder = new ViewHolder();
			holder.imageView = (ImageView)convertView.findViewById(R.id.imageView);
			holder.textView = (TextView)convertView.findViewById(R.id.textView);
			holder.textRow = (TextView)convertView.findViewById(R.id.text_row);
			convertView.setTag(holder);
		} else  {
			holder = (ViewHolder)convertView.getTag();
		}
		SampleData data = getItem(position);

		holder.imageView.setImageResource(data.resourceId);
		if(position % 2 == 0) {
			holder.textView.setTextColor(0xff00ff00);
		} else {
			holder.textView.setTextColor(0xffff0000);
		}
		holder.textView.setText(data.text);
		holder.textRow.setText(data.row);

		return convertView;
	}

	class ViewHolder {
		ImageView imageView;
		TextView textView;
		TextView textRow;
	}

}
