package alquileres.modelo;

import java.time.LocalDateTime;
import java.util.List;

public class Usuario {

	private final String id;
	private List<Reserva> reservas;
	private List<Alquiler> alquileres;

	public Usuario(String id, List<Reserva> reservas, List<Alquiler> alquileres) {
		super();
		this.id = id;
		this.reservas = reservas;
		this.alquileres = alquileres;
	}

	public String getId() {
		return id;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public List<Alquiler> getAlquileres() {
		return alquileres;
	}

	// Métodos

	public int reservasCaducadas() {
		return (int) reservas.stream().filter(reserva -> reserva.isCaducada()).count();
	}

	public double tiempoUsoHoy() {
		LocalDateTime today = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
		return alquileres.stream().filter(alquiler -> alquiler.getInicio().isAfter(today))
				.mapToDouble(alquiler -> alquiler.tiempo()).sum();
	}

	public double tiempoUsoSemana() {
		LocalDateTime hace1semana = LocalDateTime.now().minusWeeks(1);
		return alquileres.stream().filter(alquiler -> alquiler.getInicio().isAfter(hace1semana))
				.mapToDouble(alquiler -> alquiler.tiempo()).sum();
	}

	public boolean superaTiempo() {
		return tiempoUsoHoy() >= 60.0 || tiempoUsoSemana() >= 180.0;
	}

	public Reserva reservaActiva() {
		return reservas.stream()
				.filter(reserva -> reserva.activa())
				.findFirst()
				.orElse(null);
	}
	
	public Alquiler alquiler() {
		return alquileres.stream()
				.filter(alquiler -> alquiler.activo())
				.findFirst()
				.orElse(null);
	}
	
	public boolean bloqueado() {
		return reservasCaducadas() >= 3;
	}

}
