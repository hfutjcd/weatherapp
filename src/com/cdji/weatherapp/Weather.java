package com.cdji.weatherapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.cdji.weathertool.DataBaseHelper;
import com.cdji.weathertool.Nettool;
import com.cdji.weathertool.WeatherInfo;

public class Weather extends Activity {
	private String cityname;
	private WeatherInfo weathinfo;
	private TextView city_nameTextView;
	private TextView weatherinfoTextView;
	private TextView weatherdateTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		try {
			cityname = this.getIntent().getExtras().getString("cityname");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			cityname = "石家庄";
			e.printStackTrace();
		}

		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weathershow);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		init();
		getWeatherinfo(cityname);
		super.onResume();
	}

	private void init() {
		// TODO Auto-generated method stub
		city_nameTextView = (TextView) findViewById(R.id.weather_cityname_text);
		weatherinfoTextView = (TextView) findViewById(R.id.weatherinfo_detail);
		weatherdateTextView=(TextView) findViewById(R.id.weather_date_text);
	}

	private void getWeatherinfo(String cityname) {
		Asyweatherget wget = new Asyweatherget();
		wget.execute("");
	}
	public void showweather(WeatherInfo weatherInfo) {
		city_nameTextView.setText(weatherInfo.getCityname());
		weatherdateTextView.setText(weatherInfo.getDate()+"  "+weatherInfo.getweek());
		StringBuffer strbufBuffer = new StringBuffer();
		strbufBuffer.append("天气状况："+weatherInfo.getWeathinfo() + '\n' + '\n');
		strbufBuffer.append("当前温度："+weatherInfo.getPresentemp() + " ℃" + '\n');
		strbufBuffer.append("最低温度："+weatherInfo.getLowesttemp() + " ℃" + '\n');
		strbufBuffer.append("最高温度："+weatherInfo.getHeightesttemp() + " ℃" + '\n');
		strbufBuffer.append("风向："+weatherInfo.getWindirection() + "" + '\n');
		strbufBuffer.append("风力："+weatherInfo.getWindforce() + "" + '\n');
		weatherinfoTextView.setText(strbufBuffer.toString());
		DataBaseHelper dbhelper = new DataBaseHelper(this);
		SQLiteDatabase searchdb = dbhelper.getReadableDatabase();
		Cursor cursor=searchdb.rawQuery("select * from seachedcity where cityname=?",
				new String[] { weatherInfo.getCityname()});
		if (!cursor.moveToFirst()) {
			searchdb.execSQL("insert into seachedcity (id_citiy,cityname,isrecode,isalarm) values(\""+weatherInfo.getId_city()+"\",\""+weatherInfo.getCityname()+"\",\"1\",\"1\");");
		}
		
	}


	public class Asyweatherget extends AsyncTask<String, String, WeatherInfo> {

		@Override
		protected WeatherInfo doInBackground(String... params) {
			// TODO Auto-generated method stub
			System.out.println(cityname);
			WeatherInfo resultInfo=Nettool.getInfo(cityname);
			return resultInfo;
		}

		@Override
		protected void onPostExecute(WeatherInfo result) {
			// TODO Auto-generated method stub
			if (result!=null){
			Weather.this.showweather(result);
			System.out.println("联网成功");
			}
			else {
				System.out.println("联网失败");
			}
		}
		

	}
}
