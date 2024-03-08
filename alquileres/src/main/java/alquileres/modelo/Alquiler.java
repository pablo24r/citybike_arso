package alquileres.modelo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.bson.BsonType;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.codecs.pojo.annotations.BsonRepresentation;
import org.bson.types.ObjectId;

public class Alquiler {

	@BsonId
	@BsonRepresentation(BsonType.OBJECT_ID)
	private String id;

	@BsonProperty(value = "idBicicleta")
	private String idBicicleta;
	@BsonProperty(value = "inicio")
	private LocalDateTime inicio;
	@BsonProperty(value = "fin")
	private LocalDateTime fin;

	public Alquiler() {

	}

	public Alquiler(String idBicicleta, LocalDateTime inicio, LocalDateTime fin) {
        this.id = new ObjectId().toString(); // Asignar un nuevo ObjectId como identificador
		this.idBicicleta = idBicicleta;
		this.inicio = inicio;
		this.fin = fin;
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
		document.append("id", id);
		document.append("idBicicleta", idBicicleta);
		document.append("inicio", inicio);
		document.append("fin", fin);
		return document;
	}
	
    public static Alquiler fromDocument(Document document) {
        Alquiler alquiler = new Alquiler();
        alquiler.setId(document.getObjectId("_id").toString());
        alquiler.setIdBicicleta(document.getString("idBicicleta"));
        alquiler.setInicio(document.get("inicio", LocalDateTime.class));
        alquiler.setFin(document.get("fin", LocalDateTime.class));

        return alquiler;
    }
	@Override
	public String toString() {
		return "Alquiler [id=" + id + ", idBicicleta=" + idBicicleta + ", inicio=" + inicio + ", fin=" + fin + "]";
	}

}
