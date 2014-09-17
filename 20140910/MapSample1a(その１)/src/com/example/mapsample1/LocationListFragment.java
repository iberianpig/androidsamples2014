package com.example.mapsample1;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class LocationListFragment extends ListFragment {

	public LocationListFragment() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		// 位置情報の作成
		ArrayAdapter<String> list = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1);
		list.add("富士山");
		list.add("横浜駅");
		list.add("日本Google株 式会社");
		list.add("Google Inc");
		// リストの登録
		setListAdapter(list);

	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// 画面遷移処理
		@SuppressWarnings("unchecked")
		ArrayAdapter<String> list = (ArrayAdapter<String>) getListAdapter();
		String item = list.getItem(position);
		// 引数の用意
		Bundle args = new Bundle();
		//args.putParcelable(EXTRA_LATLNG, item.getLocation());
		args.putString("place", item);
		if (getActivity().findViewById(R.id.container) != null) {
			// タブレットレイアウト:フラグメントを置換
			FragmentManager manager = getFragmentManager();
			FragmentTransaction transaction = manager.beginTransaction();
			LocationMapFragment fragment = new LocationMapFragment();
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
