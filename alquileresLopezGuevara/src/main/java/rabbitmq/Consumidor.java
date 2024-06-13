package rabbitmq;

import java.io.IOException;
import java.util.Map;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Consumidor {
	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUri("amqps://hdmisobs:whTYnvtM3LZ-btEaPMLuPwTvgd8HYp0X@rat.rmq2.cloudamqp.com/hdmisobs");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		// Declaracion de cola y enlace con el exchange
		final String exchange = "citybike";
		final String queue = "citybike-estaciones";
		final String bindingKey = "citybike.alquileres.#";

		boolean durable = true;
		boolean exclusive = false;
		boolean autodelete = false;
		Map<String, Object> properties = null; // Sin propiedades
		channel.queueDeclare(queue, durable, exclusive, autodelete, properties);
		channel.queueBind(queue, exchange, bindingKey);

		// Configuracion del consumidor
		boolean autoAck = false;
		String cola = "citybike-alquileres";
		String etiquetaConsumidor = "consumidor";

		// Consumidor push

		channel.basicConsume(cola, autoAck, etiquetaConsumidor, new DefaultConsumer(channel) {

			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				String routingKey = envelope.getRoutingKey();
				String contentType = properties.getContentType();
				long deliveryTag = envelope.getDeliveryTag();
				String contenido = new String(body);
				System.out.println(routingKey);
				System.out.println(contenido);
				
				// Confirma el procesamiento
				channel.basicAck(deliveryTag, false);
			}
		});
		
		System.out.println("consumidor esperando");

	}
}
