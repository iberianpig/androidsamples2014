package com.example.firstapp;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {
	// タイマーにより実行されるタスク
	public final class MyTimerTask extends TimerTask {
		@Override
		public void run() {
			//Log.v("TEST", "MyTimerTask#run");
			runOnUiThread(moveCircle);
		}
	}

	private Runnable moveCircle = new Runnable() {
		@Override
		public void run() {
			//Log.v("TEST", "moveCircle#run");
			CircleView circleView =
				(CircleView)findViewById(R.id.circleView1);
			if(mMoving) {
				circleView.setX(circleView.getX() + 1);
				circleView.setY(circleView.getY() + 1);
			}
		}
	};

	private OnClickListener click = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Log.v("TEST", "click#OnClcikListener");
			mMoving = false;
		}
	};

	// タイマーの呼び出し間隔
	private static final long TIMER_PERIOD = 50;
	// タイマーオブジェクト
	private Timer mTimer;
	// Ｖｉｅｗ移動　Ｏｎ／Ｏｆｆのフラグ
	private boolean mMoving;	// true : 移動可  false:不可

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.circleView1).setOnClickListener(click);
		mMoving = true;		// CircleView 移動可
	}

	@Override
	protected void onResume() {
		super.onResume();
		// タイマー開始
		mTimer = new Timer(true);
		mTimer.schedule(new MyTimerTask(), TIMER_PERIOD, TIMER_PERIOD);
	}

	@Override
	protected void onPause() {
		super.onPause();
		mTimer.cancel();
	}












}
