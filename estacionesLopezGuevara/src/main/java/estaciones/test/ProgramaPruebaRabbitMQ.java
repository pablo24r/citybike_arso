package estaciones.test;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import estaciones.EstacionesApplication;
import rabbitmq.PublicadorEventos;

public class ProgramaPruebaRabbitMQ {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(EstacionesApplication.class, args);
		
		PublicadorEventos sender = context.getBean(PublicadorEventos.class);
		
		//Bicicleta bici = new Bicicleta("modelo x44");
		String id = "123";
		sender.emitirEvento("citybike.estaciones.bicicleta-desactivada",id);
	}
}
