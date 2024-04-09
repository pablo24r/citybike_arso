package alquileres.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import alquileres.modelo.Estacion;

@NoRepositoryBean
public interface RepositorioEstaciones extends CrudRepository<Estacion, String> {

	List<Estacion> findByNombre(String nombre);
}
