package com.example.circlechange;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class CircleChangeActivity extends Activity {

	private MyCircleView circleView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_circle_change);

		circleView = (MyCircleView)findViewById(R.id.my_circle_view);

		findViewById(R.id.button_minus).setOnClickListener(minus);
		findViewById(R.id.button_plus).setOnClickListener(plus);
	}

	private OnClickListener minus = new OnClickListener() {
		@Override
		public void onClick(View v) {
			circleView.minusRadius();
		}
	};

	private OnClickListener plus = new OnClickListener() {
		@Override
		public void onClick(View v) {
			circleView.plusRadius();
		}
	};

}
