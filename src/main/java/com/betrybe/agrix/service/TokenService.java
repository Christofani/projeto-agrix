package com.betrybe.agrix.service;

import com.auth0.jwt.*;
import com.auth0.jwt.algorithms.*;
import com.auth0.jwt.exceptions.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.time.temporal.*;

@Service
public class TokenService {

  @Value("${api.security.token.secret}")
  private String secret;

  // Gera o token com expiração de 2 horas
  public String generateToken(String username) {
    Algorithm algorithm = Algorithm.HMAC256(secret);

    return JWT.create()
            .withSubject(username)
            .withExpiresAt(generateExpirationDate())
            .sign(algorithm);
  }

  // Valida o token e retorna o username se válido
  public String validateToken(String token) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      return JWT.require(algorithm)
              .build()
              .verify(token)
              .getSubject();
    } catch (TokenExpiredException e) {
      throw new RuntimeException("Token expirado. Por favor, faça login novamente.");
    } catch (JWTVerificationException e) {
      throw new RuntimeException("Token inválido.");
    }
  }

  // Define a data de expiração do token
  private Instant generateExpirationDate() {
    return Instant.now().plus(2, ChronoUnit.HOURS);
  }
}
