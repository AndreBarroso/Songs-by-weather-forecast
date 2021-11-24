package com.andrebarroso.backend.entities.resources;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andrebarroso.backend.entities.ChamadaPlayList;

@RestController
@RequestMapping(value = "chamada")
public class ChamadaPlayListResources {
	
	@GetMapping
	public ResponseEntity<ChamadaPlayList> findAll() {
		ChamadaPlayList list = new ChamadaPlayList(1L, "Belo Horizonte", 50.0, LocalDate.now(), "André");
		return ResponseEntity.ok().body(list);
	}
	
}
