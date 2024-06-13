package estaciones.modelo;

public class BicicletaDTO {
	private String id;
	private String modelo;
	private boolean disponible;
	private String idEstacion;

	public BicicletaDTO(String id, String modelo, boolean disponible, String idEstacion) {
		super();
		this.id = id;
		this.modelo = modelo;
		this.disponible = disponible;
		this.idEstacion = idEstacion;
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

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public String getIdEstacion() {
		return idEstacion;
	}

	public void setIdEstacion(String idEstacion) {
		this.idEstacion = idEstacion;
	}

}
