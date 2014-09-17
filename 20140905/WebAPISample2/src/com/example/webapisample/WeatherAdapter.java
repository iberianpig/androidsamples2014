package com.example.webapisample;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class WeatherAdapter extends BaseAdapter {

	private LayoutInflater mInflate;
	private ArrayList<Weather> mItems;
	private Context context;

	public WeatherAdapter(Context context, ArrayList<Weather> items) {
		super();
		this.context = context;
		this.mInflate = (LayoutInflater)context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.mItems = items;
	}

	@Override
	public int getCount() {
		return mItems.size();
	}

	@Override
	public Object getItem(int position) {
		return mItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null) {
			convertView = mInflate
				.inflate(R.layout.item_weather, parent, false);
		}

		Weather weather = mItems.get(position);

		TextView text = (TextView)convertView.findViewById(R.id.display_datelabel);
		text.setText(weather.getmDateLabel());

		text = (TextView)convertView.findViewById(R.id.display_telop);
		text.setText(weather.getmTelop());

		text = (TextView)convertView.findViewById(R.id.display_lowtempersture);
		String s = context.getString(
				R.string.display_lowtemperature, weather.getmLowTemperture());
		text.setText(s);

		text = (TextView)convertView.findViewById(R.id.display_heightempersture);
		s = context.getString(
				R.string.display_hightemperature, weather.getmHighTemperture());
		text.setText(s);
		return convertView;
	}

}
