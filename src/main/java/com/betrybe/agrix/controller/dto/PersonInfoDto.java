package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.*;

/**
 * The type Person info dto.
 */
public record PersonInfoDto(Long personId, String username) {
  /**
   * From entity person info dto.
   *
   * @param person the person
   * @return the person info dto
   */
  public static PersonInfoDto fromEntity(Person person) {
    return new PersonInfoDto(person.getId(), person.getUsername());
  }
}
