package jp.heat.android.magic;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class Shake extends Activity implements SensorEventListener {
	//メンバー変数の定義

	private SensorManager sensorManager; // センサーを管理しているオブジェクトを保存

	// ３軸加速度センサーの過去のデータを記録している、アレーリスト（30個分確保しています）
	private ArrayList<Float> x= new ArrayList<Float>();
	private ArrayList<Float> y= new ArrayList<Float>();
	private ArrayList<Float> z= new ArrayList<Float>();
	private static final int ELEMENT_COUNT = 30; // アレーリストの格納最大値

	private View layout; // 画面処理用
	private BitmapDrawable y100; // １００円玉の画像を格納している

	// ｢チャリーン｣音の処理用の変数
	private SoundPool sp;
	private int  chari;

	// 画像を表示するか／しないかを決めています。
	private int  nomark=0;	//初期状態は｢表示します｣

	// メソッドの定義
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// ＯＳからセンサー管理オブジェクトを取得
		sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

		layout = (View) findViewById(R.id.layout); // リニアレイアウトの参照を取得
		y100 = (BitmapDrawable) getResources().getDrawable(R.drawable.y100); // 百円玉の画像を取得

		// ｢チャリーン｣の音をサウンドプールを用いて準備する。
		// public SoundPool (int maxStreams, int streamType, int srcQuality)
		sp = new SoundPool( 1 ,AudioManager.STREAM_MUSIC,0);
		// public int load (Context context, int resId, int priority)
		chari = sp.load( this , R.raw.chari, 1 );
	}

	// 1.センサーを有効にする。 2.リスナーを動作させる。
	@Override
	protected void onResume() {
		super.onResume();
		// public boolean registerListener (SensorEventListener listener, Sensor sensor, int rateUs)
		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_UI);
	}

	// SensorManager#unregisterListenerメソッドでリスナーの登録を解除する.
	@Override
	protected void onStop() {
		sensorManager.unregisterListener(this); //センサーを無効にする。
		super.onStop(); //リスナーを停止させる。
	}

	//センサーの精度変化に対しては対応せず。
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

	// SensorEventListener#onSensorChangedメソッド
	// 加速度センサーの場合にSensorEvent.values[]の値を読み出す.
	public void onSensorChanged(SensorEvent event) {
	//ここでデータのフィルター処理を行う
		//生データを取得
//		float sensorValueX = event.values[SensorManager.DATA_X];
//		float sensorValueY = event.values[SensorManager.DATA_Y];
//		float sensorValueZ = event.values[SensorManager.DATA_Z];
		float sensorValueX = event.values[0];
		float sensorValueY = event.values[1];
		float sensorValueZ = event.values[2];

		// 3方向の加速度をログ出力
		String sx = String.format("%.5f", sensorValueX);
		String sy = String.format("%.5f", sensorValueY);
		String sz = String.format("%.5f", sensorValueZ);
		Log.v("TAG", "sensorValueX = " + sx + "   sensorValueY = " + sy + "    sensorValueZ = " + sz);

		//生データをアレィリストに登録（追加と同時に最古のデータを削除する）
		addValue(x,sensorValueX); // 本アプリオリジナルメソッド(下部参照)
		addValue(y,sensorValueY);
		addValue(z,sensorValueZ);

		//中央値と生ﾃﾞｰﾀを比較して、その差分（急な変化分）を透明度に設定する。
		float valueX = sensorValueX - getMedian(x);
		float valueY = sensorValueY - getMedian(y);
		float valueZ = sensorValueZ - getMedian(z);

		//gazouが消えていたらもう表示しない
		if (nomark==1)return;

		//X,Y,Zの変化分を足して係数５を掛けた値を閾値とする
		y100.setAlpha( 255 ); // 透過処理しない
		layout.setBackground(y100);
		//layout.setBackgroundDrawable(y100); // 100円玉の表示
		if( 96 < (5 * (int)(Math.abs(valueX) + Math.abs(valueY) + Math.abs(valueZ) ) ) ){
			// public final int play (int soundID, float leftVolume, float rightVolume, int priority, int loop, float rate)
			// sooundID：再生したいファイルをloadした時の戻り値
			// leftVolume,rightVolume：左右のスピーカーからの再生音量。(0.0〜1.0)
			// priority：プライオリティ（0が一番優先度が高い）
			// loop：ループ回数（-1の場合は無限にループ、0の場合はループしない）
			// rate：再生速度（0.5〜2.0：0.5倍から2倍の速度まで設定できる）
			sp.play( chari , 1.0F , 1.0F , 0 , 0 , 1.0F );
			y100.setAlpha( 0  ); // 透過度100%
			nomark=1; // 100円玉非表示
		}
	}

	//ソートして真ん中の値を得るメソッド(平均近似値の取得)
	public float getMedian(ArrayList<Float> v) {
		ArrayList<Float> tmp = (ArrayList<Float>) v.clone();
		Collections.sort(tmp); // 昇順に並べ替える
		return tmp.get(tmp.size()/2); // 中心の値を取得
	}

	// アレーリストの末尾に追加して、最大数を管理（一番古いものを削除する）するメソッド
	void addValue(ArrayList<Float> v,float s){
		v.add(s);	//末尾に追加
		if (v.size() > ELEMENT_COUNT ){
			v.remove(0);//個数を超えていたら先頭を削除
		}
	}

	// 画面をタッチした指が離れた時に100円玉を再表示させるメソッド
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()){
		case MotionEvent.ACTION_UP:
			nomark=0; // 100円玉を再表示させる
			break;
		}
		return true;
	}

}