package com.andrebarroso.backend.services;

import java.util.List;

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
	
}
