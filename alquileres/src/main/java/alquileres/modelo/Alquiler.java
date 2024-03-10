package alquileres.modelo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonProperty;

public class Alquiler {

	@BsonProperty(value = "idBicicleta")
	private String idBicicleta;
	@BsonProperty(value = "inicio")
	private LocalDateTime inicio;
	@BsonProperty(value = "fin")
	private LocalDateTime fin;

	public Alquiler() {

	}

	public Alquiler(String idBicicleta, LocalDateTime inicio, LocalDateTime fin) {
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

	public Document toDocument() {
		Document document = new Document();
		document.append("idBicicleta", idBicicleta);
		document.append("inicio", inicio);
		document.append("fin", fin);
		return document;
	}

	public static Alquiler fromDocument(Document document) {
		Alquiler alquiler = new Alquiler();
		alquiler.setIdBicicleta(document.getString("idBicicleta"));
		alquiler.setInicio(document.get("inicio", LocalDateTime.class));
		alquiler.setFin(document.get("fin", LocalDateTime.class));

		return alquiler;
	}

	@Override
	public String toString() {
		return "Alquiler [idBicicleta=" + idBicicleta + ", inicio=" + inicio + ", fin=" + fin + "]";
	}

}
