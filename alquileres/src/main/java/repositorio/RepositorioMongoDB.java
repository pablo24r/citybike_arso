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

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import alquileres.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class RepositorioMongoDB<T extends Identificable> implements Repositorio<T, String> {

	protected MongoCollection<T> coleccion;

	public RepositorioMongoDB(String connectionString, String databaseName, String collectionName,
			Class<T> entityType) {
		MongoClient mongoClient = MongoClients.create(connectionString);
		MongoDatabase database = mongoClient.getDatabase(databaseName);

		CodecRegistry defaultCodecRegistry = CodecRegistries.fromRegistries(
				MongoClientSettings.getDefaultCodecRegistry(),
				CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));

		coleccion = database.getCollection(collectionName, entityType).withCodecRegistry(defaultCodecRegistry);
	}

	public Document newDocument(T entity) {
		Document d = new Document();
		d.append("usuario", entity.getId());

		d.append("reservas", ((Usuario) entity).getReservas());

		d.append("alquileres", ((Usuario) entity).getAlquileres());

		d.append("numero", ((Usuario) entity).getNumero());
		System.out.println("Documento: " + d.toString());
	

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
		ObjectId objectId = new ObjectId(entity.getId());
		Bson filter = Filters.eq("_id", objectId);
		UpdateResult result = coleccion.replaceOne(filter, entity);

		if (result.getMatchedCount() == 0) {
			throw new EntidadNoEncontrada("No se encontró el documento con ID: " + entity.getId());
		}
	}

	@Override
	public void delete(T entity) throws RepositorioException, EntidadNoEncontrada {
		ObjectId objectId = new ObjectId(entity.getId());
		Bson filter = Filters.eq("_id", objectId);
		DeleteResult result = coleccion.deleteOne(filter);

		if (result.getDeletedCount() == 0) {
			throw new EntidadNoEncontrada("No se encontró el documento con ID: " + entity.getId());
		}
	}

	@Override
	public T getById(String id) throws RepositorioException, EntidadNoEncontrada {
		ObjectId objectId;
		try {
			objectId = new ObjectId(id);
		} catch (IllegalArgumentException e) {
			throw new EntidadNoEncontrada("El ID proporcionado no es válido: " + id);
		}
		Bson filter = Filters.eq("_id", objectId);
		T entity = coleccion.find(filter).first();

		if (entity == null) {
			throw new EntidadNoEncontrada("No se encontró el documento con ID: " + id);
		}
		return entity;
	}

	@Override
	public List<T> getAll() throws RepositorioException {
		List<T> entities = new ArrayList<>();
		MongoCursor<T> cursor = coleccion.find().iterator();

		try {
			while (cursor.hasNext()) {
				T entity = cursor.next();
				entities.add(entity);
			}
		} finally {
			cursor.close();
		}
		return entities;
	}

	@Override
	public List<String> getIds() throws RepositorioException {
		// Implementa la lógica para obtener todos los IDs de la base de datos MongoDB
		// ...
		return null;
	}
}
