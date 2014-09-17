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

		if(convertView == null) {
			convertView = mInflater.inflate(R.layout.sample_row, parent, false);
		} else  {

		}
		SampleData data = getItem(position);

		ImageView image = (ImageView)convertView.findViewById(R.id.imageView);
		image.setImageResource(data.resourceId);

		TextView text = (TextView)convertView.findViewById(R.id.textView);
		text.setText(data.text);

		return convertView;
	}


}
