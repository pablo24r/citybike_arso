package alquileres.modelo;

import java.time.LocalDateTime;

import repositorio.Identificable;

public class Bicicleta implements Identificable {
	private String id;
	private String modelo;
	private LocalDateTime fechaAlta;
	private LocalDateTime fechaBaja;
	private boolean disponible;
	private String idEstacion;
	
	
	
	public Bicicleta(String modelo) {
		super();
		this.modelo = modelo;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public LocalDateTime getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(LocalDateTime fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public boolean isDisponible() {
		return disponible;
	}
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}


	public LocalDateTime getFechaBaja() {
		return fechaBaja;
	}


	public void setFechaBaja(LocalDateTime fechaBaja) {
		this.fechaBaja = fechaBaja;
	}


	public String getIdEstacion() {
		return idEstacion;
	}


	public void setIdEstacion(String idEstacion) {
		this.idEstacion = idEstacion;
	}
	
	
}
