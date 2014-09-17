package com.example.boradcastsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class BroadcstSample extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_broadcst_sample);
		findViewById(R.id.btn).setOnClickListener(clickButton);
	}

	private OnClickListener clickButton = new OnClickListener() {
		@Override
		public void onClick(View v) {
			//Intent intent = new Intent("broadcast_test");
			Intent intent = new Intent();
			intent.setAction("broadcast_test");
			intent.putExtra("TEST", "ブロードキャストレシーバのテスト");
			sendBroadcast(intent);
		}
	};
}
