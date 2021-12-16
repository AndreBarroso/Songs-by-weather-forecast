package com.andrebarroso.backend.services.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import com.andrebarroso.backend.entities.ChamadasPlayList;
import com.andrebarroso.backend.entities.ListaDeMusicas;
import com.andrebarroso.backend.repositories.ListaDeMusicasRespository;
import com.andrebarroso.backend.responses.playListResponse;
import com.andrebarroso.backend.responses.spotfyResponse;

import reactor.core.publisher.Mono;

public class tracksSpotify {
	
	private ChamadasPlayList dataPost;
	private WebClient webClient;
	private ListaDeMusicasRespository listaRepository;
	
	public tracksSpotify(ChamadasPlayList dataPost, WebClient webClient,
			ListaDeMusicasRespository listaRepository) {
		this.dataPost = dataPost;
		this.webClient = webClient;
		this.listaRepository = listaRepository;
	}

	public void getTracks() {
		String URLSpotifyInitBase = "https://api.spotify.com/v1/playlists/";
		spotifyEnpointsParameters parameter;
		parameter = new spotifyEnpointsParameters(dataPost.getNumberOfTracks(), dataPost.getTemperatura());
		
		String qtdTracks = "/tracks?limit=" + parameter.getNumeroDeFaixas();
		String URL = URLSpotifyInitBase + parameter.getEstiloPorCódigo() + qtdTracks;
		System.out.println("minha url");
		System.out.println(URL);
		
		Mono<spotfyResponse> apiData = this.webClient.
		method(HttpMethod.GET)
		.uri(URL)
		.header(HttpHeaders.AUTHORIZATION, dataPost.getToken())
		.retrieve()
		.bodyToMono(spotfyResponse.class);
		
		spotfyResponse l = apiData.block();
		
		for(int i = 0; i < l.getItems().size(); i ++ ) {
			ListaDeMusicas musica = new ListaDeMusicas(null, l.getSong(i), parameter.getEstiloPorNome(), l.getAlbum(i), l.getArtist(i), dataPost);

			listaRepository.save(musica);
		}
	}
	
	public playListResponse playListSugestion(Long idRequest) {
		Mono<playListResponse> APIData = this.webClient.
		method(HttpMethod.GET)
//		.uri("http://localhost:8080/chamadas/{idRequest}", idRequest)
		.uri("https://songs-by-weather-forecast-back.herokuapp.com/chamadas/{idRequest}", idRequest)
		.retrieve()
		.bodyToMono(playListResponse.class);
		playListResponse obj = APIData.block();
		return obj;
	}
}
