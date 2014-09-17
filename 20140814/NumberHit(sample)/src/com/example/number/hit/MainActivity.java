package com.example.number.hit;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
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
	public final static String KEY_RESULT = "KEY_RESULT";
	private final int REQCODE = 100;

	private TextView result;
	private ImageView image;
	private int win  = 0;
	private int loss = 0;
	private int[] results = new int[10];
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
			results[droid]++;
			result.setText(title);
			result.setTextColor(color);
			image.setImageResource(imgID);
			edit.setText("");
		}
	};
}
