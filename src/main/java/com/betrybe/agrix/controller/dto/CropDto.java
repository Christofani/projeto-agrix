package com.betrybe.agrix.controller.dto;


import com.betrybe.agrix.entity.*;
import org.springframework.cglib.core.*;

import java.time.*;

/**
 * The type Crop dto.
 */
public record CropDto(
        Long id,
        String name,
        Double plantedArea,
        LocalDate plantedDate,
        LocalDate harvestDate,
        Long farmId
) {
  /**
   * From entity crop dto.
   *
   * @param crop the crop
   * @return the crop dto
   */
  public static CropDto fromEntity(Crop crop) {
    Long farmDto = crop.getFarmId() != null ? crop.getFarmId().getId() : null;

    return new CropDto(
            crop.getId(),
            crop.getName(),
            crop.getPlantedArea(),
            crop.getPlantedDate(),
            crop.getHarvestDate(),
            farmDto
    );
  }
}
