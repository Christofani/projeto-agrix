package com.betrybe.agrix.advice;

import com.auth0.jwt.exceptions.*;
import com.betrybe.agrix.exception.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 * The type Global controller advice.
 */
@ControllerAdvice
public class GlobalControllerAdvice {

  /**
   * Handle not found exception response entity.
   *
   * @param exception the exception
   * @return the response entity
   */
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<String> handleNotFoundException(NotFoundException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
  }

  /**
   * Handle token expired exception response entity.
   *
   * @param exception the exception
   * @return the response entity
   */
  @ExceptionHandler(TokenExpiredException.class)
  public ResponseEntity<String> handleTokenExpiredException(TokenExpiredException exception) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token expirado. Por favor, faça login novamente.");
  }

  /**
   * Handle JWT verification exception response entity.
   *
   * @param exception the exception
   * @return the response entity
   */
  @ExceptionHandler(JWTVerificationException.class)
  public ResponseEntity<String> handleJWTVerificationException(JWTVerificationException exception) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido.");
  }

  /**
   * Handle access denied exception response entity.
   *
   * @param exception the exception
   * @return the response entity
   */
  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException exception) {
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
  }

}
