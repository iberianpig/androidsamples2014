package com.example.paintmethodsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class PaintMethodSampleActivity extends Activity {

	private PaintMethodView paintMethodView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		paintMethodView = new PaintMethodView(this);
		setContentView(R.layout.activity_paint_method_sample);
		paintMethodView = (PaintMethodView)findViewById(R.id.paint_method_view);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.paint_method_sample, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		paintMethodView.setPaintMethod(id);
		return true;
	}
}
