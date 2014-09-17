package com.example.sensorlistsample;

import java.util.List;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

	private SensorManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		manager = (SensorManager)getSystemService(SENSOR_SERVICE);
	}

	@Override
	protected void onResume() {
		super.onResume();

		List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ALL);
		StringBuffer list = new StringBuffer("実装されているセンサー一覧\n");

		for(Sensor s : sensors) {
			list.append("\t" + s.getName() + "\n");
		}

		TextView text = (TextView)findViewById(R.id.text_sensor_list);
		text.setText(list.toString());
	}

}
