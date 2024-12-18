package com.betrybe.agrix.service;
import com.auth0.jwt.*;
import com.auth0.jwt.algorithms.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.time.temporal.*;

@Service
public class TokenService {
  @Value("${api.security.token.secret}")
  private String secret;

  public String generateToken(String username) {
    Algorithm algorithm = Algorithm.HMAC256(secret);

    return  JWT.create()
            .withSubject(username)
            .withExpiresAt(generateExpirationDate())
            .sign(algorithm);
  }

  private Instant generateExpirationDate() {
    return  Instant.now()
            .plus(2, ChronoUnit.HOURS);
  }

  public String validateToken(String token) {
    Algorithm algorithm = Algorithm.HMAC256(secret);
    return JWT.require(algorithm)
            .build()
            .verify(token)
            .getSubject();
  }
}
