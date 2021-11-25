package com.andrebarroso.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrebarroso.backend.entities.ListaDeMusicas;
import com.andrebarroso.backend.repositories.ListaDeMusicasRespository;

@Service
public class ListaDeMusicasService {
	
	@Autowired
	private ListaDeMusicasRespository repository;
	
	public List<ListaDeMusicas> findAll() {
		return repository.findAll();
	}
}
