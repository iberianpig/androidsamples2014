package com.example.gestureanim;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class GestureAnimFrame extends FrameLayout {

	private GestureDetector gesture;

	public GestureAnimFrame(Context context) {
		super(context);
		init(context);
	}
	public GestureAnimFrame(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	public GestureAnimFrame(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}
	private void init(Context context) {
		gesture = new GestureDetector(context, listener);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		//return super.onTouchEvent(event);
		return gesture.onTouchEvent(event);
	}

	private SimpleOnGestureListener listener
		= new SimpleOnGestureListener() {
			@Override
			public boolean onDoubleTap(MotionEvent e) {
				return super.onDoubleTap(e);
			}
			@Override
			public boolean onDown(MotionEvent e) {
				//return super.onDown(e);
				return true;	// 以後のイベントを通知するようにtrueを戻す
			}
			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2,
					float velocityX, float velocityY) {
				return super.onFling(e1, e2, velocityX, velocityY);
			}
			@Override
			public void onLongPress(MotionEvent e) {
				//super.onLongPress(e);
				ImageView image = (ImageView)getChildAt(0);
				ObjectAnimator objectAnimator
					= ObjectAnimator.ofFloat(image, "alpha", 0f, 1f );
			    // 3秒かけて実行させます
			    objectAnimator.setDuration(5000);
			    // アニメーションを開始します
				objectAnimator.start();
			}
			@Override
			public boolean onSingleTapConfirmed(MotionEvent e) {
				return super.onSingleTapConfirmed(e);
			}
	};
}
