package com.example.filereadwritesample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class FileReadWriteActivity extends Activity {

	private EditText edit;
	private TextView text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_read_write);

		edit = (EditText) findViewById(R.id.edit_data);
		text = (TextView) findViewById(R.id.text_data);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.file_read_write, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_save) {
			saveInternalFile();
		} else if (id == R.id.action_load) {
			loadInternalFile();
		} else if (id == R.id.action_sdsave) {
			saveExternalFile();
		} else if (id == R.id.action_sdload) {
			loadExternalFile();
		}
		return true;
	}

	private void saveInternalFile() {
		OutputStream out;
		try {
			out = openFileOutput("log.txt", MODE_PRIVATE);
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(out, "UTF-8"));
			String s = edit.getText().toString();
			writer.append(s);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadInternalFile() {
		InputStream in;
	    String lineBuffer;
        StringBuffer buffer = new StringBuffer();
	    try {
	        in = openFileInput("log.txt");
	        BufferedReader reader= new BufferedReader(new InputStreamReader(in,"UTF-8"));
	        while( (lineBuffer = reader.readLine()) != null ){
	        	buffer.append(lineBuffer);
	        	buffer.append("\n");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    text.setText(buffer);
	}

	private void saveExternalFile() {
		String filePath = Environment.getExternalStorageDirectory() + "/memo.txt";
        File file = new File(filePath);
        file.getParentFile().mkdir();
        Log.v("TEST", "PATH: " + filePath);
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file, false);	// false： 新規（追加モードOFF）
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
            String str = edit.getText().toString();
            bw.write(str);
            bw.flush();
            bw.close();
        } catch (Exception e) {
        }
	}

	private void loadExternalFile() {
		String filePath = Environment.getExternalStorageDirectory() + "/memo.txt";
        File file = new File(filePath);
        StringBuffer buffer = new StringBuffer();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while((line = br.readLine()) != null) {
            	buffer.append(line);
            	buffer.append("\n");
            }
        } catch (Exception e) {
        }
        text.setText(buffer.toString());
	}
}
