package retrofit.estaciones;

import alquileres.modelo.EstacionDTO;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ProgramaPrueba {

    public static void main(String[] args) {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://localhost:8080/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build();

        EstacionRestClient service = retrofit.create(EstacionRestClient.class);

        try {
            // Recuperación
            Response<EstacionDTO> responseEstacion = service.getEstacion("66507204b585b911b22d81b9").execute();
            if (responseEstacion.isSuccessful() && responseEstacion.body() != null) {
                EstacionDTO estacion = responseEstacion.body();
                System.out.println("Estación: " + estacion.getNombre());
                System.out.println("Huecos disponibles: " + estacion.getNumPuestos());
            } else {
                System.out.println("Error al obtener la estación: " + responseEstacion.errorBody().string());
            }

            Response<EstacionDTO> responseEstacionar = service.estacionarBicicleta("662f6c8b7a7ea20f9700b871", "664f446bdf767b29d692ee77").execute();
            if (responseEstacionar.isSuccessful() && responseEstacionar.body() != null) {
                EstacionDTO e2 = responseEstacionar.body();
                System.out.println("Estación 2: " + e2.getNombre());
                System.out.println("Num bicis: " + e2.getNumPuestos());
            } else {
                System.out.println("Error al estacionar la bicicleta: " + responseEstacionar.errorBody().string());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}