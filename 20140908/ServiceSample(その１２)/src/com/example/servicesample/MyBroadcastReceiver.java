package com.example.servicesample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class MyBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		String message = bundle.getString("message");

		//Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

		ServiceBroadcastSample activity
					= (ServiceBroadcastSample) context;
		activity.setMessage(message);
	}

}
