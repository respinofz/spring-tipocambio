package com.example.tipocambio.app.models.dao;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.tipocambio.app.models.documents.Moneda;

import reactor.core.publisher.Mono;

public interface MonedaDao extends ReactiveCrudRepository<Moneda, Long> {

	@Query("SELECT * FROM moneda WHERE nombre = :nombre")
	public Mono<Moneda> findByNombre(String nombre);
}
