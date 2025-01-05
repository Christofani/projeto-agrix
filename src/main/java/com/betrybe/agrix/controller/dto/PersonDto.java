package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.*;
import com.betrybe.agrix.security.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Person dto.
 */
public record PersonDto(
        Long personId,
        String username,
        Role role,
        List<FarmDto> farms) {

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
            person.getRole(),
            person.getFarms() // Converte as fazendas associadas para DTO
                    .stream()
                    .map(FarmDto::fromEntity)
                    .collect(Collectors.toList())
    );
  }
}
