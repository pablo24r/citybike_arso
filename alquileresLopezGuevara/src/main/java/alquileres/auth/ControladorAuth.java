package alquileres.auth;

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

	@POST
	@Path("/login")
	@PermitAll
	public Response login(@FormParam("username") String username, @FormParam("password") String password) {
		// Verificar las credenciales y emisión del token
		Map<String, Object> claims = verificarCredenciales(username, password);
		if (claims != null) {
			Date caducidad = Date.from(Instant.now().plusSeconds(3600)); // 1 hora de validez
			String token = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, "secreto")
					.setExpiration(caducidad).compact();
			return Response.ok(token).build();
		} else {
			return Response.status(Response.Status.UNAUTHORIZED).entity("Credenciales inválidas").build();
		}

	}

	private Map<String, Object> verificarCredenciales(String username, String password) {
		// Esto es ficticio, lo suyo es ir a la bbdd a comprobar las credenciales
		Map<String, Object> claims = new HashMap<String, Object>();// el cuerpo del token
		claims.put("sub", username); // Identificador del usuario
		claims.put("roles", "usuario"); // Para el control de autorización por roles
		return claims;
	}
	
	// eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqdWFuIiwicm9sZXMiOiJwcm9mZXNvciIsImV4cCI6MTcxNTUzMTg2N30.gmhqbKJ6J8NzSD5UGpm8ppRPxgO-u_RZQXiZNehy3n8
}
