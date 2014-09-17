package com.example.broadcastalarmmanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.v("TEST", "BootBroadcastReceiver#onReceive");

		Intent i = new Intent(context, MyIntentService.class);

		PendingIntent pendingIntent
			= PendingIntent.getService(
					context,
					-1,
					i,
					PendingIntent.FLAG_UPDATE_CURRENT);

	      AlarmManager alarmManager
	      	= (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

	      alarmManager.setInexactRepeating(
	    		AlarmManager.RTC,
	    		System.currentTimeMillis(),
	    		10000,
	    		pendingIntent);
	}
}
