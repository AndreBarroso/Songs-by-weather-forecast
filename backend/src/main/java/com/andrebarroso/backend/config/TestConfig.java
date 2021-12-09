package com.andrebarroso.backend.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.andrebarroso.backend.entities.ChamadasPlayList;
import com.andrebarroso.backend.entities.ListaDeMusicas;
import com.andrebarroso.backend.repositories.ChamadasPlayListRepository;
import com.andrebarroso.backend.repositories.ListaDeMusicasRespository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private ListaDeMusicasRespository listaDeMusicasRepository;
	
	@Autowired
	private ChamadasPlayListRepository chamadaPlayListRespository;
	
	@Override
	public void run(String... args) throws Exception {
		
		 ChamadasPlayList c1 = new ChamadasPlayList(null, "Belo Horizonte", 40.0, Instant.now(), "André", "jnfeinfwen42342");
		 ChamadasPlayList c2 = new ChamadasPlayList(null, "Porto Alegre", 5.0, Instant.now(), "Pedro", "nfoerno2394932");

		 ListaDeMusicas l1 = new ListaDeMusicas(null, "Californication", "Rock", "Greates Hits", "Red Hot", c1);
		 ListaDeMusicas l2 = new ListaDeMusicas(null, "ByTheWay","Rock", "Greates Hits", "Red Hot", c1);
		 
		 chamadaPlayListRespository.saveAll(Arrays.asList(c1, c2));
		 listaDeMusicasRepository.saveAll(Arrays.asList(l1, l2));
	}
}
