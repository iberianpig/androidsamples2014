package com.example.surfaceviewsample2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class AnimationSurfaceView extends SurfaceView
	implements Runnable, SurfaceHolder.Callback {

	private static final long FPS = 30;
	private static final long FRAME_TIME = 1000 / FPS;
	private static final int BALL_R = 10;

	private SurfaceHolder surfaceHolder;
	private Thread thread;
	private int screenWidth;
	private int screenHeight;
	private int cx = BALL_R;
	private int cy = BALL_R;
	private int vx = 1;
	private int vy = 2;

	public AnimationSurfaceView(Context context) {
		super(context);

		surfaceHolder = getHolder();
		surfaceHolder.addCallback(this);
	}

	// ---------------------------------------
	// SurfaceHolder.Callbackインターフェース
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		screenWidth = width;
		screenHeight = height;
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		thread = null;
	}
	// ---------------------------------------

	// ---------------------------------------
	// Runnableインタ－フェース
	@Override
	public void run() {

		Canvas canvas = null;
		Paint paint = new Paint();
		Paint bgPaint = new Paint();

		// background
		bgPaint.setStyle(Style.FILL);
		bgPaint.setColor(Color.WHITE);

		// Ball
		paint.setStyle(Style.FILL);
		paint.setAntiAlias(true);
		paint.setColor(Color.BLUE);

		long loopCount = 0;
		long waitTime = 0;
		long startTime = System.currentTimeMillis();

		// ここで描画のループ
		while(thread != null) {
			try {
				loopCount++;
				canvas = surfaceHolder.lockCanvas();
				if(canvas != null) {
					canvas.drawRect(0, 0, screenWidth, screenHeight, bgPaint);
					canvas.drawCircle(cx, cy, BALL_R, paint);
					surfaceHolder.unlockCanvasAndPost(canvas);
					// 次の位置を計算
					cx += vx;
					cy += vy;
					if(cx < BALL_R || (cx > screenWidth - BALL_R)) {
						vx = -vx;
					}
					if(cy < BALL_R || (cy > screenHeight - BALL_R)) {
						vy = -vy;
					}
				}

				// 待ち時間の計算
				waitTime = (loopCount * FRAME_TIME)
				        - (System.currentTimeMillis() - startTime);
				if( waitTime > 0 ) {
				      Thread.sleep(waitTime);
				}
			} catch (InterruptedException e) {
			}
		}
	}
	// ---------------------------------------
}
