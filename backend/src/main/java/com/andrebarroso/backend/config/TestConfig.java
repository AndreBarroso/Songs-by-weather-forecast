package com.andrebarroso.backend.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.andrebarroso.backend.entities.ListaDeMusicas;
import com.andrebarroso.backend.repositories.ListaDeMusicasRespository;


@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private ListaDeMusicasRespository listaDeMusicasRepository;

	@Override
	public void run(String... args) throws Exception {
		ListaDeMusicas l1 = new ListaDeMusicas(null, "Californication", "Rock", 1L);
		ListaDeMusicas l2 = new ListaDeMusicas(null, "ByTheWay", "Rock", 2L);
		
		listaDeMusicasRepository.saveAll(Arrays.asList(l1, l2));
		
	}
}
