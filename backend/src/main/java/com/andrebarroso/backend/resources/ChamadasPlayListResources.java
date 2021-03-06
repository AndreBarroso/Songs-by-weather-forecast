package com.andrebarroso.backend.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.andrebarroso.backend.entities.ChamadasPlayList;
import com.andrebarroso.backend.responses.playListResponse;
import com.andrebarroso.backend.services.ChamadasPlayListService;

@RestController
@RequestMapping(value = "/chamadas", consumes="application/json", produces ="application/json")
public class ChamadasPlayListResources {
	private playListResponse requestCompleted;

	@Autowired
	private ChamadasPlayListService service;
	
	@GetMapping
	public ResponseEntity<List<ChamadasPlayList>> findAll() {
		List<ChamadasPlayList> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ChamadasPlayList> findById(@PathVariable Long id) {
		ChamadasPlayList obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/filter")
	public ResponseEntity<List<ChamadasPlayList>> findByName(@RequestParam("name") String name) {
		List<ChamadasPlayList> obj = service.findAll();
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<playListResponse> insert(@RequestBody ChamadasPlayList obj) {

		requestCompleted = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.ok().body(requestCompleted);
	}
}
