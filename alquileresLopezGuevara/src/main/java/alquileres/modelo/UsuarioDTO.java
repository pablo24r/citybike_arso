package alquileres.modelo;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDTO {

    private String id;
    private List<Reserva> reservas = new ArrayList<>();
    private List<Alquiler> alquileres = new ArrayList<>();

    // Constructor por defecto
    public UsuarioDTO() {}

    // Constructor que toma un objeto Usuario
    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.reservas = usuario.getReservas();
        this.alquileres = usuario.getAlquileres();
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public List<Alquiler> getAlquileres() {
        return alquileres;
    }

    public void setAlquileres(List<Alquiler> alquileres) {
        this.alquileres = alquileres;
    }
    
    // Métodos adicionales (opcional)
    @Override
    public String toString() {
        return "UsuarioDTO [id=" + id + ", reservas=" + reservas + ", alquileres=" + alquileres + "]";
    }
}
