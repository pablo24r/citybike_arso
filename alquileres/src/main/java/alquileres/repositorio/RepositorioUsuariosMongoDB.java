package alquileres.repositorio;

import alquileres.modelo.Usuario;
import repositorio.RepositorioException;
import repositorio.RepositorioMongoDB;

public class RepositorioUsuariosMongoDB extends RepositorioMongoDB<Usuario> {

    public RepositorioUsuariosMongoDB() {
    }
	
    public RepositorioUsuariosMongoDB(String connectionString, String databaseName, String collectionName) {
        
    	super(connectionString, databaseName, collectionName, Usuario.class);
    }
    
    public void guardarUsuario(Usuario usuario) {
        try {
            String id = add(usuario);
            System.out.println("Usuario insertado con ID: " + id);

            // Agrega información de depuración para ver los documentos de reserva y alquiler
            System.out.println("Reservas Document List: " + usuario.getReservas().toString());
            System.out.println("Alquileres Document List: " + usuario.getAlquileres().toString());
        } catch (RepositorioException e) {
            System.out.println("Error al insertar el usuario en la base de datos: " + e.getMessage());
        }
    }
    



}
