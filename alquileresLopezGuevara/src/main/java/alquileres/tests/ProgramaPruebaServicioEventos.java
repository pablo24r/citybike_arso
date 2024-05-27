package alquileres.tests;

import java.time.LocalDateTime;

import alquileres.modelo.Bicicleta;
import alquileres.servicio.ServicioEventos;

public class ProgramaPruebaServicioEventos {
	public static void main(String[] args) throws Exception {
		
		ServicioEventos ser = new ServicioEventos();
		Bicicleta bici = new Bicicleta("modelo x12");
		bici.setId("662f6c7c8b431333486f6630");
		
		
		//ser.publicarEvento("bicicleta-alquiler-concluido", bici.getId(), LocalDateTime.now());
	}

}
