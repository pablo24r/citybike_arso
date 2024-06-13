package rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PublicadorEventos {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void emitirEvento(String evento, Object mensaje) {
		rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, evento, mensaje);
	}

}
