package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.*;
import com.betrybe.agrix.security.*;

/**
 * The type Person dto.
 */
public record PersonDto(
        Long personId,
        String username,
        Role role) {

  /**
   * From entity person dto.
   *
   * @param person the person
   * @return the person dto
   */
  public static PersonDto fromEntity(Person person) {
    return new PersonDto(
            person.getId(),
            person.getUsername(),
            person.getRole()
    );
  }
}
