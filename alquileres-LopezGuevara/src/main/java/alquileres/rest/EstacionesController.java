package alquileres.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import alquileres.modelo.Estacion;
import alquileres.servicio.IServicioEstaciones;

@RestController
@RequestMapping("/estaciones")
public class EstacionesController {

	private IServicioEstaciones servicio;

	@Autowired
	public EstacionesController(IServicioEstaciones servicio) {
		this.servicio = servicio;
	}

	@GetMapping("/{id}") // http://localhost:8080/estaciones/66157085af32a15b30df07d2
	public Estacion getEncuestaById(@PathVariable String id) throws Exception {
		return servicio.getEstacion(id);
	}
}