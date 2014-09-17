package com.example.contentprovidersample;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ContentResolver cr = getContentResolver();

		//
		Cursor cursor = cr.query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,
				null, null);
		cursor.moveToFirst();
		int fieldIndex = cursor
				.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID);
		Log.v("TEST", ContactsContract.CommonDataKinds.Phone._ID);

		Long id = cursor.getLong(fieldIndex);
		Log.v("TEST", "id : " + id);
		cursor.close();

		Uri person = ContentUris.withAppendedId(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI, id);
		cursor = cr.query(person, null, null, null, null);
		cursor.moveToFirst();
		fieldIndex = cursor
				.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
		String name = cursor.getString(fieldIndex);
		cursor.close();

		TextView text = (TextView) findViewById(R.id.text_name);
		text.setText(name);

	}

}
