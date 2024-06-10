package pasarela.security;

import java.io.IOException;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class SecuritySuccessHandler implements AuthenticationSuccessHandler {
	
	 @Autowired
	    private RestTemplate restTemplate;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException {
		DefaultOAuth2User usuario = (DefaultOAuth2User) authentication.getPrincipal();
		
		System.out.println(usuario.getName());
		String login = usuario.getAttribute("login");
		System.out.println(login);
		
		Map<String, Object> claims = fetchUserInfo(usuario);
		
        // URL del endpoint
        String url = "http://localhost:5000/api/usuarios/OAuth2";

        // Crea el objeto RestTemplate
        RestTemplate restTemplate = new RestTemplate();

		
		
		if (claims != null) {
			Date caducidad = Date.from(
					Instant.now()
					.plusSeconds(3600)); // 1 hora de validez
			
			// Genera una clave segura para el algoritmo HS256
			SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
			
			String token = Jwts.builder()
					.setClaims(claims)
					.signWith(secretKey)
					.setExpiration(caducidad)
					.compact();
			System.out.println(token);
            
			response.getWriter().append(token);
			
		} else {
			// notifica que no está autorizado en la aplicación
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().append("Unauthorized: Información de usuario incorrecta.");
		}
	}

	private Map<String, Object> fetchUserInfo(DefaultOAuth2User usuario) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("nombre", "pablo");
		claims.put("rol", "gestor");
		
		return claims;
	}
}