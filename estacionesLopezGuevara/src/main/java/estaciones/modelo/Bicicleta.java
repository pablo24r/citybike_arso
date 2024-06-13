package estaciones.modelo;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import repositorio.Identificable;

@Document(collection = "bicicletas")
public class Bicicleta implements Identificable {
	@Id
	private String id;
	private String modelo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime fechaAlta;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime fechaBaja;
	private boolean disponible;
	private String idEstacion;
	
	
	
	public Bicicleta(String modelo) {
		super();
		this.modelo = modelo;
	}
	
	public Bicicleta() {
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public LocalDateTime getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(LocalDateTime fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public boolean isDisponible() {
		return disponible;
	}
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}


	public LocalDateTime getFechaBaja() {
		return fechaBaja;
	}


	public void setFechaBaja(LocalDateTime fechaBaja) {
		this.fechaBaja = fechaBaja;
	}


	public String getIdEstacion() {
		return idEstacion;
	}


	public void setIdEstacion(String idEstacion) {
		this.idEstacion = idEstacion;
	}
	
	public static BicicletaDTO toDTO (Bicicleta bici) {
		return new BicicletaDTO(bici.getId(), bici.getModelo(), bici.isDisponible(), bici.getIdEstacion());
	}
	
}
