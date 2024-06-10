package pasarela.security;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
/*
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String token ="";


        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);

            // Verificar y extraer la información del usuario del token JWT
            Map<String, Object> claims = Jwts.parserBuilder()
                    .setSigningKey(Keys.secretKeyFor(SignatureAlgorithm.HS256))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            // Recuperar el nombre de usuario y los roles del usuario
            String username = (String) claims.get("sub");
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            String role = (String) claims.get("rol");
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));

            // Crear una instancia de UsernamePasswordAuthenticationToken con la información del usuario
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    username, null, authorities);

            // Establecer la autenticación en el contexto de seguridad
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        // Continuar con la cadena de filtros
        chain.doFilter(request, response);
    }*/
}
