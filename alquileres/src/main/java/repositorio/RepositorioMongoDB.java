package repositorio;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;

import alquileres.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class RepositorioMongoDB<T extends Identificable> implements Repositorio<T, String> {

	MongoCollection<T> coleccion;

	public RepositorioMongoDB() {
		String connectionString = "mongodb+srv://pabloraullopezmartinez:ARSO2024@clusterarso.w0erjqo.mongodb.net/?retryWrites=true&w=majority&appName=ClusterARSO";
		MongoClient mongoClient = MongoClients.create(connectionString);
		MongoDatabase database = mongoClient.getDatabase("ClusterARSO");

		CodecRegistry defaultCodecRegistry = CodecRegistries.fromRegistries(
				MongoClientSettings.getDefaultCodecRegistry(),
				CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));

		coleccion = (MongoCollection<T>) database.getCollection("usuario", Usuario.class)
				.withCodecRegistry(defaultCodecRegistry);

	}

	public Document newDocument(T entity) {
		Document d = new Document();
		d.append("usuario", entity.getId());
		if (entity.getClass().equals(Usuario.class)) {
			d.append("reservas", ((Usuario) entity).getReservas());
			d.append("alquileres", ((Usuario) entity).getAlquileres());
		}

		return d;
	}

	public static String insertOneDocument(MongoCollection<Document> coleccion, Document d) {
		InsertOneResult result = coleccion.insertOne(d);

		if (result.getInsertedId() != null)
			return result.getInsertedId().asObjectId().getValue().toString();
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String add(T entity) throws RepositorioException {
		return insertOneDocument((MongoCollection<Document>) coleccion, newDocument(entity));
	}

	@Override
	public void update(T entity) throws RepositorioException, EntidadNoEncontrada {
		// Filtro para buscar el documento por su ID
		ObjectId objectId;
		objectId = new ObjectId(entity.getId());
		Bson filter = Filters.eq("_id", objectId);
		// Reemplaza un documento en la colección según el filtro especificado
		UpdateResult result = coleccion.replaceOne(filter, entity);
		// Si no se encontro el documento, lanzar excepción
		if (result.getMatchedCount() == 0) {
			throw new EntidadNoEncontrada("No se encontró el documento con ID: " + entity.getId());
		}
	}

	@Override
	public void delete(T entity) throws RepositorioException, EntidadNoEncontrada {
		// Filtro para buscar el documento por su ID
		ObjectId objectId;
		objectId = new ObjectId(entity.getId());
		Bson filter = Filters.eq("_id", objectId); // Eliminar el documento
		DeleteResult result = coleccion.deleteOne(filter);
		// Si no se eliminó el documento, lanzar excepción
		if (result.getDeletedCount() == 0) {
			throw new EntidadNoEncontrada("No se encontró el documento con ID: " + entity.getId());
		}
	}

	@Override
	public T getById(String id) throws RepositorioException, EntidadNoEncontrada {
		// Convertir la cadena de texto 'id' en un ObjectId
		ObjectId objectId;
		try {
			objectId = new ObjectId(id);
		} catch (IllegalArgumentException e) {
			throw new EntidadNoEncontrada("El ID proporcionado no es válido: " + id);
		}
		// Filtro para buscar el documento por su ID
		Bson filter = Filters.eq("_id", objectId);
		// buscar el primer documento que satisfaga el filtro en este caso al buscar por
		// ID solo deberia haber un match
		T entity = coleccion.find(filter).first();
		// Si no se encontró el documento, lanzar excepción
		if (entity == null) {
			throw new EntidadNoEncontrada("No se encontró el documento con ID: " + id);
		}
		return entity;
	}

	@Override
	public java.util.List<T> getAll() throws RepositorioException {
		// Lista para almacenar los documentos
		List<T> entities = new ArrayList<>();
		// Obtener un cursor a todos los documentos
		MongoCursor<T> cursor = coleccion.find().iterator();
		try {
			// Iterar sobre el cursor para obtener cada documento
			while (cursor.hasNext()) {
				T entity = cursor.next();
				entities.add(entity);
			}
		} finally {
			// Cerrar el cursor para liberar recursos
			cursor.close();
		}
		return entities;
	}

	@Override
	public java.util.List<String> getIds() throws RepositorioException {
		// Implementar la lógica para obtener todos los IDs de la base de datos MongoDB
		// ...
		return null;
	}
}
