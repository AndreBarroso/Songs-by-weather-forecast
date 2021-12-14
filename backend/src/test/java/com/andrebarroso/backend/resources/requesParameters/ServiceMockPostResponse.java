package com.andrebarroso.backend.resources.requesParameters;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.andrebarroso.backend.entities.ChamadasPlayList;
import com.andrebarroso.backend.entities.ListaDeMusicas;
import com.andrebarroso.backend.responses.playListResponse;

public class ServiceMockPostResponse extends playListResponse{
	private String cidade = "Pato Branco";
	private String solicitante = "André Barroso";
	private String dataDaChamada = Instant.now().toString();
	private String temperatura = "29.0";
	private List<ListaDeMusicas> listaMusicas = new ArrayList<>();
	
	private ListaDeMusicas obj1 = new MusicObjServicePostMock(
			1L, "pop","evermore", "Taylor Swift","willow"
		);
	private ListaDeMusicas obj2 = new MusicObjServicePostMock(
			2L, "pop", "In The Lonely Hour", "Sam Smith","I'm Not The Only One"
		);
	private ListaDeMusicas obj3 = new MusicObjServicePostMock(
			3L, "pop", "Love In The Future (Expanded Edition)", "John Legend","All of Me"
		);
	
	public ServiceMockPostResponse() {
		listaMusicas.add(obj1);
		listaMusicas.add(obj2);
		listaMusicas.add(obj3);
	}

	public String getCidade() {
		return cidade;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public String getDataDaChamada() {
		return dataDaChamada;
	}

	public String getTemperatura() {
		return temperatura;
	}

	public List getListaMusicas() {
		return listaMusicas;
	}
}
