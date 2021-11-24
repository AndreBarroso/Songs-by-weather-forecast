package com.andrebarroso.backend.entities.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andrebarroso.backend.entities.ListaDeMusicas;

@RestController
@RequestMapping(value = "musicas")
public class ListaDeMusicasResources {

	@GetMapping
	public ResponseEntity<ListaDeMusicas> findAll() {
		ListaDeMusicas l = new ListaDeMusicas(1L, "Californication", "Rock", 1L);
		return ResponseEntity.ok().body(l);
	}
}
