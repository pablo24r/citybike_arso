package estaciones.servicio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import estaciones.modelo.BicicletaDTO;
import estaciones.modelo.Estacion;
import estaciones.modelo.EstacionDTO;
import repositorio.EntidadNoEncontrada;

public interface IServicioEstaciones {
	// Gestor
	public String darAltaEstacion(String nombre, int numPuestos, String dirPostal, String coordenadas);

	public String darAltaBicicleta(String modelo, String idEstacion);

	public void darBajaBicicleta(String idBici, String motivo);

	public Page<BicicletaDTO> getListadoBicicletasDTO(String idEstacion, Pageable pageable);

	// Todos los usuarios
	public Page<EstacionDTO> getListadoEstacionesDTO(Pageable pageable);

	public ResponseEntity<Object> getInfoEstacion(String idEstacion) throws EntidadNoEncontrada;

	public Page<BicicletaDTO> getBicisDisponibles(String idEstacion, Pageable pageable);

	public void estacionarBicicleta(String idEstacion, String idBicicleta);

	public Estacion getEstacion(String idEstacion) throws EntidadNoEncontrada;

	public boolean hayHuecoDisponible(String idEstacion);
}
