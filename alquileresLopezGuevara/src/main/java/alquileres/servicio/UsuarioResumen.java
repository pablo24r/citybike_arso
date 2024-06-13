package alquileres.servicio;

import java.util.ArrayList;
import java.util.List;

import alquileres.modelo.Alquiler;
import alquileres.modelo.Reserva;

public class UsuarioResumen {

	private String id;
	private List<Reserva> reservas = new ArrayList<Reserva>();
	private List<Alquiler> alquileres = new ArrayList<Alquiler>();

	public UsuarioResumen(String id, List<Reserva> reservas, List<Alquiler> alquileres) {
		super();
		this.id = id;
		this.reservas = reservas;
		this.alquileres = alquileres;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public List<Alquiler> getAlquileres() {
		return alquileres;
	}

	public void setAlquileres(List<Alquiler> alquileres) {
		this.alquileres = alquileres;
	}

	@Override
	public String toString() {
		return "UsuarioResumen [id=" + id + ", reservas=" + reservas + ", alquileres=" + alquileres + "]";
	}

}
