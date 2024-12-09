package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.*;

/**
 * The type Crop creation dto.
 */
public record CropCreationDto(
        String name,
        Double plantedArea
) {
  /**
   * To entity crop.
   *
   * @return the crop
   */
  public Crop toEntity() {
    return new Crop(name, plantedArea);
  }
}
