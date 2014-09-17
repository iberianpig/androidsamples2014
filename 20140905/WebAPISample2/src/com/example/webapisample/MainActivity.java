package com.example.webapisample;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends ListActivity
			implements LoaderCallbacks<JSONObject>{

	private static final int LOADER_ID = 1;
	private static final String CITY = "CITY_ID";

	private static final int CITY_YOKOHAMA = 140010;
	private static final int CITY_OOSAKA = 270000;
	private static final int CITY_NAHA = 471010;

	private LoaderManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Bundle args = new Bundle();
		args.putInt(CITY, CITY_YOKOHAMA);

		manager = getLoaderManager();
		manager.initLoader(LOADER_ID, args, this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		int city = 0;
		if (id == R.id.action_yokohama) {
			city = CITY_YOKOHAMA;
		} else if(id == R.id.action_oosaka) {
			city = CITY_OOSAKA;
		} else if(id == R.id.action_naha) {
			city = CITY_NAHA;
		} else {
			return super.onOptionsItemSelected(item);
		}
		Bundle args = new Bundle();
		args.putInt(CITY, city);
		manager.restartLoader(LOADER_ID, args, this);

		return true;
	}

	// ------------------------------------------------------------------
	// Loader callbacks
	@Override
	public Loader<JSONObject> onCreateLoader(int id, Bundle args) {
		Loader<JSONObject> loader = null;
		switch(id) {
		case LOADER_ID:
			int city = args.getInt(CITY);
			loader = new WebAPILoader(this, city);
			break;
		default:
			break;
		}
		return loader;
	}
	@Override
	public void onLoadFinished(Loader<JSONObject> loader, JSONObject data) {
		TextView display_lastupdate = (TextView) findViewById(R.id.display_lastupdate);
		TextView display_description = (TextView) findViewById(R.id.display_description);
		TextView display_copyright_text = (TextView) findViewById(R.id.display_copyright_text);
		TextView display_copyright_url = (TextView) findViewById(R.id.display_copyright_url);

		if(data != null) {
			try {
				JSONObject description = data.getJSONObject("description");

				display_lastupdate.setText(description.getString("publicTime"));
				display_description.setText(description.getString("text"));

				JSONArray weathers_json = data.getJSONArray("forecasts");
				ArrayList<Weather> weathers = new ArrayList<Weather>();
				for (int i = 0; i < weathers_json.length(); i++) {
					JSONObject weather_json = (JSONObject) weathers_json.get(i);
					Weather weather = new Weather();
					weather.setmDateLabel(weather_json.getString("dateLabel"));
					weather.setmTelop(weather_json.getString("telop"));
					JSONObject temperature = weather_json
							.getJSONObject("temperature");
					// 最低気温を取得(観測できなかった場合は存在しないため、nullチェックを行う)
					if (!temperature.isNull("min")) {
						JSONObject temperature_min = temperature
								.getJSONObject("min");
						weather.setmLowTemperture(temperature_min
								.getString("celsius"));
					}
					// 最高気温を取得(観測できなかった場合は存在しないため、nullチェックを行う)
					if (!temperature.isNull("max")) {
						JSONObject temperature_max = temperature
								.getJSONObject("max");
						weather.setmHighTemperture(temperature_max
								.getString("celsius"));
					}
					// リストに追加
					weathers.add(weather);
				}

				// アダプタを設定
				WeatherAdapter adapter = new WeatherAdapter(this, weathers);
				setListAdapter(adapter);

				JSONObject copyright = data.getJSONObject("copyright");
				JSONObject image = copyright.getJSONObject("image");
				// コピーライト文章を反映
				display_copyright_text.setText(image.getString("title"));
				display_copyright_url.setText(image.getString("link"));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else  {
			display_lastupdate.setText(R.string.display_loadfailed);
			display_description.setText(R.string.display_loadfailed);
			setListAdapter(null);
			display_copyright_text.setText(R.string.display_loadfailed);
			display_copyright_url.setText(R.string.display_loadfailed);
		}
	}
	@Override
	public void onLoaderReset(Loader<JSONObject> arg0) {
	}
	// ------------------------------------------------------------------

}
