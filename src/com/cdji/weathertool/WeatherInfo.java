package com.cdji.weathertool;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.R.bool;
import android.R.string;

public class WeatherInfo implements Serializable{
//	private Date date;
	private String date,pubdate;
	
	private String cityname, weathinfo, windirection, windforce;
	private String presentemp, lowesttemp, heightesttemp;
	private String id_city;
	private bool isrecode;
	private bool isalarm;
	

	public bool getIsrecode() {
		return isrecode;
	}

	public void setIsrecode(bool isrecode) {
		this.isrecode = isrecode;
	}

	public bool getIsalarm() {
		return isalarm;
	}

	public void setIsalarm(bool isalarm) {
		this.isalarm = isalarm;
	}

	public String getId_city() {
		return id_city;
	}

	public void setId_city(String id_city) {
		this.id_city = id_city;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}



	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPubdate() {
		return pubdate;
	}

	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	public String getWeathinfo() {
		return weathinfo;
	}

	public void setWeathinfo(String weathinfo) {
		this.weathinfo = weathinfo;
	}

	public String getWindirection() {
		return windirection;
	}

	public void setWindirection(String windirection) {
		this.windirection = windirection;
	}

	public String getWindforce() {
		return windforce;
	}

	public void setWindforce(String windforce) {
		this.windforce = windforce;
	}

	public String getPresentemp() {
		return presentemp;
	}

	public void setPresentemp(String d) {
		this.presentemp = d;
	}

	public String getLowesttemp() {
		return lowesttemp;
	}

	public void setLowesttemp(String lowesttemp) {
		this.lowesttemp = lowesttemp;
	}

	public String getHeightesttemp() {
		return heightesttemp;
	}

	public void setHeightesttemp(String heightesttemp) {
		this.heightesttemp = heightesttemp;
	}

	public String getDateString() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyƒÍMM‘¬dd»’ HH:mm:ss ");
		if (date != null) {
			return formatter.format(date);
		} else {
			return null;
		}

	}

}
