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
import android.widget.TextView;

public class MainActivity extends ListActivity
			implements LoaderCallbacks<JSONObject>{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		LoaderManager manager = getLoaderManager();
		manager.initLoader(1, null, this);

	}

	// -----------------------------------
	// LoaderCallbacks
	// -----------------------------------
	@Override
	public Loader<JSONObject> onCreateLoader(int id, Bundle args) {
		Loader<JSONObject> loader = null;
		switch(id) {
		case 1:
			loader = new WebAPILoader(this, 140010);
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




}
