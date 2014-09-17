package com.example.jsonsample;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
			String json_text = "{" +
					"\"nodeA\" : \"nodeA-data\"," +
					"\"nodeB\" : \"nodeB-data\"" +
					"}";
			try {
				JSONObject json_data = new JSONObject(json_text);
				String result = json_data.getString("nodeA");
				text.setText(result);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	};

	private OnClickListener level = new OnClickListener() {
		@Override
		public void onClick(View v) {
			String json_text = "{" +
					"'nodeA' : 'nodeA-data'," +
					"'nodeB' : 'nodeB-data'," +
					"'nodeC' : " +
						"{" +
							"'nodeC-A' : 'nodeC-A-data'," +
							"'nodeC-B' : 'nodeC-B-data'" +
						"}" +
					"}";
			try {
				JSONObject json_data = new JSONObject(json_text);
				JSONObject inner = json_data.getJSONObject("nodeC");
				String result = inner.getString("nodeC-A");
				text.setText(result);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	};

	private OnClickListener array = new OnClickListener() {
		@Override
		public void onClick(View v) {
			String json_text = "{" +
						"'nodeA' : 'nodeA-data'," +
						"'nodeB' : 'nodeB-data'," +
						"'nodeC' : ['A', 'B', 'C']" +
					"}";
			try {
				JSONObject json_data = new JSONObject(json_text);
				JSONArray inner = json_data.getJSONArray("nodeC");
				StringBuffer string = new StringBuffer();
				for(int i = 0; i < inner.length(); i++) {
					string.append(inner.getString(i));
				}
				text.setText(string.toString());
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	};





}
