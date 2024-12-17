package com.betrybe.agrix.exception;

public class FertilizerNotFoundException extends NotFoundException {
  public FertilizerNotFoundException() {
    super("Fertilizante não encontrado");
  }
}
