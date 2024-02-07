package alquileres.modelo;

import java.time.LocalDateTime;

public class Reserva {

	private String idBicicleta;
	private LocalDateTime creada;
	private LocalDateTime caducidad;
	private boolean caducada;

	public Reserva(String id, LocalDateTime creada, LocalDateTime caducidad) {
		this.idBicicleta = id;
		this.creada = creada;
		this.caducidad = caducidad;
	}

	public String getIdBicicleta() {
		return idBicicleta;
	}

	public void setIdBicicleta(String idBicicleta) {
		this.idBicicleta = idBicicleta;
	}

	public LocalDateTime getCreada() {
		return creada;
	}

	public void setCreada(LocalDateTime creada) {
		this.creada = creada;
	}

	public LocalDateTime getCaducidad() {
		return caducidad;
	}

	public void setCaducidad(LocalDateTime caducidad) {
		this.caducidad = caducidad;
	}

	public boolean isCaducada() {
		LocalDateTime ahora = LocalDateTime.now();
		this.setCaducada(ahora.isAfter(this.caducidad));
		return ahora.isAfter(this.caducidad);
	}

	public void setCaducada(boolean caducada) {
		this.caducada = caducada;
	}

	public boolean activa() {
		return !this.caducada;
	}

}
