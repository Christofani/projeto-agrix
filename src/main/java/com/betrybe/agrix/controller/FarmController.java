package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.*;
import com.betrybe.agrix.entity.*;
import com.betrybe.agrix.exception.*;
import com.betrybe.agrix.services.*;
import com.betrybe.agrix.utils.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * The type Farm controller.
 */
@RestController
@RequestMapping("/farms")
public class FarmController {

  private final FarmService farmService;

  /**
   * Instantiates a new Farm controller.
   *
   * @param farmService the farm service
   */
  @Autowired
  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  /**
   * Create farm farm dto.
   *
   * @param farmCreationDto the farm creation dto
   * @return the farm dto
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FarmDto createFarm(@RequestBody FarmCreationDto farmCreationDto) {
    return FarmDto.fromEntity(
            farmService.createFarm(farmCreationDto.toEntity())
    );
  }

  /**
   * Find all farms list.
   *
   * @return the list
   */
  @GetMapping
  public List<FarmDto> findAll() {
    List<Farm> allFarms = farmService.findAllFarms();
    return allFarms.stream()
            .map(FarmDto::fromEntity)
            .toList();
  }

  /**
   * Find by farm id farm dto.
   *
   * @param id the id
   * @return the farm dto
   * @throws FarmNotFoundException the farm not found exception
   */
  @GetMapping("/{id}")
  public FarmDto findByFarmId(@PathVariable Long id) throws FarmNotFoundException {
    return FarmDto.fromEntity(farmService.findByFarmId(id));
  }

  /**
   * Update farm farm dto.
   *
   * @param id            the id
   * @param farmUpdateDto the farm update dto
   * @return the farm dto
   * @throws FarmNotFoundException the farm not found exception
   */
  @PutMapping("/{id}")
  public FarmDto updateFarm(
          @PathVariable Long id, @RequestBody FarmCreationDto farmUpdateDto
  ) throws FarmNotFoundException {
    Farm farmToUpdate = farmService.findByFarmId(id);

    farmToUpdate.setName(farmUpdateDto.name());
    farmToUpdate.setSize(farmUpdateDto.size());

    Farm updatedFarm = farmService.updateFarm(farmToUpdate);

    return FarmDto.fromEntity(updatedFarm);
  }

  /**
   * Delete farm string.
   *
   * @param id the id
   * @return the string
   * @throws FarmNotFoundException the farm not found exception
   */
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public String deleteFarm(@PathVariable Long id) throws FarmNotFoundException {
    farmService.deleteFarm(id);
    return MessageUtil.FARM_DELETED;
  }

}