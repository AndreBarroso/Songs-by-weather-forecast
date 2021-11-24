package com.andrebarroso.backend.entities;
import java.io.Serializable;
import java.time.LocalDate;

public class ChamadaPlayList implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String cidade;
	private Double temperatura;
	private LocalDate dataDaChamada;
	private String solicitante;
	
	public ChamadaPlayList(Long id, String cidade, double temperatura, LocalDate dataDaChamada, String solicitante) {
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

	public LocalDate getDataDaChamada() {
		return dataDaChamada;
	}

	public void setDataDaChamada(LocalDate dataDaChamada) {
		this.dataDaChamada = dataDaChamada;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}
}

