package com.example.servicesample;

import android.app.IntentService;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyBindService extends IntentService {

	private IBinder binder = new MyBinder();
	private String message;

	public class MyBinder extends Binder {
		MyBindService getService() {
			Log.v("TEST", "MyBinder#getService");
			return MyBindService.this;
		}
	}

	public MyBindService() {
		super("MyBindService");
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.v("TEST", "MyBinder#onCreate");
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.v("TEST", "MyBinder#onBind");
		return binder;
	}
	@Override
	public boolean onUnbind(Intent intent) {
		Log.v("TEST", "MyBinder#onUnbind");
		return super.onUnbind(intent);
	}
	@Override
	protected void onHandleIntent(Intent arg0) {
		Log.v("TEST", "MyBinder#onHandleIntent");
		Log.v("TEST", "  message = [" + message + "]");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.v("TEST", "MyBinder#onDestroy");
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
