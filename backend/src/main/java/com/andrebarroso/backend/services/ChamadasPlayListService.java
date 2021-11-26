package com.andrebarroso.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrebarroso.backend.entities.ChamadasPlayList;
import com.andrebarroso.backend.repositories.ChamadasPlayListRepository;

@Service
public class ChamadasPlayListService {
	@Autowired
	private ChamadasPlayListRepository repository;
	
	public List<ChamadasPlayList> findAll() {
		return repository.findAll();
	}
	
	public ChamadasPlayList insert(ChamadasPlayList obj) {
		return repository.save(obj);
	}

	public ChamadasPlayList findById(Long id) {
		Optional <ChamadasPlayList> obj = repository.findById(id);
		return obj.get();
	}
}
