package com.example.paintmethodsample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
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
		if(methodType == R.id.action_set_style) {
			setStyleSample(canvas);
		}
	}

	private void setStyleSample(Canvas canvas) {
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		paint.setStyle(Paint.Style.STROKE);
		RectF rect = new RectF();
		for(int i = 1; i <= 10; i++) {
			// setメソッド： 位置を再設定しながら描画
			rect.set(i * 22.5f, i * 22.5f, 100 + i * 22.5f, 100 + i * 22.5f);
			canvas.drawRect(rect, paint);
		}
	}

	public void setMethodType(int type) {
		methodType = type;
		invalidate();
	}
}
