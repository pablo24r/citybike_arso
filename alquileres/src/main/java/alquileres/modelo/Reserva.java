package alquileres.modelo;

import java.time.LocalDateTime;

import org.bson.BsonType;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

public class Reserva {

	@BsonId
	@BsonRepresentation(BsonType.OBJECT_ID)
	private String id;

	@BsonProperty(value = "idBicicleta")
	private String idBicicleta;

	@BsonProperty(value = "creada")
	private LocalDateTime creada;

	@BsonProperty(value = "caducidad")
	private LocalDateTime caducidad;

	@BsonProperty(value = "caducada")
	private boolean caducada;

	public Reserva() {
	}

	public Reserva(String idBicicleta, LocalDateTime creada, LocalDateTime caducidad) {
		this.idBicicleta = idBicicleta;
		this.creada = creada;
		this.caducidad = caducidad;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Document toDocument() {
		Document document = new Document();
		document.append("id", id);
		document.append("idBicicleta", idBicicleta);
		document.append("creada", creada);
		document.append("caducidad", caducidad);
		document.append("caducada", caducada);
		return document;
	}

	@Override
	public String toString() {
		return "Reserva [id=" + id + ", idBicicleta=" + idBicicleta + ", creada=" + creada + ", caducidad=" + caducidad
				+ ", caducada=" + caducada + "]";
	}

}
