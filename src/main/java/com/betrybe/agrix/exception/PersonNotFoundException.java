package com.betrybe.agrix.exception;

public class PersonNotFoundException extends NotFoundException {
  public PersonNotFoundException() {
    super("Pessoa não encontrada !");
  }
}
