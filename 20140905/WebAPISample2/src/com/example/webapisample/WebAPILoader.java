package com.example.webapisample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.AsyncTaskLoader;
import android.content.Context;

public class WebAPILoader extends AsyncTaskLoader<JSONObject> {
	private int mCityId;
	private final static String API_URL =
			"http://weather.livedoor.com/forecast/webservice/json/v1?city=%1$d";

	public WebAPILoader(Context context, int cityId) {
		super(context);
		this.mCityId = cityId;
	}

	@Override
	protected void onStartLoading() {
		forceLoad();
		super.onStartLoading();
	}

	@Override
	public JSONObject loadInBackground() {
		URL url ;
		JSONObject result = null;
		try {
			String s = String.format(API_URL, mCityId);
			url = new URL(s);
			HttpURLConnection connection =
					(HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();

			BufferedReader reader = new BufferedReader(
				new InputStreamReader(connection.getInputStream()));
			String line;
			StringBuilder lines = new StringBuilder();
			while((line = reader.readLine()) != null) {
				lines.append(line);
			}
			String json = lines.toString();
			result = new JSONObject(json);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}
}
