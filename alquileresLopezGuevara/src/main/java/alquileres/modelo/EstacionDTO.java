package alquileres.modelo;

public class EstacionDTO {
    private String id;
    private String nombre;
	private int numPuestos;
	private String direccionPostal;
	private String coordenadas;
	
    // Constructor, getters y setters
	public EstacionDTO() {
	    // Constructor sin argumentos
	}
    public EstacionDTO(String id, String nombre, int numPuestos, String direccionPostal, String coordenadas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.numPuestos = numPuestos;
		this.direccionPostal = direccionPostal;
		this.coordenadas = coordenadas;
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
    
    
}