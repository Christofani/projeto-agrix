package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.*;
import com.betrybe.agrix.security.*;

/**
 * The type Person creation dto.
 */
public record PersonCreationDto(
        String username,
        String password,
        Role role
        ) {
  /**
   * To entity person.
   *
   * @return the person
   */
  public Person toEntity() {
    return new Person(username, password, role);
  }
}
