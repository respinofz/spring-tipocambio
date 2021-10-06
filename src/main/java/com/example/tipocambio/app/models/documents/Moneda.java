package com.example.tipocambio.app.models.documents;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
public class Moneda implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7893958863276054173L;
	
	
	@Id
	private Long id;
	private String nombre;
	private Double tipoCambio;
	
	
	public Moneda(String nombre, Double tipoCambio) {
		this.nombre = nombre;
		this.tipoCambio = tipoCambio;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Double getTipoCambio() {
		return tipoCambio;
	}
	public void setTipoCambio(Double tipoCambio) {
		this.tipoCambio = tipoCambio;
	}
	
	
	
}
