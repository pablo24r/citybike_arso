package alquileres.tests;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;

public class LimpiarRepositorioUsuarios {
	public static void main(String[] args) {
		// Borrar la coleccion
		MongoClient mongoClient = MongoClients.create(
				"mongodb+srv://pabloraullopezmartinez:ARSO2024@clusterarso.w0erjqo.mongodb.net/ClusterARSO?retryWrites=true&w=majority&appName=ClusterARSO");
		MongoCollection<org.bson.Document> coleccion = mongoClient.getDatabase("ClusterARSO").getCollection("usuarios");

		coleccion.drop();
		System.out.println("Coleccion borrada.");
	}
}
