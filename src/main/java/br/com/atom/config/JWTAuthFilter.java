package br.com.atom.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.atom.services.JWTUtils;
import br.com.atom.services.OurUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {
	
	
	private JWTUtils jwtUtils;

	private OurUserDetailsService ourUserDetailsService;
	
	
	
	public JWTAuthFilter(JWTUtils jwtUtils, OurUserDetailsService ourUserDetailsService) {
		this.jwtUtils = jwtUtils;
		this.ourUserDetailsService = ourUserDetailsService;
	}



	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String autHeader = request.getHeader("Authorization");
		final String jwtToken;
		final String userEmail;
		if (autHeader == null || autHeader.isBlank()) {
			filterChain.doFilter(request, response);
			return;
		}
		jwtToken = autHeader.substring(7);
		userEmail = jwtUtils.extractUsername(jwtToken);
		
		if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = ourUserDetailsService.loadUserByUsername(userEmail);
			
			if (jwtUtils.isTokenValid(jwtToken, userDetails)) {
				SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				securityContext.setAuthentication(token);
				SecurityContextHolder.setContext(securityContext);
			}
		}
		filterChain.doFilter(request, response);
	}

}
