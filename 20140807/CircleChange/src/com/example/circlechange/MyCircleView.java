package com.example.circlechange;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MyCircleView extends View {

	private static final float MIN_RADIUS = 30.0f;
	private static final float CHANGE_VALUE = 10.0f;

	private Paint myPaint;
	private float radius;

	public MyCircleView(Context context) {
		super(context);
		init();
	}

	public MyCircleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public MyCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		myPaint = new Paint();
		myPaint.setAntiAlias(true);
		myPaint.setColor(Color.CYAN);
		radius = MIN_RADIUS;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		int x = getWidth();
		int y = getHeight();
		canvas.drawCircle(x / 2, y / 2, radius, myPaint);
	}

	public void minusRadius() {
		if((radius - CHANGE_VALUE) < MIN_RADIUS) {
			;
		} else {
			radius -= CHANGE_VALUE;
			invalidate();
		}
	}

	public void plusRadius() {
		float max = Math.min(getWidth(), getHeight()) / 2;
		if((radius + CHANGE_VALUE) > max) {
			;
		} else {
			radius += CHANGE_VALUE;
			invalidate();
		}
	}

}
