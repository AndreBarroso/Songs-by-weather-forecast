package com.andrebarroso.backend.entities;

import java.io.Serializable;
import java.util.Objects;

public class ListaDeMusicas implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String estilo;
	private long chamadaId;
	
	public ListaDeMusicas() {
	}

	public ListaDeMusicas(Long id, String name, String estilo, long chamadaId) {
		this.id = id;
		this.name = name;
		this.estilo = estilo;
		this.chamadaId = chamadaId;
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

	public long getChamadaId() {
		return chamadaId;
	}

	public void setChamadaId(long chamadaId) {
		this.chamadaId = chamadaId;
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
