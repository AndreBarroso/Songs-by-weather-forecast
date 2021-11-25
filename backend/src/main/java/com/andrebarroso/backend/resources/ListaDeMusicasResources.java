package com.andrebarroso.backend.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andrebarroso.backend.entities.ListaDeMusicas;
import com.andrebarroso.backend.services.ListaDeMusicasService;

@RestController
@RequestMapping(value = "/musicas")
public class ListaDeMusicasResources {
	
	@Autowired
	private ListaDeMusicasService service;

	@GetMapping
	public ResponseEntity<List<ListaDeMusicas>> findAll() {
		List<ListaDeMusicas> l = service.findAll();
		return ResponseEntity.ok().body(l);
	}
}
