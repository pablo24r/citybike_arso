package alquileres.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import alquileres.modelo.Bicicleta;

@NoRepositoryBean
public interface RepositorioBicicletas extends CrudRepository<Bicicleta, String> {

}
