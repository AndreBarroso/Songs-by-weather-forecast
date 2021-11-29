package com.andrebarroso.backend.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ListaDeMusicas implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String estilo;
	private String album;
	private String artista;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "chamada_id")
	private ChamadasPlayList playList;
	
	public ListaDeMusicas() {
	}

	public ListaDeMusicas(Long id, String name, String estilo, String album, String artista,ChamadasPlayList playList) {
		this.id = id;
		this.name = name;
		this.estilo = estilo;
		this.album = album;
		this.artista = artista;
		this.playList = playList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEstilo() {
		return estilo;
	}

	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ListaDeMusicas other = (ListaDeMusicas) obj;
		return Objects.equals(id, other.id);
	}
}
