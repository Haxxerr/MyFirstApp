package com.example.NewProject.security.jwt;

import com.example.NewProject.security.service.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;


@Component
public class JwtUtils {
  private static final Logger log = LoggerFactory.getLogger(JwtUtils.class);

  private static final String AUTHORITIES_KEY = "auth";

  @Value("${spring.security.jwtSecret}")
  private String jwtSecret;

  @Value("${spring.security.jwtExpirationMs}")
  private Long jwtExpirationMs;

  public String generateJwtToken(Authentication authentication) {

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    String authorities = authentication.getAuthorities().stream()
      .map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

    long now = (new Date()).getTime();
    Date validity = new Date(now + jwtExpirationMs);

    return Jwts.builder()
      .setSubject(userDetails.getUsername())
      .claim(AUTHORITIES_KEY, authorities)
      .setIssuedAt(new Date())
      .setExpiration(validity)
      .signWith(key(), SignatureAlgorithm.HS512)
      .compact();
  }

  private Key key() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
  }

  public String getUserNameFromJwtToken(String token) {
    return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody().getSubject();
  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
      return true;
    } catch (MalformedJwtException e) {
      log.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      log.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      log.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      log.error("JWT claims string is empty: {}", e.getMessage());
    }

    return false;
  }
}
