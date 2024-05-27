package rabbitmq;

import java.util.Optional;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import estaciones.modelo.Bicicleta;
import estaciones.modelo.Estacion;
import estaciones.servicio.ServicioEstaciones;

@Component
public class EventListener {
	
    private final ServicioEstaciones servicioEstaciones;
    
    @Autowired
    public EventListener(ServicioEstaciones servicioEstaciones) {
        this.servicioEstaciones = servicioEstaciones;
    }
	
	@RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
	public void handleEvent(Message mensaje) throws JsonMappingException, JsonProcessingException {
		System.out.println("Mensaje recibido: " + mensaje);
		String body = new String(mensaje.getBody());
		System.out.println(body);
		System.out.println("Evento: " + mensaje.getMessageProperties().getReceivedRoutingKey());
		String evento = mensaje.getMessageProperties().getReceivedRoutingKey(); 
		switch(evento) {
		case "citybike.alquileres.bicicleta-alquilada":{
	        ObjectMapper mapper = new ObjectMapper();
	        JsonNode rootNode = mapper.readTree(body);
	        
	        String id = rootNode.get("id").asText();
	        Optional<Bicicleta> bici = servicioEstaciones.getRepoBicis().findById(id);
	        System.out.println("Bicicleta no disponible: " + bici.get().getId());
	        bici.get().setDisponible(false);
	        servicioEstaciones.getRepoBicis().save(bici.get());
	        
	        String idEstacion = bici.get().getIdEstacion();
	        System.out.println("ID ESTACION" + idEstacion);
	        Optional<Estacion> estacion = servicioEstaciones.getRepoEstaciones().findById(idEstacion);
		    // Eliminar la bicicleta de la lista de bicicletas de la estación
		    estacion.get().getListadoBicicletas().removeIf(b -> b.getId().equals(bici.get().getId()));
		    estacion.get().setNumPuestos(estacion.get().getNumPuestos()+1);

		    // Guardar la estación actualizada
		    servicioEstaciones.getRepoEstaciones().save(estacion.get());
			break;
		}
		case "citybike.alquileres.bicicleta-alquiler-concluido":{
	        ObjectMapper mapper = new ObjectMapper();
	        JsonNode rootNode = mapper.readTree(body);
	        
	        String idBici = rootNode.get("id").asText();
	        String idEstacion = rootNode.get("id2").asText();
	        servicioEstaciones.estacionarBicicleta(idEstacion, idBici);
			break;
		}
		default:
			break;
		}

	}
}
