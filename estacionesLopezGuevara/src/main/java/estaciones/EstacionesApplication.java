package estaciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"estaciones", "rabbitmq"})
public class EstacionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstacionesApplication.class, args);
	}

}
