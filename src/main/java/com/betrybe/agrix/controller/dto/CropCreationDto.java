package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.*;

import java.time.*;

/**
 * The type Crop creation dto.
 */
public record CropCreationDto(
        String name,
        Double plantedArea,
        LocalDate plantedDate,
        LocalDate harvestDate
) {
  /**
   * To entity crop.
   *
   * @return the crop
   */
  public Crop toEntity() {
    return new Crop(name, plantedArea, plantedDate, harvestDate);
  }
}
