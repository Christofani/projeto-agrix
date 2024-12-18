package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.*;

public record FertilizerCreationDto(
        String name,
        String brand,
        String composition
) {

  public Fertilizer toEntity() {
    return new Fertilizer(name, brand, composition);
  }
}
