package estaciones.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import estaciones.modelo.Estacion;

public interface RepositorioEstacionesMongo 
	extends RepositorioEstaciones, MongoRepository<Estacion, String> {
	
}
