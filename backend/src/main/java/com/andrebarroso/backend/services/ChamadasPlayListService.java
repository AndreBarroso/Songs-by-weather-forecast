package com.andrebarroso.backend.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.andrebarroso.backend.entities.ChamadasPlayList;
import com.andrebarroso.backend.entities.ListaDeMusicas;
import com.andrebarroso.backend.repositories.ChamadasPlayListRepository;
import com.andrebarroso.backend.repositories.ListaDeMusicasRespository;
import com.andrebarroso.backend.responses.openweathermapResponse;
import com.andrebarroso.backend.responses.spotfyResponse;
import com.andrebarroso.backend.services.exceptions.ResourceNotFoundException;

import reactor.core.publisher.Mono;

@Service
public class ChamadasPlayListService {
	private String URLBaseOpenweathermap = "https://api.openweathermap.org/data/2.5/weather?q=";
	private String apiTempToken = "&appid=dc883dd38174c35149a5d7d336f5ff65";
	private Object responsePost;
	private Double temp;
	private String URLSpotifyInitBase = "https://api.spotify.com/v1/playlists/";
	private String URLSpotifyFinalBase = "/tracks?limit=20";
	private String typeMusic;
	private String autorization = "Bearer BQD0yeFh8TUo5UOJ8ry5UIX6xZHaG2Fc6veMtGrhvO6zWDNIHgQflvbQLqfZEybn4RsjOeE7JF7ekD9bbbA8RiBdRZIgmp-pI-OxcsToSFk6KTFZqxr2C-R7aXNk7qijPP_hIglcA8u-nUr4ag";
	private List <String> list = new ArrayList();
	private ListaDeMusicas musicaNova;

	@Autowired
	private WebClient webClient;
	
	@Autowired
	private ChamadasPlayListRepository repository;
	
	@Autowired
	private ListaDeMusicasRespository listaRepository;
	
	public List<ChamadasPlayList> findAll() {
		return repository.findAll();
	}
	
	public ChamadasPlayList insert(ChamadasPlayList obj) {
		try {
			temp = getCurrentTemperature(obj.getCidade());
			obj.setTemperatura(temp);
			obj.setDataDaChamada(Instant.now());
			responsePost = repository.save(obj);
			
			musicaNova = getTracks("37i9dQZF1DXa2PvUpywmrr", obj);
			listaRepository.save(musicaNova);
			
			return repository.save(obj);
			}
			catch(Exception e) {
			  throw new ResourceNotFoundException(obj.getCidade());
			}
	}

	public ChamadasPlayList findById(Long id) {
		Optional <ChamadasPlayList> obj = repository.findById(id);
		return obj.get();
	}
	
	private Double getCurrentTemperature(String city) {
		
		Mono<openweathermapResponse> apiData = this.webClient.
		method(HttpMethod.GET)
		.uri(URLBaseOpenweathermap + city + apiTempToken)
		.retrieve()
		.bodyToMono(openweathermapResponse.class);
		
		openweathermapResponse temperature = apiData.block();
		
		return temperature.getTemp();
	}
	
	private ListaDeMusicas getTracks(String typeOfSongs, ChamadasPlayList obj) {
	
		typeMusic = "37i9dQZF1DXa2PvUpywmrr";
		
		Mono<spotfyResponse> apiData = this.webClient.
		method(HttpMethod.GET)
		.uri(URLSpotifyInitBase + typeOfSongs + URLSpotifyFinalBase)
		.header(HttpHeaders.AUTHORIZATION, autorization)
		.retrieve()
		.bodyToMono(spotfyResponse.class);
		
		spotfyResponse l = apiData.block();
		
		list.add(l.getSong());
		list.add(l.getAlbum());
		list.add(l.getArtist());
		
		ListaDeMusicas musica = new ListaDeMusicas(null, l.getSong(), "festa", l.getAlbum(), l.getArtist(), obj);
//		(Long id, String name, String estilo, long chamadaId, ChamadasPlayList playList)
		
		
		System.out.println(list);
//		System.out.println(l.getTrack());
//		System.out.println(l.getAlbum());
//		System.out.println(l.getArtist());
//		System.out.println(l.getSong());
		
		return musica;
	}
}
