package alquileres.tests;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;

public class LimpiarRepositorioUsuarios {
	public static void main(String[] args) {
		// Borrar la coleccion
		MongoClient mongoClient = MongoClients.create(
				"mongodb://arso:arso@ac-zbd3jyk-shard-00-00.rshlvvr.mongodb.net:27017,ac-zbd3jyk-shard-00-01.rshlvvr.mongodb.net:27017,ac-zbd3jyk-shard-00-02.rshlvvr.mongodb.net:27017/?ssl=true&replicaSet=atlas-145642-shard-0&authSource=admin&retryWrites=true&w=majority&appName=ClusterARSO");
		MongoCollection<org.bson.Document> coleccion = mongoClient.getDatabase("ClusterARSO").getCollection("usuarios");

		coleccion.drop();
		System.out.println("Coleccion borrada.");
	}
}
