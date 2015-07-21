package com.cdji.weatherapp;

import java.util.ArrayList;
import java.util.List;

import com.cdji.weathertool.DataBaseHelper;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	private EditText city_text;
	private Button search_btn;
	private Button delete_btn;
	

	// private TableLayout hot_city_tab;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		hasSearcedhistory();

	}

	private void hasSearcedhistory() {
		// TODO Auto-generated method stub
		DataBaseHelper dbhelper = new DataBaseHelper(this);
		SQLiteDatabase searchdb = dbhelper.getReadableDatabase();
		Cursor cursor = searchdb.rawQuery(
				"select * from seachedcity where isalarm=?",
				new String[] { "1" });
		if (cursor.moveToFirst()) {
			// cursor.moveToNext();
			String strnameString = cursor.getString(2);
			System.out.println(cursor.getString(2));
			while (cursor.moveToNext()) {
				System.out.println(cursor.getString(2));
			}

			seachweather(strnameString);
		}
		cursor.close();
		searchdb.close();
		dbhelper.close();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		init();

		super.onResume();
	}

	// 初始化按钮响应事件
	private void init() {
		// TODO Auto-generated method stub
		city_text = (EditText) findViewById(R.id.city_text);
		search_btn = (Button) findViewById(R.id.search);
		delete_btn=(Button) findViewById(R.id.delete_btn);
		
		search_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				// TODO Auto-generated method stub				
				seachweather(city_text.getText().toString());
			}
		});
		
		delete_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				DataBaseHelper dbhelper = new DataBaseHelper(MainActivity.this);
//				SQLiteDatabase searchdb = dbhelper.getReadableDatabase();
//				searchdb.execSQL("delete * from seachedcity;");
//				searchdb.execSQL("delete from seachedcity; select * from seachedcity;update seachedcity set seq=0 where name=seachedcity; ");
//				searchdb.close();
			}
		});
		
		// hot_city_tab=(TableLayout) findViewById(R.id.tablegrid);
		// 添加所有热门城市的响应事件
		List<TextView> textview_list = new ArrayList<TextView>();
		textview_list.add((TextView) findViewById(R.id.beijing));
		textview_list.add((TextView) findViewById(R.id.tianjing));
		textview_list.add((TextView) findViewById(R.id.shanghai));
		textview_list.add((TextView) findViewById(R.id.congqing));
		textview_list.add((TextView) findViewById(R.id.shengyang));
		textview_list.add((TextView) findViewById(R.id.dalian));
		textview_list.add((TextView) findViewById(R.id.changcun));
		textview_list.add((TextView) findViewById(R.id.haerbin));
		textview_list.add((TextView) findViewById(R.id.zhengzou));
		textview_list.add((TextView) findViewById(R.id.wuhan));
		textview_list.add((TextView) findViewById(R.id.changsha));
		textview_list.add((TextView) findViewById(R.id.guangzou));
		textview_list.add((TextView) findViewById(R.id.hefei));
		textview_list.add((TextView) findViewById(R.id.shenzen));
		textview_list.add((TextView) findViewById(R.id.nanjing));
		textview_list.add((TextView) findViewById(R.id.xian));
		textview_list.add((TextView) findViewById(R.id.wuxi));
		textview_list.add((TextView) findViewById(R.id.changzou));
		textview_list.add((TextView) findViewById(R.id.shuzou));
		textview_list.add((TextView) findViewById(R.id.hangzou));
		textview_list.add((TextView) findViewById(R.id.ningbo));
		textview_list.add((TextView) findViewById(R.id.jinan));
		textview_list.add((TextView) findViewById(R.id.qingdao));
		textview_list.add((TextView) findViewById(R.id.fuzou));
		textview_list.add((TextView) findViewById(R.id.xiamen));
		textview_list.add((TextView) findViewById(R.id.chengdu));
		textview_list.add((TextView) findViewById(R.id.kunming));
		textview_list.add((TextView) findViewById(R.id.taian));
		TableListener hclistener = new TableListener();
		for (int i = 0; i < textview_list.size() - 1; i++) {
			textview_list.get(i).setOnClickListener(hclistener);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public class TableListener implements OnClickListener {
		// private EditText editText;
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			seachweather(((TextView) v).getText().toString());
			// switch (v.getId()) {
			// case R.id.beijing:
			// Log.i("Tag", "beijing be clicked");
			// break;
			// case R.id.tianjing:
			// Log.i("TAG", "tianjing be clicked");
			// break;
			// default:
			// Log.i("TAG","other textview been clicked");
			// break;
			// }
		}

	}

	public void seachweather(String cityname) {
		Intent intent = new Intent();
		Bundle bundle = new Bundle();
		bundle.putString("cityname", cityname);
		intent.putExtras(bundle);
		intent.setClass(this, Weather.class);
		startActivity(intent);
	}

}
