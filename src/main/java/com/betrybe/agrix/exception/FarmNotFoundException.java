package com.betrybe.agrix.exception;

public class FarmNotFoundException extends NotFoundException {
  public FarmNotFoundException() {
    super("Fazenda não encontrada!");
  }
}
