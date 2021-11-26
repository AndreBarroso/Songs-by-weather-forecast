package com.andrebarroso.backend.entities;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ChamadasPlayList implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cidade;
	private Double temperatura;
	private Instant dataDaChamada;
	private String solicitante;
	
	@OneToMany(mappedBy = "playList")
	private List<ListaDeMusicas> listaMusicas = new ArrayList<>();
	
	public ChamadasPlayList(Long id, String cidade, double temperatura, Instant dataDaChamada, String solicitante) {
		this.id = id;
		this.cidade = cidade;
		this.temperatura = temperatura;
		this.dataDaChamada = dataDaChamada;
		this.solicitante = solicitante;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Double getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(Double temperatura) {
		this.temperatura = temperatura;
	}

	public Instant getDataDaChamada() {
		return dataDaChamada;
	}

	public void setDataDaChamada(Instant dataDaChamada) {
		this.dataDaChamada = dataDaChamada;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}
	
	public List<ListaDeMusicas> getListaMusicas() {
		return listaMusicas;
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
		ChamadasPlayList other = (ChamadasPlayList) obj;
		return Objects.equals(id, other.id);
	}

}
