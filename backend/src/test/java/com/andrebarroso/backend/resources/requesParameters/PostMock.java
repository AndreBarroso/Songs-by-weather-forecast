package com.andrebarroso.backend.resources.requesParameters;

import com.andrebarroso.backend.entities.ChamadasPlayList;

public class PostMock extends ChamadasPlayList{
	private String token = "j32kjbu3ihi3223dfe";
	private String numberOfTracks = "3";
	private String solicitante = "André Barroso";
	private String cidade = "Pato Branco";
	
	public PostMock() {
	}
	
	public String getToken() {
		return token;
	}
	
	public String getNumberOfTracks() {
		return numberOfTracks;
	}
	
	public String getSolicitante() {
		return solicitante;
	}
	
	public String getCidade() {
		return cidade;
	}
}
