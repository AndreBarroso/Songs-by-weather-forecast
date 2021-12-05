package com.andrebarroso.backend.responses;

import java.util.ArrayList;
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
		return "cidade";
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
	
//	public Object fullData() {
//		return {"temperatura": temperatura};
//	}
	
	@Override
	public String toString() {
		return "{ cidade:" + cidade + ", temperatura:" + temperatura + ", dataDaChamada:"
				+ dataDaChamada + ", solicitante:" + solicitante + ", listaMusicas:" + listaMusicas + "}";
	}

//	@Override
//	public String toString() {
//		return "playListResponse [cidade=" + cidade + ", temperatura=" + temperatura + ", dataDaChamada="
//				+ dataDaChamada + ", solicitante=" + solicitante + ", listaMusicas=" + listaMusicas + "]";
//	}
	
	
}
