package alquileres.modelo;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;



public class Estacion  {
	private String id;
	private String nombre;
	private int numPuestos;
	private String direccionPostal;
	private String coordenadas;
	private LocalDateTime fechaAlta;
	private List<Bicicleta> listadoBicicletas;
	
	public Estacion() {
	    // Constructor sin argumentos
	}
	
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

	

}
