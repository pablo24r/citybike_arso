package alquileres.repositorio;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import alquileres.modelo.Usuario;
import repositorio.RepositorioMongoDB;

public class RepositorioUsuariosMongoDB extends RepositorioMongoDB<Usuario> {

	String connectionString = "mongodb+srv://arso:arso@cluster0.7z0ykhs.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";

	MongoClient mongoClient = MongoClients.create(connectionString); // cadena de conexión
	MongoDatabase database = mongoClient.getDatabase("Cluster0"); // nombre bases de datos
	MongoCollection<Document> coleccion = database.getCollection("editorial"); // nombre colección

	public RepositorioUsuariosMongoDB() {
	}

	public void guardarUsuario(Usuario usuario) {
		Document usuarioDocument = new Document("id", usuario.getId()).append("reservas", usuario.getReservas())
				.append("alquileres", usuario.getAlquileres());
		coleccion.insertOne(usuarioDocument);
	}

}
