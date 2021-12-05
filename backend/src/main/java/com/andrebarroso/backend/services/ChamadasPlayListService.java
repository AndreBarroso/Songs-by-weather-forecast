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
import com.andrebarroso.backend.responses.playListResponse;
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
	private String autorization = "Bearer BQCo5-8eu98BkKNcSzT_NG5qymG_1kVChZhmZrUFU9U36XNUw16VbKR9G-UtxKsO-HwYSPcmTIhEus-j9PJLBdsZqsMZoUNnhUh3gWkg16nJJKINaxLi9QPf9xXOWHDw-65EWWA_wu4_6eF-KQ";
	private List <String> list = new ArrayList();
	private ListaDeMusicas musicaNova;
	private List testa;
	private ChamadasPlayList response;

	@Autowired
	private WebClient webClient;
	
	@Autowired
	private ChamadasPlayListRepository repository;
	
	@Autowired
	private ListaDeMusicasRespository listaRepository;
	
	public List<ChamadasPlayList> findAll() {
		return repository.findAll();
	}
	
	public playListResponse insert(ChamadasPlayList obj) {
		try {
			temp = getCurrentTemperature(obj.getCidade());
			obj.setTemperatura(temp);
			obj.setDataDaChamada(Instant.now());
			responsePost = repository.save(obj);
			
			getTracks("37i9dQZF1DXa2PvUpywmrr", obj);
			
			response = repository.save(obj);
			
			playListSugestion(obj.getId());      
			return playListSugestion(obj.getId());
	
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
	
	private playListResponse playListSugestion(Long idRequest) {
		System.out.println("ooooooooooooooooooooooooo");  

		Mono<playListResponse> apiData = this.webClient.
		method(HttpMethod.GET)
		.uri("http://localhost:8080/chamadas/{idRequest}", idRequest)
		.retrieve()
		.bodyToMono(playListResponse.class);
		
		System.out.println("teeeeeeeeeeestou111");
		
		playListResponse obj = apiData.block();
	
		System.out.println(obj.getListaMusicas());
		
		return obj;
	}
	
//	public void getSpotfyToken () {
//		String clientID‌ = "4e8d12ac54234d9091767fdf7890e974";
//		String clientSecret = "meu secret";
//		String stringToEncode = clientID‌ + ":" + clientSecret;
//        String base64Code = Base64.getEncoder().encodeToString(stringToEncode.getBytes());
//
//        System.out.println("Texto em Base64: " + base64Code);
//		
//		Mono<spotifyTokenReponse> apiData = this.webClient.
//		method(HttpMethod.POST)
//		.uri("https://accounts.spotify.com/api/token")
//		.header(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
//		.header(HttpHeaders.AUTHORIZATION, "Basic " + base64Code)
//		.retrieve()
//		.bodyToMono(spotifyTokenReponse.class);
//		
//		spotifyTokenReponse l = apiData.block();
//		
//		System.out.println("Deu certo: " + l);
//	}
	
	private void getTracks(String typeOfSongs, ChamadasPlayList obj) {
	
		typeMusic = "37i9dQZF1DXa2PvUpywmrr";
		
		Mono<spotfyResponse> apiData = this.webClient.
		method(HttpMethod.GET)
		.uri(URLSpotifyInitBase + typeOfSongs + URLSpotifyFinalBase)
		.header(HttpHeaders.AUTHORIZATION, autorization)
		.retrieve()
		.bodyToMono(spotfyResponse.class);
		
		spotfyResponse l = apiData.block();
		
//		getSpotfyToken();
		
		for(int i = 0; i < l.getItems().size(); i ++ ) {
			ListaDeMusicas musica = new ListaDeMusicas(null, l.getSong(i), "festa", l.getAlbum(i), l.getArtist(i), obj);

			listaRepository.save(musica);
		}
	}
}
