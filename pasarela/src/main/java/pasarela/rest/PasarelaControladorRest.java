package pasarela.rest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@RestController
@RequestMapping("/auth")
public class PasarelaControladorRest {



		private Retrofit retrofit = new Retrofit.Builder().baseUrl("http://localhost:5000/api/usuarios/")
				.addConverterFactory(ScalarsConverterFactory.create())
				.addConverterFactory(GsonConverterFactory.create()).build();
		ClienteUsuariosApi usuariosService = retrofit.create(ClienteUsuariosApi.class);
	

	
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestParam String nick, @RequestParam String password)
			throws Exception {
		Call<Map<String, Object>> call = usuariosService.verificarCredenciales(nick, password);
		retrofit2.Response<Map<String, Object>> response = call.execute();

		if (!response.isSuccessful() || response.body() == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		Map<String, Object> userClaims = response.body();
		System.out.println(userClaims.toString());
		
	
		String jwt = Jwts.builder().setSubject(nick)
				.claim("id", userClaims.get("id"))
				.claim("nombre", userClaims.get("nombre"))
				.claim("rol", userClaims.get("rol"))
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hora de expiración
				.signWith(SignatureAlgorithm.HS256, "secreto")
				.compact();

		Map<String, Object> result = new HashMap<>();
		result.put("token", jwt);
		result.put("id", userClaims.get("id"));
		result.put("nombre", userClaims.get("nombre"));
		result.put("rol", userClaims.get("rol"));

		return ResponseEntity.ok(result);

	}

	@PostMapping("/oauth2")
	public ResponseEntity<?> loginOAuth2() throws Exception {

		return ResponseEntity.ok().build();

	}

	@GetMapping("/prueba/{id}")
	public ResponseEntity<?> prueba(@PathVariable String id) {
		return ResponseEntity.ok().body(("{\"message\": \"Prueba exitosa\", \"userId\": \"" + id + "\"}"));
	}
}


