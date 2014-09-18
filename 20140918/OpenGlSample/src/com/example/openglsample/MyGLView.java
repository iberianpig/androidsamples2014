package com.example.openglsample;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class MyGLView extends GLSurfaceView {

	private MyRendere myRenderer;

	public MyGLView(Context context) {
		super(context);
		myRenderer = new MyRendere();
		setRenderer(myRenderer);
	}

}
