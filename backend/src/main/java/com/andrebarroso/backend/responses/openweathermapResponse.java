package com.andrebarroso.backend.responses;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class openweathermapResponse {
	private Object main;
	private String mainString;
	private String tempString;
	private Double tempCelcius;

	
	private String getTempKelvin() {
		mainString = main.toString();
		tempString = mainString.substring(6, mainString.indexOf(","));
		return mainString.substring(6, mainString.indexOf(","));
	}
	
	private Double getTempCelcius(String temp) {
		return Double.parseDouble( temp ) - 273.15;
	}
	
	public Double getTemp() {
		tempCelcius = getTempCelcius(getTempKelvin());
		return Math.round(tempCelcius * 10.0)/10.0;
	}
}
