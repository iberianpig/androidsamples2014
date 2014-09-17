package com.example.servicesample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class ServiceSampleActivity extends Activity {

	private class ActivityInfo {
		protected int id;
		protected Class<?> cls;
		public ActivityInfo(int id, Class<?> cls) {
			this.id = id;
			this.cls = cls;
		}
	}

	private ActivityInfo[] activites = {
		new ActivityInfo(R.id.button_simple_service, SimpleServiceSample.class)
	};


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service_sample);

		for(int i = 0; i < activites.length; i++) {
			findViewById(activites[i].id).setOnClickListener(buttonClick);
		}
	}

	private OnClickListener buttonClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			for(ActivityInfo a : activites) {
				if(a.id == v.getId()) {
					Intent intent
						= new Intent(ServiceSampleActivity.this, a.cls);
					startActivity(intent);
					return;
				}
			}
		}
	};

}
