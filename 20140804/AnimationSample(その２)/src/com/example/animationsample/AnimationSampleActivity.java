package com.example.animationsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class AnimationSampleActivity extends Activity {

	private ImageView image;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animation_sample);

		image = (ImageView)findViewById(R.id.image);

		findViewById(R.id.button_scale).setOnClickListener(scaleButton);
		findViewById(R.id.button_rotate).setOnClickListener(rotateButton);

	}

	private OnClickListener scaleButton = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Animation anim = AnimationUtils.loadAnimation(
					AnimationSampleActivity.this, R.anim.scale);
			image.startAnimation(anim);
		}
	};

	private OnClickListener rotateButton = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Animation anim = AnimationUtils.loadAnimation(
					AnimationSampleActivity.this, R.anim.rotate);
			image.startAnimation(anim);
		}
	};
}
