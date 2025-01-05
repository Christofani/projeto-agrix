package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.*;

import java.util.*;
import java.util.stream.*;

/**
 * The type Farm dto.
 */
public record FarmDto(
        Long id,
        String name,
        Double size,
        List<CropDto> crops
) {

  /**
   * From entity farm dto.
   *
   * @param farm the farm
   * @return the farm dto
   */
  public static FarmDto fromEntity(Farm farm) {

    List<CropDto> cropDtos = farm.getCrops() != null
            ? farm.getCrops().stream()
            .map(CropDto::fromEntity)
            .collect(Collectors.toList())
            : Collections.emptyList();

    return new FarmDto(
            farm.getId(),
            farm.getName(),
            farm.getSize(),
            cropDtos
    );
  }
}
