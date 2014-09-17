package com.example.mapsample;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class LocationMapFragment extends Fragment {

	public LocationMapFragment() {
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.v("TEST", "onAttach called");
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v("TEST", "onCreate called");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		Log.v("TEST", "onCreateView called");

		TextView view = new TextView(getActivity());
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.v("TEST", "onActivityCreated called");
	}

	@Override
	public void onStart() {
		super.onStart();
		Log.v("TEST", "onStart called");
	}
	@Override
	public void onResume() {
		super.onResume();
		Log.v("TEST", "onResume called");
	}
	@Override
	public void onPause() {
		super.onPause();
		Log.v("TEST", "onPause called");
	}
	@Override
	public void onStop() {
		super.onStop();
		Log.v("TEST", "onStop called");
	}
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.v("TEST", "onDestroyView called");
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.v("TEST", "onDestroy called");
	}
	@Override
	public void onDetach() {
		super.onDetach();
		Log.v("TEST", "onDetach called");
	}

}
