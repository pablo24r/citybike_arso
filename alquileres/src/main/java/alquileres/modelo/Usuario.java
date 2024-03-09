package alquileres.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.bson.BsonType;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.codecs.pojo.annotations.BsonRepresentation;
import org.bson.types.ObjectId;

import repositorio.Identificable;

public class Usuario implements Identificable {

	@BsonId
	@BsonRepresentation(BsonType.OBJECT_ID)
	private String id;

	@BsonProperty(value = "reservas")
	private List<Reserva> reservas = new ArrayList<Reserva>();

	@BsonProperty(value = "alquileres")
	private List<Alquiler> alquileres = new ArrayList<Alquiler>();
	

	
	public Usuario() {
        this.id = new ObjectId().toString(); // Asignar un nuevo ObjectId como identificador
	}

	public Usuario(List<Reserva> reservas, List<Alquiler> alquileres) {
        this.id = new ObjectId().toString(); // Asignar un nuevo ObjectId como identificador
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
	
	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public void setAlquileres(List<Alquiler> alquileres) {
		this.alquileres = alquileres;
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

	public Document toDocument() {
		Document document = new Document();
		document.append("id", id);
		List<Document> reservasDocument = new ArrayList<>();
		for (Reserva reserva : reservas) {
			reservasDocument.add(reserva.toDocument());
		}
		document.append("reservas", reservasDocument);
		List<Document> alquileresDocument = new ArrayList<>();
		for (Alquiler alquiler : alquileres) {
			alquileresDocument.add(alquiler.toDocument());
		}
		document.append("alquileres", alquileresDocument);
		return document;
	}

	@SuppressWarnings("unchecked")
    public static Usuario fromDocument(Document document) {
        Usuario usuario = new Usuario();
        usuario.setId(document.getObjectId("_id").toString());

        // Recuperar y establecer las listas de reservas y alquileres desde el documento
        List<Document> reservasDocument = (List<Document>) document.get("reservas");
        List<Document> alquileresDocument = (List<Document>) document.get("alquileres");

        List<Reserva> reservas = new ArrayList<>();
        List<Alquiler> alquileres = new ArrayList<>();

        // Convertir documentos a objetos Reserva y Alquiler y agregar a las listas
        if (reservasDocument != null) {
            for (Document reservaDoc : reservasDocument) {
                Reserva reserva = Reserva.fromDocument(reservaDoc);
                reservas.add(reserva);
            }
        }

        if (alquileresDocument != null) {
            for (Document alquilerDoc : alquileresDocument) {
                Alquiler alquiler = Alquiler.fromDocument(alquilerDoc);
                alquileres.add(alquiler);
            }
        }

        // Establecer las listas en el usuario
        usuario.reservas= reservas;
        usuario.alquileres= alquileres;

        return usuario;
    }

	
}
