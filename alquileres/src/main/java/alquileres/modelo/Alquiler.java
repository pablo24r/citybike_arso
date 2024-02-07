package alquileres.modelo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Alquiler {

	private String idBicicleta;
	private LocalDateTime inicio;
	private LocalDateTime fin;

	public Alquiler(String idBicicleta, LocalDateTime inicio, LocalDateTime fin) {
		super();
		this.idBicicleta = idBicicleta;
		this.inicio = inicio;
		this.fin = fin;
	}

	public String getIdBicicleta() {
		return idBicicleta;
	}

	public void setIdBicicleta(String idBicicleta) {
		this.idBicicleta = idBicicleta;
	}

	public LocalDateTime getInicio() {
		return inicio;
	}

	public void setInicio(LocalDateTime inicio) {
		this.inicio = inicio;
	}

	public LocalDateTime getFin() {
		return fin;
	}

	public void setFin(LocalDateTime fin) {
		this.fin = fin;
	}

	public boolean activo() {
		if (this.fin == null)
			return true;
		else
			return false;
	}

	public double tiempo() {
		LocalDateTime ahora = LocalDateTime.now();

		// Si el alquiler está activo usamos el instante actual (fin no tiene valor)
		LocalDateTime momentoFinal = (fin != null) ? fin : ahora;

		return ChronoUnit.MINUTES.between(inicio, momentoFinal);
	}

}
