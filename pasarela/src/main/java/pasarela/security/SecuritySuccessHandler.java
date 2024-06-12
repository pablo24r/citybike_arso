package pasarela.security;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import pasarela.rest.ClienteUsuariosApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Component
public class SecuritySuccessHandler implements AuthenticationSuccessHandler {
	
	private Retrofit retrofit = new Retrofit.Builder().baseUrl("http://localhost:5000/api/usuarios/")
			.addConverterFactory(ScalarsConverterFactory.create())
			.addConverterFactory(GsonConverterFactory.create()).build();
	
	private final ClienteUsuariosApi usuariosService = retrofit.create(ClienteUsuariosApi.class);
	
	
	@SuppressWarnings("unused")
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,Authentication authentication) {
		DefaultOAuth2User usuario = (DefaultOAuth2User) authentication.getPrincipal();
 		try {
			String idGitHub = (String) usuario.getAttribute("login");
			Map<String, Object> claims = usuariosService.verificarOauth(idGitHub).execute().body();
			
			if (claims != null) {
				System.out.println(claims.toString());
				Date caducidad = Date.from(Instant.now().plusSeconds(3600));
				String token = Jwts.builder().setSubject(idGitHub)
						.claim("id", claims.get("id"))
						.claim("nombre", claims.get("nombre"))
						.claim("rol", claims.get("rol"))
						.setIssuedAt(new Date())
						.setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hora de expiración
						.signWith(SignatureAlgorithm.HS256, "secreto")
						.compact();
				try {
					
					System.out.println(claims.toString());
					
				

					Map<String, Object> result = new HashMap<>();
					result.put("token", token);
					result.put("id", claims.get("id"));
					result.put("nombre", claims.get("nombre"));
					result.put("rol", claims.get("rol"));
				    
					response.getWriter().append(result.toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuario no registrado en la aplicación.");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}