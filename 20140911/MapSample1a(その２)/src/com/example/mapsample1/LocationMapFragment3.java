package com.example.mapsample1;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class LocationMapFragment3 extends Fragment {

	public LocationMapFragment3() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		//return super.onCreateView(inflater, container, savedInstanceState);
		super.onCreateView(inflater, container, savedInstanceState);
		// テキストボックスを生成する
		TextView view = new TextView(getActivity());
		// パラメータから文字列を読み込む
		Bundle param = getArguments();
		if (param != null) {
			// テキストビューに文字列を設定する
			String name = param.getString("place");
			view.setText(name);
		}
		return view; // 戻り値として設定したビューが、画面に表示される
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onActivityCreated(savedInstanceState);
		Log.v("TEST", "onActivityCreated");
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO 自動生成されたメソッド・スタブ
		super.onAttach(activity);
		Log.v("TEST", "onAttach");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		Log.v("TEST", "onCreate");
	}

	@Override
	public void onDestroy() {
		// TODO 自動生成されたメソッド・スタブ
		super.onDestroy();
		Log.v("TEST", "onDestroy");
	}

	@Override
	public void onDestroyView() {
		// TODO 自動生成されたメソッド・スタブ
		super.onDestroyView();
		Log.v("TEST", "onDestroyView");
	}

	@Override
	public void onDetach() {
		// TODO 自動生成されたメソッド・スタブ
		super.onDetach();
		Log.v("TEST", "onDetach()");
	}

	@Override
	public void onPause() {
		// TODO 自動生成されたメソッド・スタブ
		super.onPause();
		Log.v("TEST", "onPause");
	}

	@Override
	public void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();
		Log.v("TEST", "onResume");
	}

	@Override
	public void onStart() {
		// TODO 自動生成されたメソッド・スタブ
		super.onStart();
		Log.v("TEST", "onStart");
	}

	@Override
	public void onStop() {
		// TODO 自動生成されたメソッド・スタブ
		super.onStop();
		Log.v("TEST", "onStop");
	}

}
