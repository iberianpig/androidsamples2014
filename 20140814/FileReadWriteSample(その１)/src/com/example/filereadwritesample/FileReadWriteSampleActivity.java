package com.example.filereadwritesample;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class FileReadWriteSampleActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_read_write_sample);

		findViewById(R.id.button_save).setOnClickListener(buttonSave);

	}

	private OnClickListener buttonSave = new OnClickListener() {
		@Override
		public void onClick(View v) {
			OutputStream out;
			try {
				out = openFileOutput("log.txt", MODE_PRIVATE);
				PrintWriter writer = new PrintWriter(
					new OutputStreamWriter(out, "UTF-8"));
				EditText edit = (EditText)findViewById(R.id.edit_data);
				writer.append(edit.getText().toString());
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	};
}
