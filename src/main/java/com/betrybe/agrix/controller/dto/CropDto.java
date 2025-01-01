package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.*;
import java.time.*;
import java.util.*;
import java.util.stream.*;

/**
 * The type Crop dto.
 */
public record CropDto(
        Long id,
        String name,
        Double plantedArea,
        LocalDate plantedDate,
        LocalDate harvestDate,
        Long farmId,
        List<FertilizerDto> fertilizers // Adicionado
) {
  /**
   * From entity crop dto.
   *
   * @param crop the crop
   * @return the crop dto
   */
  public static CropDto fromEntity(Crop crop) {
    Long farmDto = crop.getFarmId() != null ? crop.getFarmId().getId() : null;

    // Mapeia os fertilizantes da entidade para DTOs
    List<FertilizerDto> fertilizerDtos = crop.getFertilizers() != null
            ? crop.getFertilizers().stream()
            .map(FertilizerDto::fromEntity)
            .collect(Collectors.toList())
            : Collections.emptyList();

    return new CropDto(
            crop.getId(),
            crop.getName(),
            crop.getPlantedArea(),
            crop.getPlantedDate(),
            crop.getHarvestDate(),
            farmDto,
            fertilizerDtos // Inclui os fertilizantes
    );
  }
}
