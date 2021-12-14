package com.andrebarroso.backend.resources.requesParameters;

import com.andrebarroso.backend.entities.ListaDeMusicas;

public class MusicObjServicePostMock extends ListaDeMusicas{
	private Long id;
	private String estilo;
	private String album;
	private String artista;
	private String nome;
	public MusicObjServicePostMock(Long id, String estilo, String album, String artista, String nome) {
		this.id = id;
		this.estilo = estilo;
		this.album = album;
		this.artista = artista;
		this.nome = nome;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getEstilo() {
		return estilo;
	}
	
	public String getAlbum() {
		return album;
	}
	
	public String getArtista() {
		return artista;
	}
	
	public String getNome() {
		return nome;
	}
}
