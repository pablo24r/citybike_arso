package alquileres.servicio;

public interface IServicioEventos {
    void publicarEvento(String routingKey, String id, String id2) throws Exception;
    void suscribirseEvento(String routingKey) throws Exception ;
}
