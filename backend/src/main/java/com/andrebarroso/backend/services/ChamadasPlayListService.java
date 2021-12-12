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
import com.andrebarroso.backend.responses.playListResponse;
import com.andrebarroso.backend.responses.spotfyResponse;
import com.andrebarroso.backend.services.exceptions.ResourceNotFoundException;
import com.andrebarroso.backend.services.utils.cityTemperature;
import com.andrebarroso.backend.services.utils.spotifyEnpointsParameters;
import com.andrebarroso.backend.services.utils.tracksSpotify;

import reactor.core.publisher.Mono;

@Service
public class ChamadasPlayListService {
	private Object responsePost;
	private Double temp;
	private ChamadasPlayList response;
	private cityTemperature objAPITemperature;
	private tracksSpotify objAPITracksSpotify;

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
			objAPITemperature = new cityTemperature(obj.getCidade(), webClient);
            temp = objAPITemperature.getCurrentTemperature();
			obj.setTemperatura(temp);
			obj.setDataDaChamada(Instant.now());
			responsePost = repository.save(obj);

			objAPITracksSpotify = new tracksSpotify(obj, webClient, listaRepository);
			objAPITracksSpotify.getTracks();
  
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
	
	private playListResponse playListSugestion(Long idRequest) {
		Mono<playListResponse> apiData = this.webClient.
		method(HttpMethod.GET)
		.uri("http://localhost:8080/chamadas/{idRequest}", idRequest)
		.retrieve()
		.bodyToMono(playListResponse.class);
		playListResponse obj = apiData.block();
		return obj;
	}
}
