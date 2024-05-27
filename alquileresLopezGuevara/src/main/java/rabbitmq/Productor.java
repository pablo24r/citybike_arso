package rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Productor {
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqps://hdmisobs:whTYnvtM3LZ-btEaPMLuPwTvgd8HYp0X@rat.rmq2.cloudamqp.com/hdmisobs");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        
        final String exchange = "citybike";
        final String emisor = "alquileres"; // Identificador del emisor
        final String evento = "bicicleta-alquilada"; // Identificador del evento
        
        // Construir la clave de enrutamiento
        String routingKey = String.format("citybike.%s.%s", emisor, evento);
        
        // Crear un objeto de ejemplo para enviar como mensaje
        EventoAlquiler eventoAlquiler = new EventoAlquiler("12345", "usuario123", "2024-05-27T14:00:00Z");
        
        // Convertir el objeto a JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String mensaje = objectMapper.writeValueAsString(eventoAlquiler);
        
        // Publicar el mensaje
        channel.basicPublish(exchange, routingKey, new AMQP.BasicProperties.Builder()
                .contentType("application/json")
                .build(), mensaje.getBytes());
        
        channel.close();
        connection.close();
        System.out.println("Mensaje publicado con routingKey: " + routingKey);
    }
}

// Clase de ejemplo para representar el evento de alquiler
class EventoAlquiler {
    private String idBicicleta;
    private String usuario;
    private String fechaHoraInicio;

    public EventoAlquiler(String idBicicleta, String usuario, String fechaHoraInicio) {
        this.idBicicleta = idBicicleta;
        this.usuario = usuario;
        this.fechaHoraInicio = fechaHoraInicio;
    }

    // Getters y setters

    public String getIdBicicleta() {
        return idBicicleta;
    }

    public void setIdBicicleta(String idBicicleta) {
        this.idBicicleta = idBicicleta;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(String fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }
}
