package com.andrebarroso.backend.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(String city) {
		super("Cidade não encontrada ou " + "( "+ city + " )" + " é um valor inválido");
	}
}
