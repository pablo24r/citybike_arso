package estaciones.auth;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.security.PermitAll;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Path("auth")
public class ControladorAuth {

	// curl -X POST http://localhost:8080/api/auth/login -H "Content-Type: application/x-www-form-urlencoded" -d "username=pedro&password=usuario"
	@POST
	@PermitAll
	@Path("/login")
	public Response login(@FormParam("username") String username, @FormParam("password") String password) {

		Map<String, Object> claims = verificarCredenciales(username, password);
		if (claims != null) {
			Date caducidad = Date.from(Instant.now().plusSeconds(3600)); // 1 hora de validez
			String token = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, "secreto")
					.setExpiration(caducidad).compact();
			return Response.ok(token + " | " + claims.get("roles")).build();
		} else {
			return Response.status(Response.Status.UNAUTHORIZED).entity("Credenciales inválidas").build();
		}
	}

	private Map<String, Object> verificarCredenciales(String username, String password) {

		Map<String, Object> claims = new HashMap<String, Object>();// el cuerpo del token
		claims.put("sub", username);
		if (password.equals("gestor")) {
			System.out.println("El usuario "+username+" tiene el rol de gestor");
			claims.put("roles", "gestor");
		} else {
			System.out.println("El usuario "+username+" tiene el rol de usuario");
			claims.put("roles", "usuario");
		}

		return claims;
	}

}
