package com.andrebarroso.backend.services.utils;

public class spotifyEnpointsParameters {
	private final String codigoMusicasFesta = "37i9dQZF1DXa2PvUpywmrr";
	private final String codigoMusicasPop = "37i9dQZF1DWTwnEm1IYyoj";
	private final String codigoMusicasRock = "37i9dQZF1DX8FwnYE6PRvL";
	private final String codigoMusicasClassicas = "37i9dQZF1DWWEJlAGA9gs0";
	
	private String estilo;
	private String numeroDeFaixas;
	private Double temperatura;
	
	public spotifyEnpointsParameters(String numeroDeFaixas, Double temperatura) {
		this.numeroDeFaixas = numeroDeFaixas;
		this.temperatura = temperatura;
	}
	
	public Double getTemperatura() {
		return temperatura;
	}
	
	public String getNumeroDeFaixas() {
		return numeroDeFaixas;
	}

	public String getEstiloPorCódigo() {
		if(temperatura > 30) return codigoMusicasFesta;
		if(temperatura >= 15 && temperatura <= 30) return codigoMusicasPop;
		if(temperatura >= 10 && temperatura < 15) return codigoMusicasRock;
		return codigoMusicasClassicas;
	}
	
	public String getEstiloPorNome() {
		String estilo = getEstiloPorCódigo();
		if(estilo == codigoMusicasFesta ) return "festa";
		if(estilo == codigoMusicasPop ) return "pop";
		if(estilo == codigoMusicasRock ) return "rock";
		return "classica";
	}
}
