package com.example.web.service.impl;

import com.example.web.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl implements JwtService {

  //todo move it to application properties
  private static final String SECRET_KEY =
      "46294A404E635266556A586E327235753878214125442A472D4B615064536756";

  @Override
  public String extractUsername(String jwtToken) {
    return extractClaim(jwtToken, Claims::getSubject);
  }

  public <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(jwtToken);
    return claimsResolver.apply(claims);
  }

  @Override
  public String generateToken(UserDetails userDetails) {
    //generate no extra claims since the HashMap is empty
    return generateToken(new HashMap<>(), userDetails);
  }

  @Override
  public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
    //adding extra claims with this method
    return Jwts
        .builder()
        .setClaims(extraClaims)
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60))
        .signWith(SignatureAlgorithm.HS256, getSignInKey())
        .compact();
  }

  @Override
  public Claims extractAllClaims(String jwtToken) {
    return Jwts
        .parser()
        .setSigningKey(getSignInKey())
        .parseClaimsJws(jwtToken)
        .getBody();
  }

  @Override
  public boolean isTokenValid(String jwtToken, UserDetails userDetails) {
    //validate if token is the same within the user.
    final String username = extractUsername(jwtToken);

    return (username.equals(userDetails.getUsername())) && !isTokenExpired(jwtToken);

  }

  @Override
  public boolean isTokenExpired(String jwtToken) {
    return extractExpiration(jwtToken).before(new Date());
  }

  @Override
  public Date extractExpiration(String jwtToken) {
    return extractClaim(jwtToken, Claims::getExpiration);
  }

  @Override
  public Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
