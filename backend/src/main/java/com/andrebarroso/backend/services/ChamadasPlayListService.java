package com.andrebarroso.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.andrebarroso.backend.entities.ChamadasPlayList;
import com.andrebarroso.backend.repositories.ChamadasPlayListRepository;
import com.andrebarroso.backend.responses.openweathermapResponse;

import reactor.core.publisher.Mono;

@Service
public class ChamadasPlayListService {
	private String URLBase = "https://api.openweathermap.org/data/2.5/weather?q=";
	private String apiTempToken = "&appid=dc883dd38174c35149a5d7d336f5ff65";
	
	@Autowired
	private WebClient webClient;
	
	@Autowired
	private ChamadasPlayListRepository repository;
	
	public List<ChamadasPlayList> findAll() {
		return repository.findAll();
	}
	
	public ChamadasPlayList insert(ChamadasPlayList obj) {
		obj.setTemperatura(getCurrentTemperature("London"));
		
		return repository.save(obj);
	}

	public ChamadasPlayList findById(Long id) {
		Optional <ChamadasPlayList> obj = repository.findById(id);
		return obj.get();
	}
	
	private Double getCurrentTemperature(String city) {
		
		Mono<openweathermapResponse> apiData = this.webClient.
		method(HttpMethod.GET)
		.uri(URLBase + city + apiTempToken)
		.retrieve()
		.bodyToMono(openweathermapResponse.class);
		
		openweathermapResponse temperature = apiData.block();
		
		return temperature.getTemp();
	}
}
