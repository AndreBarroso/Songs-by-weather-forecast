package com.andrebarroso.backend.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andrebarroso.backend.entities.ChamadasPlayList;
import com.andrebarroso.backend.services.ChamadasPlayListService;

@RestController
@RequestMapping(value = "/chamadas")
public class ChamadasPlayListResources {
	
	@Autowired
	private ChamadasPlayListService service;
	
	@GetMapping
	public ResponseEntity<List<ChamadasPlayList>> findAll() {
		List<ChamadasPlayList> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<ChamadasPlayList> insert(@RequestBody ChamadasPlayList obj) {
		obj = service.insert(obj);
		return ResponseEntity.ok().body(obj);
	}
	
}
