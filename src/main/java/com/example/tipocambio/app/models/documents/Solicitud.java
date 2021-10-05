package com.example.tipocambio.app.models.documents;

public class Solicitud {

	private Double monto;
	private Double monto_tc;
	private String moneda_origen;
	private String moneda_destino;
	private Double tipo_cambio;
	
	
	public Solicitud(Double monto, Double monto_tc, String moneda_origen, String moneda_destino, Double tipo_cambio) {
		this.monto = monto;
		this.monto_tc = monto_tc;
		this.moneda_origen = moneda_origen;
		this.moneda_destino = moneda_destino;
		this.tipo_cambio = tipo_cambio;
	}
	
	public Solicitud() {
	}

	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	public Double getMonto_tc() {
		return monto_tc;
	}
	public void setMonto_tc(Double monto_tc) {
		this.monto_tc = monto_tc;
	}
	public String getMoneda_origen() {
		return moneda_origen;
	}
	public void setMoneda_origen(String moneda_origen) {
		this.moneda_origen = moneda_origen;
	}
	public String getMoneda_destino() {
		return moneda_destino;
	}
	public void setMoneda_destino(String moneda_destino) {
		this.moneda_destino = moneda_destino;
	}
	public Double getTipo_cambio() {
		return tipo_cambio;
	}
	public void setTipo_cambio(Double tipo_cambio) {
		this.tipo_cambio = tipo_cambio;
	}
	
	
}
