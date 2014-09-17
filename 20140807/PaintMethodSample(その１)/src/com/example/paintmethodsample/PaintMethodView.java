package com.example.paintmethodsample;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class PaintMethodView extends View {
	private int methodType = 0;

	public PaintMethodView(Context context) {
		super(context);
	}

	public PaintMethodView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public PaintMethodView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void onDraw(Canvas canvas) {
	}

	public void setMethodType(int type) {
		methodType = type;
		invalidate();
	}
}
