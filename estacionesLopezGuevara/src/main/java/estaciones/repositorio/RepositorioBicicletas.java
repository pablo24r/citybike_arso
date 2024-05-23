package estaciones.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import estaciones.modelo.Bicicleta;

@NoRepositoryBean
public interface RepositorioBicicletas extends CrudRepository<Bicicleta, String>, PagingAndSortingRepository<Bicicleta, String> {

}
