package com.andrebarroso.backend.services;

import java.time.Instant;
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
import com.andrebarroso.backend.services.utils.spotifyEnpointsParameters;

import reactor.core.publisher.Mono;

@Service
public class ChamadasPlayListService {
	private String URLBaseOpenweathermap = "https://api.openweathermap.org/data/2.5/weather?q=";
	private String apiTempToken = "&appid=dc883dd38174c35149a5d7d336f5ff65";
	private Object responsePost;
	private Double temp;
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
		Mono<playListResponse> apiData = this.webClient.
		method(HttpMethod.GET)
		.uri("http://localhost:8080/chamadas/{idRequest}", idRequest)
		.retrieve()
		.bodyToMono(playListResponse.class);
		playListResponse obj = apiData.block();
		return obj;
	}
	
	private void getTracks(String typeOfSongs, ChamadasPlayList obj) {
		String URLSpotifyInitBase = "https://api.spotify.com/v1/playlists/";
		spotifyEnpointsParameters parameter;
		parameter = new spotifyEnpointsParameters(obj.getNumberOfTracks(), obj.getTemperatura());
		
		String qtdTracks = "/tracks?limit=" + parameter.getNumeroDeFaixas();
		String URL = URLSpotifyInitBase + parameter.getEstiloPorCódigo() + qtdTracks;
		System.out.println("minha url");
		System.out.println(URL);
		
		Mono<spotfyResponse> apiData = this.webClient.
		method(HttpMethod.GET)
		.uri(URL)
		.header(HttpHeaders.AUTHORIZATION, obj.getToken())
		.retrieve()
		.bodyToMono(spotfyResponse.class);
		
		spotfyResponse l = apiData.block();
		
		for(int i = 0; i < l.getItems().size(); i ++ ) {
			ListaDeMusicas musica = new ListaDeMusicas(null, l.getSong(i), parameter.getEstiloPorNome(), l.getAlbum(i), l.getArtist(i), obj);

			listaRepository.save(musica);
		}
	}
}
