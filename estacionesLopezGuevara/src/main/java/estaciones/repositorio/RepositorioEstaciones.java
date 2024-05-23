package estaciones.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import estaciones.modelo.Estacion;

@NoRepositoryBean
public interface RepositorioEstaciones extends CrudRepository<Estacion, String>, PagingAndSortingRepository<Estacion, String> {

	List<Estacion> findByNombre(String nombre);
}
