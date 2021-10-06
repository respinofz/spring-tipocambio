package com.example.tipocambio.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.tipocambio.app.models.documents.Moneda;
import com.example.tipocambio.app.models.services.MonedaService;


@SpringBootApplication
public class SpringBootTipocambioApirestApplication implements CommandLineRunner{
	
	@Autowired
	private MonedaService service;
	
	private static final Logger log = LoggerFactory.getLogger(SpringBootTipocambioApirestApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTipocambioApirestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		
		
		List<Integer> arreglo = Arrays.asList(1, 21, 8, 13, 6, 15, 7, 15);
		
		
		List<Integer> arregloAux = new ArrayList<>();
		for (int i=0; i<arreglo.size();i++) {
			if (arreglo.get(i)%2>0) arregloAux.add(arreglo.get(i));
		}
			
		
		arregloAux.sort(null);
		
		log.info(arregloAux.toString());
		
		
		
		
		List<Moneda> monedas = Arrays.asList(
		 new Moneda("PEN", 1.00),
		 new Moneda("USD", 0.24),
		 new Moneda("EUR", 0.21),
		 new Moneda("GBP", 0.18),
		 new Moneda("AUD", 0.33),
		 new Moneda("CAD", 0.30),
		 new Moneda("CNY", 1.56));
		
		service.saveAll(monedas)	
		.subscribe();
		
		
	}

}
