package com.example.surfaceviewsample2;

import android.app.Activity;
import android.os.Bundle;

public class SurfaceViewSample2Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new AnimationSurfaceView(this));
	}

}
