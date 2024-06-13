package alquileres.tests;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class ProgramaToken1 {

	public static void main(String[] args) {

		Map<String, Object> claims = new HashMap<String, Object>();// el cuerpo del token
		claims.put("sub", "juan@um.es");
		claims.put("roles", "profesor");

		Date caducidad = Date.from(Instant.now().plusSeconds(3600)); // 1 hora de validez
		String token = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, "secreto")
				.setExpiration(caducidad).compact();
		
		System.out.println(token);
	}

}
