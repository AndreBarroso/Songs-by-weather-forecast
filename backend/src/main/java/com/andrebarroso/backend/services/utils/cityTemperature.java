package com.andrebarroso.backend.services.utils;

import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;
import com.andrebarroso.backend.responses.openweathermapResponse;
import com.andrebarroso.backend.services.utils.cityTemperature;

import reactor.core.publisher.Mono;

public class cityTemperature {
	private String URLBaseOpenweathermap = "https://api.openweathermap.org/data/2.5/weather?q=";
	private String apiTempToken = "&appid=dc883dd38174c35149a5d7d336f5ff65";
	
	private WebClient webClient;
	
	private String city;
	
	public cityTemperature(String city, WebClient webClient) {
		this.webClient = webClient;
		this.city = city;
	}

	public Double getCurrentTemperature() {
		Mono<openweathermapResponse> apiData = this.webClient.
		method(HttpMethod.GET)
		.uri(URLBaseOpenweathermap + city + apiTempToken)
		.retrieve()
		.bodyToMono(openweathermapResponse.class);
		
		openweathermapResponse temperature = apiData.block();
		return temperature.getTemp();
	}
}
