package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.*;

public record FarmCreationDto(String name, Double size) {
  public Farm toEntity() {
    return new Farm(name, size);
  }
}
