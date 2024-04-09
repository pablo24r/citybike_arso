package alquileres.modelo;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import repositorio.Identificable;
 
public class Bicicleta implements Identificable {
	@Id
	private String id;
	private String modelo;
	private LocalDateTime fechaAlta;
	private LocalDateTime fechaBaja;
	private boolean disponible;
	
	
	
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
	
	
}
