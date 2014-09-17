package com.example.sharedpreferencessample;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPreferencesSampleActivity extends Activity
	implements OnClickListener{
	private static final String PREF_NAME = "PrefName";
	private static final String KEY_TEXT = "text";
	private static final String KEY_INT = "int_data";

	private SharedPreferences pref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shared_preferences_sample);

		pref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

		findViewById(R.id.button_save).setOnClickListener(this);

		TextView text = (TextView)findViewById(R.id.text_data);
		String s = pref.getString(KEY_TEXT, "NO_DATA");
		text.setText(s);

		text = (TextView)findViewById(R.id.text_data1);
		int i = pref.getInt(KEY_INT, -1);
		text.setText(String.valueOf(i));
	}

	@Override
	public void onClick(View v) {
		// 入力された文字列を取得
		EditText edit = (EditText)findViewById(R.id.edit_input);
		String s = edit.getText().toString();
		edit = (EditText)findViewById(R.id.edit_int);
		int i = Integer.parseInt(edit.getText().toString());
		// プリファレンスに保存
		Editor editor = pref.edit();
		editor.putString(KEY_TEXT, s);	// KEY, VALUEの組み合わせで保存
		editor.putInt(KEY_INT, i);
		editor.commit();
	}

}
