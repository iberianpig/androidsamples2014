package com.example.number.hit;

import java.util.Random;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	public final static String KEY_WIN  = "KEY_WIN";
	public final static String KEY_LOSS = "KEY_LOSS";

	private TextView result;
	private ImageView image;
	private int win  = 0;
	private int loss = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		result = (TextView)findViewById(R.id.text_result);
		result.setText("");
		image = (ImageView)findViewById(R.id.image_result);
		image.setImageAlpha(0x88);

		Button button = (Button)findViewById(R.id.button_judgment);
		button.setOnClickListener(judgement);
	}


	@Override
	protected void onResume() {
		super.onResume();
		SharedPreferences pref
			= PreferenceManager.getDefaultSharedPreferences(this);
		win = pref.getInt(KEY_WIN, 0);
		loss = pref.getInt(KEY_LOSS, 0);
		dispWinLoss();
	}

	@Override
	protected void onPause() {
		super.onPause();
		SharedPreferences pref
			= PreferenceManager.getDefaultSharedPreferences(this);
		Editor editor = pref.edit();
		editor.putInt(KEY_WIN, win);
		editor.putInt(KEY_LOSS, loss);
		editor.commit();
	}

	private OnClickListener judgement = new OnClickListener() {
		@Override
		public void onClick(View v) {
			int droid = new Random().nextInt(10);
			result.setText("");
			EditText edit = (EditText)findViewById(R.id.edit_number);
			String input = edit.getText().toString();
			String error = null;
			int man = 0;
			if(input.length() == 0) {
				error = "数字を入力してください";
			}
			else {
				man = Integer.parseInt(input);
				if(man < 0 || 9 < man) {
					error = "０から９までの数字を入力してください";
				}
			}
			if(error != null) {
				Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
				return;
			}
			String title = null;
			int color;
			int imgID;
			if(man == droid) {
				title = "大当たり：　" + man;
				color = 0xffff0000;
				imgID = R.drawable.good;
				win++;
			}
			else {
				title = "はずれ：　" + droid;
				color = 0xff0000ff;
				imgID = R.drawable.bad;
				loss++;
			}
			dispWinLoss();
			result.setText(title);
			result.setTextColor(color);
			image.setImageResource(imgID);
			edit.setText("");
		}
	};

	private void dispWinLoss() {
		String s = "当たり：　" + win + "  はずれ：" + loss;
		TextView text = (TextView)findViewById(R.id.text_winloss);
		text.setText(s);
	}
}
