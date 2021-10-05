package com.example.tipocambio.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.tipocambio.app.handler.MonedaHandler;

@Configuration
public class RouterFunctionConfig {
	
	@Bean
	public RouterFunction<ServerResponse> routes(MonedaHandler handler){
		
		return RouterFunctions.route(RequestPredicates.GET("/api/tipocambio"), request -> handler.listar(request))
				.andRoute(RequestPredicates.GET("/api/tipocambio/procesar"), request -> handler.procesar(request))
				.andRoute(RequestPredicates.GET("/api/tipocambio/{nombre}"), request -> handler.ver(request))
				.andRoute(RequestPredicates.POST("/api/tipocambio/actualizar/{nombre}"), request -> handler.editar(request)); 
	}

}
