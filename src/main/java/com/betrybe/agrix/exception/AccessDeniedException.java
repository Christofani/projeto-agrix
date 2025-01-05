package com.betrybe.agrix.exception;

public class AccessDeniedException extends NotFoundException {

  public AccessDeniedException(String message) {
    super(message); // Passa a mensagem personalizada para a classe pai
  }

  // Constructor sem parâmetros para a mensagem padrão
  public AccessDeniedException() {
    super("Você não tem permissão para acessar este recurso.");
  }
}
