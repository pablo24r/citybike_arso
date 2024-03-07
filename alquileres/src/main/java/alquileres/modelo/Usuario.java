package alquileres.modelo;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

import repositorio.Identificable;

public class Usuario implements Identificable {

	@BsonId
	@BsonRepresentation(BsonType.OBJECT_ID)
	private String id;

	@BsonProperty(value = "reservas")
	private List<Reserva> reservas;

	@BsonProperty(value = "alquileres")
	private List<Alquiler> alquileres;

	public Usuario() {

	}

	public Usuario(List<Reserva> reservas, List<Alquiler> alquileres) {
		this.reservas = reservas;
		this.alquileres = alquileres;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
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
		return reservas.stream().filter(reserva -> reserva.activa()).findFirst().orElse(null);
	}

	public Alquiler alquiler() {
		return alquileres.stream().filter(alquiler -> alquiler.activo()).findFirst().orElse(null);
	}

	public boolean bloqueado() {
		return reservasCaducadas() >= 3;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", reservas=" + reservas + ", alquileres=" + alquileres + "]";
	}

	/*
	 * public Document toDocument() { Document d = new Document(); d.append("id",
	 * id); d.append("reservas", getReservas()); d.append("alquileres",
	 * getAlquileres()); return d; }
	 * 
	 * public static Usuario fromDocument(Document document) { List<Reserva>
	 * reservas = (List<Reserva>) document.get("reservas"); List<Alquiler>
	 * alquileres = (List<Alquiler>) document.get("alquileres"); return new
	 * Usuario(reservas, alquileres); }
	 */

}
