package com.example.tipocambio.app.models.services;

import com.example.tipocambio.app.models.documents.Moneda;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MonedaService {

	public Flux<Moneda> findAll();
	
	public Mono<Moneda> findById(Long id);
	
	public Mono<Moneda> findByNombre(String nombre);
	
	public Mono<Moneda> save(Moneda moneda);
	
	public Flux<Moneda> saveAll(Iterable<Moneda> list);
	
}
