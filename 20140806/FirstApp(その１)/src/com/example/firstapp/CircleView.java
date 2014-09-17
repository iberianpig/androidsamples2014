package com.example.firstapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CircleView extends View {

	private Paint mFillPen;

	public CircleView(Context context) {
		super(context);
		init();
	}

	public CircleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		mFillPen = new Paint();
		mFillPen.setAntiAlias(true);
		mFillPen.setColor(Color.BLUE);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// Viewのサイズを取得
		int w = getWidth();
		int h = getHeight();
		canvas.drawCircle(w / 2, h / 2, Math.min(w,  h) / 2, mFillPen);
	}



}
