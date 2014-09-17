package com.example.multiactivitysample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EditorActivity extends Activity {
	private long editorId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editor);

		// イベントハンドラの設定
		Button operate_save = (Button) findViewById(R.id.operate_save);
		operate_save.setOnClickListener(saveButton);

		// Extraが指定された場合、その設定を反映する
		Intent intent = getIntent();
		if (intent.hasExtra(MainActivity.EXTRA_ID)) {
			EditText editor_title = (EditText) findViewById(R.id.editor_title);
			EditText editor_body = (EditText) findViewById(R.id.editor_body);

			editorId = intent.getLongExtra(MainActivity.EXTRA_ID, 0);

			editor_title.setText(intent
					.getStringExtra(MainActivity.EXTRA_TITLE));
			editor_body.setText(intent.getStringExtra(MainActivity.EXTRA_BODY));
		} else {
			editorId = -1;
		}
	}

	private OnClickListener saveButton = new OnClickListener() {
		@Override
		public void onClick(View v) {
			EditText editor_title = (EditText) findViewById(R.id.editor_title);
			EditText editor_body = (EditText) findViewById(R.id.editor_body);

			Intent result = new Intent();
			if (editorId != -1) {
				result.putExtra(MainActivity.EXTRA_ID, editorId);
			}
			result.putExtra(MainActivity.EXTRA_TITLE, editor_title.getText()
					.toString());
			result.putExtra(MainActivity.EXTRA_BODY, editor_body.getText()
					.toString());
			setResult(RESULT_OK, result);
			finish();
		}
	};
}
