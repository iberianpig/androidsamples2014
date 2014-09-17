package com.example.servicesample;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class AlarmServiceSample extends Activity {


	// http://kimihiro-n.appspot.com/show/343001
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.servie_alarm);

		findViewById(R.id.button_schedule).setOnClickListener(scheduleButton);
		findViewById(R.id.button_cancel).setOnClickListener(cancelButton);
	}

	private OnClickListener scheduleButton = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(
				AlarmServiceSample.this, MyAlarmService.class);

			PendingIntent pi = PendingIntent.getService(
				AlarmServiceSample.this,
				-1,
				intent,
				PendingIntent.FLAG_UPDATE_CURRENT);

			AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
			am.setInexactRepeating(
					AlarmManager.RTC,
					System.currentTimeMillis(),
					5000,
					pi);
		}
	};
	private OnClickListener cancelButton = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(
					AlarmServiceSample.this, MyAlarmService.class);

			PendingIntent pi = PendingIntent.getService(
					AlarmServiceSample.this,
					-1,
					intent,
					PendingIntent.FLAG_UPDATE_CURRENT);

			AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
			am.cancel(pi);
		}
	};
}
