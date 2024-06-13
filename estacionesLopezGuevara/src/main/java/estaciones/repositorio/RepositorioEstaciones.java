package estaciones.repositorio;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import estaciones.modelo.Estacion;

@NoRepositoryBean
public interface RepositorioEstaciones extends PagingAndSortingRepository<Estacion, String> {

	List<Estacion> findByNombre(String nombre);
}
