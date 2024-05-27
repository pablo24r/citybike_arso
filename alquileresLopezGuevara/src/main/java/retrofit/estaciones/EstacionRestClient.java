package retrofit.estaciones;

import alquileres.modelo.EstacionDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EstacionRestClient {
	
	@GET("estaciones/{id}")
	Call<EstacionDTO> getEstacion(@Path("id") String id);
	
	
	@POST("estaciones/{idEstacion}/estacionar/{idBici}")
	Call<EstacionDTO> estacionarBicicleta(@Path("idEstacion") String idEstacion, @Path("idBici") String idBici);
}
