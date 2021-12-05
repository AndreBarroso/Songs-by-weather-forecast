package com.andrebarroso.backend.responses;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class playListResponse {
	private String cidade;
	private String temperatura;
	private String dataDaChamada;
	private String solicitante;
	private List listaMusicas;
	
	public String getCidade() {
		return cidade;
	}
	
	public String getTemperatura() {
		return temperatura;
	}
	
	public String getDataDaChamada() {
		return dataDaChamada;
	}
	
	public String getSolicitante() {
		return solicitante;
	}
	
	public List getListaMusicas() {
		return listaMusicas;
	}
}
