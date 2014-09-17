package com.example.jsonsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class JsonMainActivity extends Activity {

	private TextView text;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_json_main);

		text = (TextView)findViewById(R.id.text_result);

		findViewById(R.id.button_simple).setOnClickListener(simple);
		findViewById(R.id.button_level).setOnClickListener(level);
		findViewById(R.id.button_array).setOnClickListener(array);
	}

	private OnClickListener simple = new OnClickListener() {
		@Override
		public void onClick(View v) {
		}
	};

	private OnClickListener level = new OnClickListener() {
		@Override
		public void onClick(View v) {
		}
	};

	private OnClickListener array = new OnClickListener() {
		@Override
		public void onClick(View v) {
		}
	};





}
