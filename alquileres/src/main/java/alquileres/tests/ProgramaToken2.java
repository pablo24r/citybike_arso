package alquileres.tests;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class ProgramaToken2 {
	public static void main(String[] args) {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqdWFuQHVtLmVzIiwicm9sZXMiOiJwcm9mZXNvciIsImV4cCI6MTcxMDA3MjMzN30.t3-2bTCE_nvdmJFkc9PeVTF0bMGU0Ivg3PAltL1vk5I";

		Claims claims = Jwts.parser().setSigningKey("secreto").parseClaimsJws(token).getBody();
		Date caducidad = claims.getExpiration();

		if (caducidad.before(new Date())) {
			System.out.println("Ha caducado");
		}
		
		System.out.println(claims);
	}
}
