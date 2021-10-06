package com.example.tipocambio.app.handler;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.tipocambio.app.models.documents.Moneda;
import com.example.tipocambio.app.models.documents.Solicitud;
import com.example.tipocambio.app.models.services.MonedaService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class MonedaHandler {
	
	@Autowired
	private MonedaService service;
	
	@Autowired
	private Validator validator;
	
	
	private static final Logger log = LoggerFactory.getLogger(MonedaHandler.class);
	
	public Mono<ServerResponse> ver(ServerRequest request){
		
		String nombre = request.pathVariable("nombre");
		
		return service.findByNombre(nombre).flatMap(m -> ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(m))
				.switchIfEmpty(ServerResponse.notFound().build())); 
	}
	

	public Mono<ServerResponse> listar(ServerRequest request){
		
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(service.findAll(), Moneda.class);
		
	}
	
	public Mono<ServerResponse> procesar(ServerRequest request){
		
		Solicitud solicitud = new Solicitud();
		
		String moneda_origen = request.queryParam("moneda_origen").get();
		String moneda_destino = request.queryParam("moneda_destino").get();
		Double monto = Double.valueOf(request.queryParam("monto").get());
		
				
		return service.findByNombre(moneda_origen)
			.zipWith(service.findByNombre(moneda_destino))
			.map(tuple -> {
				Moneda mo = tuple.getT1();
				Moneda md = tuple.getT2();
				
				solicitud.setMonto(monto);
				solicitud.setMonto_tc((monto/mo.getTipoCambio())*md.getTipoCambio());
				solicitud.setMonto_tc(Math.round(solicitud.getMonto_tc() * 100.0) / 100.0);			
				solicitud.setMoneda_origen(moneda_origen);
				solicitud.setMoneda_destino(moneda_destino);
				solicitud.setTipo_cambio(md.getTipoCambio()/mo.getTipoCambio());
				solicitud.setTipo_cambio(Math.round(solicitud.getTipo_cambio() * 100.0) / 100.0);
				
				return solicitud;
			}).flatMap(s -> ServerResponse.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(BodyInserters.fromValue(s)));
	}
	
	
	
	
	
	public Mono<ServerResponse> editar(ServerRequest request){
		
		Mono<Moneda> moneda = request.bodyToMono(Moneda.class);
		
		String nombre = request.pathVariable("nombre");
		
		Mono<Moneda> monedaDB = service.findByNombre(nombre);
		
		return monedaDB.zipWith(moneda, (mod, mon) -> {
			
			mod.setTipoCambio(mon.getTipoCambio());
			return mod;
		}).flatMap(m -> ServerResponse
				.created(URI.create("/api/tipocambio/".concat(m.getNombre())))
				.contentType(MediaType.APPLICATION_JSON)
				.body(service.save(m), Moneda.class));
	}
	
	
	
	  public Mono<ServerResponse> nuevoactualizar(ServerRequest request){
	  	  
		  Flux<Moneda> monedas = request.bodyToFlux(Moneda.class);
		  
		  Flux<Moneda> monedasDB =  monedas.flatMap(this::filtrarMonedas)
			  		.flatMap(service::save);
		  
		  return ServerResponse.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(monedasDB, Moneda.class);  
	  
	  }
	  
	  private Mono<Moneda> filtrarMonedas(Moneda moneda) {  
		  
		  return service.findByNombre(moneda.getNombre())
				  .map(mon -> {
					  Moneda newMoneda = new Moneda(mon.getNombre(), moneda.getTipoCambio());
					  newMoneda.setId(mon.getId());
					  return newMoneda;
				  })
		          .defaultIfEmpty(moneda);
	 }
	 

}
