package alquileres.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import alquileres.modelo.Estacion;

public interface RepositorioEstacionesMongo 
	extends RepositorioEstaciones, MongoRepository<Estacion, String> {
	
}
