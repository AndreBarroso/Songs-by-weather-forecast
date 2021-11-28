package com.andrebarroso.backend.responses;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class openweathermapResponse {
	private Object main;
	private String base;
	
	@Override
	public String toString() {
		return "openweathermapResponse [main=" + main + ", base=" + base + "]";
	}
	
	
}
