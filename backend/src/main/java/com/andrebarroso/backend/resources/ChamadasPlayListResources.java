package com.andrebarroso.backend.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.andrebarroso.backend.entities.ChamadasPlayList;
import com.andrebarroso.backend.responses.openweathermapResponse;
import com.andrebarroso.backend.services.ChamadasPlayListService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/chamadas")
public class ChamadasPlayListResources {
	
	@Autowired
	private WebClient webClient;
	
	@Autowired
	private ChamadasPlayListService service;
	
	@GetMapping
	public ResponseEntity<List<ChamadasPlayList>> findAll() {
		List<ChamadasPlayList> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ChamadasPlayList> findById(@PathVariable Long id) {
		ChamadasPlayList obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<ChamadasPlayList> insert(@RequestBody ChamadasPlayList obj) {
		getForecast();
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	private Double getForecast() {
		Mono<openweathermapResponse> apiData = this.webClient.
		method(HttpMethod.GET)
		.uri("https://api.openweathermap.org/data/2.5/weather?q=London&appid=dc883dd38174c35149a5d7d336f5ff65")
		.retrieve()
		.bodyToMono(openweathermapResponse.class);
		
//		apiData.subscribe( p -> {
//			System.out.println("finalizou a requisição");
//		});
		
		openweathermapResponse teste = apiData.block();
		
		
		
		System.out.println(teste);
		System.out.println("testooooooou");
		return 33.3;
	}
}
