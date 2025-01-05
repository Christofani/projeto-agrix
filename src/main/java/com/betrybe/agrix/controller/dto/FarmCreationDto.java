package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.entity.Person;

/**
 * The type Farm creation dto.
 */
public record FarmCreationDto(
        String name,
        Double size,
        Long personId // Adicionado o campo personId
) {
  /**
   * To entity farm.
   *
   * @return the farm
   */
  public Farm toEntity() {
    Farm farm = new Farm(name, size);
    if (personId != null) {
      Person person = new Person();
      person.setId(personId); // Apenas define o ID, evitando carregamento desnecessário
      farm.setPerson(person);
    }
    return farm;
  }
}
