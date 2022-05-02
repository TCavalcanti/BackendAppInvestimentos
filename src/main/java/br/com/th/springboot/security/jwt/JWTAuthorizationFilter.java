package br.com.th.springboot.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";

    private String secret;

    public JWTAuthorizationFilter(String secret) {
        this.secret = secret;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            // System.out.println("############ " + request.getRequestURI());
            // aqui vou excluir as rotas publicas no caso a de login
            if (!request.getRequestURI().equals("/api/login")) {

                String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");

                Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(jwtToken).getBody();

                if (claims.getSubject() != null) {
                    setUpSpringAuthentication(claims);
                } else {
                    SecurityContextHolder.clearContext();
                }

            }

            filterChain.doFilter(request, response);

        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
            return;
        }

    }

    /**
     * Authentication method in Spring flow
     * 
     * @param claims
     */
    private void setUpSpringAuthentication(Claims claims) {
        @SuppressWarnings("unchecked")
        // List<String> authorities = (List) claims.get("authorities");

        // UsernamePasswordAuthenticationToken auth = new
        // UsernamePasswordAuthenticationToken(claims.getSubject(), null,
        // authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
                null);

        // System.out.println("###### setUpSpringAuthentication:"+auth.getName());

        SecurityContextHolder.getContext().setAuthentication(auth);

    }

}
