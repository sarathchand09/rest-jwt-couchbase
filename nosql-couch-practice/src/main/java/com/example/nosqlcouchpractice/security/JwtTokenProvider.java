package com.example.nosqlcouchpractice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

  private static final String BEARER = "Bearer ";
  private static final String AUTHORIZATION = "Authorization";

  @Value("${jwt.secret-key}")
  private String secretKey;

  @Value("${jwt.expiry}")
  private long expiryInMillis;

  private UserDetailsService userDetailsService;

  public JwtTokenProvider(
      @Qualifier("customUserDetailService") UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  @PostConstruct
  private void init() {
    this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
  }

  public String createToken(String userName, List<String> roles) {
    // The JWT signature algorithm we will be using to sign the token
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    // claims
    Claims claims = Jwts.claims().setSubject(userName);
    claims.put("roles", roles);
    Date issuedAt = new Date();
    Date validity = new Date(issuedAt.getTime() + expiryInMillis);

    // We will sign our JWT with our ApiKey secret
    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);

    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(issuedAt)
        .setExpiration(validity)
        .signWith(new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName()))
        .compact();
  }

  boolean validateToken(String token) {
    try {
      Jws<Claims> claims =
          Jwts.parser()
              .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
              .parseClaimsJws(token);
      if (claims.getBody().getExpiration().before(new Date())) {
        throw new JwtException("Expired or invalid JWT token");
      }
    } catch (JwtException | IllegalArgumentException e) {
      throw new RuntimeException(e.getMessage());
    }
    return true;
  }

  String resolveToken(HttpServletRequest req) {
    String bearerToken = req.getHeader(AUTHORIZATION);
    return bearerToken != null && bearerToken.startsWith(BEARER)
        ? bearerToken.substring(BEARER.length())
        : null;
  }

  Authentication getAuthentication(String token) {
    UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  String getUsername(String token) {
    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
  }
}
