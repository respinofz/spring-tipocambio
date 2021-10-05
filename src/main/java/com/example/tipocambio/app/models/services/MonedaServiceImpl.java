package com.example.tipocambio.app.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tipocambio.app.models.dao.MonedaDao;
import com.example.tipocambio.app.models.documents.Moneda;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MonedaServiceImpl implements MonedaService{

	@Autowired
	private MonedaDao dao;
	
	@Override
	public Flux<Moneda> findAll() {
		return dao.findAll();
	}

	@Override
	public Mono<Moneda> findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public Mono<Moneda> findByNombre(String nombre) {
		return dao.findByNombre(nombre);
	}

	@Override
	public Mono<Moneda> save(Moneda moneda) {
		return dao.save(moneda);
	}

	@Override
	public Flux<Moneda> saveAll(Iterable<Moneda> list) {
		return dao.saveAll(list);
	}

}
