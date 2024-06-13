package pasarela.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		Claims claims = null;
		String header = request.getHeader("Authorization");
		
		if (header != null && header.startsWith("Bearer ")) {
			String token = header.replace("Bearer ", "");
			try {
				claims = Jwts.parser().setSigningKey("secreto").parseClaimsJws(token).getBody();
				
				Date expiration = claims.getExpiration();
				if (expiration.before(new Date())) {
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token caducado");
					return;
				}
			} catch (ExpiredJwtException e) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token caducado");
				return;
			} catch (Exception e) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido");
				return;
			}
		
			if(claims != null) {
				System.out.println(claims.toString());
				// Establece el contexto de seguridad
				ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	            if (claims.get("rol") != null) {
	                authorities.add(new SimpleGrantedAuthority(claims.get("rol").toString()));
	            }
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
						authorities);
				
				// Establecemos la autenticación en el contexto de seguridad
				// Se interpreta como que el usuario ha superado la autenticación
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		
		chain.doFilter(request, response);
	}
}