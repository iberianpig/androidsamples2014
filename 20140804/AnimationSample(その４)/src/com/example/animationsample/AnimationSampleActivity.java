package com.example.animationsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class AnimationSampleActivity extends Activity {

	private ImageView image;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animation_sample);

		image = (ImageView)findViewById(R.id.image);

		findViewById(R.id.button_scale).setOnClickListener(scaleButton);
		findViewById(R.id.button_rotate).setOnClickListener(rotateButton);
		findViewById(R.id.button_translate).setOnClickListener(translateButton);
		findViewById(R.id.button_alpha).setOnClickListener(alphaButton);
		findViewById(R.id.button_set).setOnClickListener(setButton);

	}

	private OnClickListener scaleButton = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Animation anim = AnimationUtils.loadAnimation(
					AnimationSampleActivity.this, R.anim.scale);
//			anim.setAnimationListener(listener);
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

	private OnClickListener translateButton = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Animation anim = AnimationUtils.loadAnimation(
					AnimationSampleActivity.this, R.anim.translate);
			image.startAnimation(anim);
		}
	};

	private OnClickListener alphaButton = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Animation anim = AnimationUtils.loadAnimation(
					AnimationSampleActivity.this, R.anim.alpha);
			anim.setAnimationListener(listener);
			image.startAnimation(anim);
		}
	};

	private OnClickListener setButton = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Animation anim = AnimationUtils.loadAnimation(
					AnimationSampleActivity.this, R.anim.animation_set);
			image.startAnimation(anim);
		}
	};

	private AnimationListener listener = new AnimationListener() {

		@Override
		public void onAnimationStart(Animation animation) {
			Toast.makeText(AnimationSampleActivity.this,
					"AnimationStart", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
			Toast.makeText(AnimationSampleActivity.this,
					"onAnimationRepeat", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onAnimationEnd(Animation animation) {
			Toast.makeText(AnimationSampleActivity.this,
					"onAnimationEnd", Toast.LENGTH_SHORT).show();
			Animation anim = AnimationUtils.loadAnimation(
					AnimationSampleActivity.this, R.anim.alpha_2);
			image.startAnimation(anim);
		}
	};


}
