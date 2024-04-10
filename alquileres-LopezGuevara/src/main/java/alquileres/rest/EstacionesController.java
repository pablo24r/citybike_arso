package alquileres.rest;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import alquileres.modelo.Bicicleta;
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

	/*@GetMapping("/{idEstacion}") // http://localhost:8080/estaciones/6616cafe364b2c64b4a41338
	public Estacion getEstacionById(@PathVariable String idEstacion) throws Exception {
		return servicio.getEstacion(idEstacion);
	}*/

	@PostMapping("/{idEstacion}")
	@RolesAllowed("gestor")
	public Response altaEstacion(@PathVariable String nombre, @PathVariable int numPuestos,
			@PathVariable String dirPostal, @PathVariable String coordenadas) {
		try {
			servicio.darAltaEstacion(nombre, numPuestos, dirPostal, coordenadas);
			return Response.status(Response.Status.CREATED).entity("Se ha dado de alta la estación").build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@PostMapping("/bicicletas")
	@RolesAllowed("gestor")
	public Response altaBici(@PathVariable String modelo, @PathVariable String idEstacion) {
		try {
			servicio.darAltaBicicleta(modelo, idEstacion);
			return Response.status(Response.Status.CREATED).entity("Se ha dado de alta la bicicleta").build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@PutMapping("/bicicletas/{idBicicleta}")
	@RolesAllowed("gestor")
	public Response bajaBicicleta(@PathVariable String idBici, @PathVariable String motivo) {
		try {
			servicio.darBajaBicicleta(idBici, motivo);
			return Response.status(Response.Status.OK).entity("Se ha dado de baja la bicicleta").build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@RolesAllowed("usuario")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getListadoEstaciones() {
		try {
			List<Estacion> listado = servicio.getListadoEstaciones();
			String json = new Gson().toJson(listado);
			return Response.status(Response.Status.OK).entity(json).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@GetMapping("/{idEstacion}")
	@RolesAllowed("usuario")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getInfoEstacion(@PathVariable String idEstacion) {
		try {
			String info = servicio.getInfoEstacion(idEstacion);
			String json = new Gson().toJson(info);
			return Response.status(Response.Status.OK).entity(json).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("falla aqui").build();
		}
	}

	@GetMapping("/{idEstacion}/bicicletas")
	@RolesAllowed("usuario")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getBicisDisponibles(@PathVariable String idEstacion,
			@RequestParam(name = "type", defaultValue = "ready") String type) {
		try {
			if ("ready".equals(type)) {
				List<Bicicleta> disponibles = servicio.getBicisDisponibles(idEstacion);
				String json = new Gson().toJson(disponibles);
				return Response.status(Response.Status.OK).entity(json).build();
			} else if ("all".equals(type)) {
				// Aquí puedes tener lógica específica para el tipo "all"
				List<Bicicleta> listado = servicio.getListadoBicicletas(idEstacion);
				String json = new Gson().toJson(listado);
				return Response.status(Response.Status.OK).entity(json).build();
			} else {
				return Response.status(Response.Status.BAD_REQUEST).entity("Parámetro incorrecto").build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("ha fallado chatgpt").build();
		}
	}

	@PutMapping("/{idEstacion}/bicicletas/{idbici}")
	@RolesAllowed("usuario")
	public Response estacionarBicicleta(@PathVariable String idEstacion, @PathVariable String idBici) {
		try {
			servicio.estacionarBicicleta(idEstacion, idBici);
			return Response.status(Response.Status.OK).entity("Bicicleta estacionada").build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

}