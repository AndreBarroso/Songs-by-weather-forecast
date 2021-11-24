package com.andrebarroso.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andrebarroso.backend.entities.ListaDeMusicas;

public interface ListaDeMusicasRespository extends JpaRepository<ListaDeMusicas, Long>{
}
