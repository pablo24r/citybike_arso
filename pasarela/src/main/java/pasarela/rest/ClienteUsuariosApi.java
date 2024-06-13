package pasarela.rest;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ClienteUsuariosApi {

    @FormUrlEncoded
    @POST("login")
    Call<Map<String, Object>>verificarCredenciales(
        @Query("nick") String nick,
        @Field("password") String password
    );
    
    @POST("oauth2")
    Call<Map<String, Object>>verificarOauth(
        @Query("nick") String nick);
}
