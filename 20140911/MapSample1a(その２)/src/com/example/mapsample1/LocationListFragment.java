package com.example.mapsample1;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;

public class LocationListFragment extends ListFragment {

	public LocationListFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		// 位置情報の作成
		ArrayAdapter<Location> list = new ArrayAdapter<Location>(getActivity(),
				android.R.layout.simple_list_item_1);

		list.add(
				new Location("富士山", new LatLng(35.3605555, 138.72777769999993)));
		list.add(
				new Location("横浜駅", new LatLng(35.466188, 139.62271499999997)));
		list.add(
				new Location("日本Google株 式会社", new LatLng(35.6604005, 139.7290428)));
		list.add(
				new Location("Google Inc",new LatLng(37.423156, -122.084917)));
		// リストの登録
		setListAdapter(list);

	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// 画面遷移処理
		ArrayAdapter<Location> list = (ArrayAdapter<Location>) getListAdapter();
		Location item = list.getItem(position);
		// 引数の用意
		Bundle args = new Bundle();
		args.putParcelable("latlng", item.getLocation());
		args.putString("place", item.getName());
		if (getActivity().findViewById(R.id.container) != null) {
			// タブレットレイアウト:フラグメントを置換
			FragmentManager manager = getFragmentManager();
			FragmentTransaction transaction = manager.beginTransaction();
			LocationMapFragment3 fragment = new LocationMapFragment3();
			// Fragmentを設定
			fragment.setArguments(args);
			transaction.replace(R.id.container, fragment);
			transaction.addToBackStack(null);
			transaction.commit();
		} else {
			// スマートフォンレイアウト：アクティビティを表示
			Intent intent = new Intent(getActivity(), MapActivity.class);
			intent.putExtras(args);
			startActivity(intent);
		}
	}

}
