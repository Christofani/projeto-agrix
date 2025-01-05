package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The type Farm dto.
 */
public record FarmDto(
        Long id,
        String name,
        Double size,
        PersonInfoDto person, // Alterado para apenas o ID da pessoa
        List<CropDto> crops
) {

  /**
   * From entity farm dto.
   *
   * @param farm the farm
   * @return the farm dto
   */
  public static FarmDto fromEntity(Farm farm) {
    // Converte Person para PersonInfoDto
    PersonInfoDto personInfoDto = farm.getPerson() != null ? PersonInfoDto.fromEntity(farm.getPerson()) : null;

    // Converte a lista de Crops para CropDtos
    List<CropDto> cropDtos = farm.getCrops() != null
            ? farm.getCrops().stream()
            .map(CropDto::fromEntity)
            .collect(Collectors.toList())
            : List.of();

    return new FarmDto(
            farm.getId(),
            farm.getName(),
            farm.getSize(),
            personInfoDto,
            cropDtos
    );
  }
}
