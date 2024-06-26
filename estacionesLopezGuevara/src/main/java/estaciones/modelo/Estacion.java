package estaciones.modelo;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import repositorio.Identificable;

@Document(collection = "estaciones")
public class Estacion implements Identificable {
	@Id
	private String id;
	private String nombre;
	private int numPuestos;
	private String direccionPostal;
	private String coordenadas;
	private LocalDateTime fechaAlta;
	private List<Bicicleta> listadoBicicletas;
	
	
	
	public Estacion(String nombre, int numPuestos, String direccionPostal, String coordenadas) {
		super();
		this.nombre = nombre;
		this.numPuestos = numPuestos;
		this.direccionPostal = direccionPostal;
		this.coordenadas = coordenadas;
		this.listadoBicicletas = new LinkedList<Bicicleta>();
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public int getNumPuestos() {
		return numPuestos;
	}



	public void setNumPuestos(int numPuestos) {
		this.numPuestos = numPuestos;
	}



	public String getDireccionPostal() {
		return direccionPostal;
	}



	public void setDireccionPostal(String direccionPostal) {
		this.direccionPostal = direccionPostal;
	}



	public String getCoordenadas() {
		return coordenadas;
	}



	public void setCoordenadas(String coordenadas) {
		this.coordenadas = coordenadas;
	}



	public LocalDateTime getFechaAlta() {
		return fechaAlta;
	}



	public void setFechaAlta(LocalDateTime fechaAlta) {
		this.fechaAlta = fechaAlta;
	}



	public List<Bicicleta> getListadoBicicletas() {
		return listadoBicicletas;
	}



	public void setListadoBicicletas(List<Bicicleta> listadoBicicletas) {
		this.listadoBicicletas = listadoBicicletas;
	}
	
	public static EstacionDTO toDTO (Estacion e) {
		return new EstacionDTO(e.getId(), e.getNombre(), e.getNumPuestos(), e.getDireccionPostal(), e.getCoordenadas());
	}

	

}
