package com.betrybe.agrix.controller;


import com.betrybe.agrix.controller.dto.*;
import com.betrybe.agrix.entity.*;
import com.betrybe.agrix.exception.*;
import com.betrybe.agrix.repository.*;
import com.betrybe.agrix.service.*;
import com.betrybe.agrix.utils.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.access.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * The type Fertilizer controller.
 */
@RestController
@RequestMapping("/fertilizers")
@Secured("ADMIN")
public class FertilizerController {
  private final FertilizerService fertilizerService;

  /**
   * Instantiates a new Fertilizer controller.
   *
   * @param fertilizerService    the fertilizer service
   * @param fertilizerRepository the fertilizer repository
   */
  @Autowired
  public FertilizerController(FertilizerService fertilizerService, FertilizerRepository fertilizerRepository) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Create fertilizer fertilizer dto.
   *
   * @param fertilizer the fertilizer
   * @return the fertilizer dto
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FertilizerDto createFertilizer(@RequestBody FertilizerCreationDto fertilizer) {
    return FertilizerDto.fromEntity(fertilizerService.createFertilizer(fertilizer.toEntity()));
  }

  /**
   * Find all fertilizers list.
   *
   * @return the list
   */
  @GetMapping
  public List<FertilizerDto> findAllFertilizers() {
    List<Fertilizer> allFertilizers = fertilizerService.findAllFertilizers();
    return allFertilizers.stream()
            .map(FertilizerDto::fromEntity)
            .toList();
  }

  /**
   * Find fertilizer by id fertilizer dto.
   *
   * @param id the id
   * @return the fertilizer dto
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  @GetMapping("/{id}")
  public FertilizerDto findFertilizerById(@PathVariable Long id) throws FertilizerNotFoundException {
    return FertilizerDto.fromEntity(fertilizerService.findByFertilizerId(id));
  }

  /**
   * Updated fertilizer fertilizer dto.
   *
   * @param id         the id
   * @param fertilizer the fertilizer
   * @return the fertilizer dto
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  @PutMapping("/{id}")
  public FertilizerDto updatedFertilizer(
          @PathVariable Long id,
          @RequestBody FertilizerCreationDto fertilizer
  ) throws FertilizerNotFoundException {
    Fertilizer fertilizerExisting = fertilizerService.findByFertilizerId(id);

    fertilizerExisting.setName(fertilizer.name());
    fertilizerExisting.setBrand(fertilizer.brand());
    fertilizerExisting.setComposition(fertilizer.composition());

    Fertilizer updatedFertilizer = fertilizerService.updateFertilizer(fertilizerExisting);

    return FertilizerDto.fromEntity(updatedFertilizer);
  }

  /**
   * Delete fertilizer string.
   *
   * @param id the id
   * @return the string
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  @DeleteMapping("/{id}")
  public String deleteFertilizer(@PathVariable Long id) throws FertilizerNotFoundException {
    fertilizerService.deleteFertilizer(id);
    return MessageUtil.FERTILIZER_DELETED;
  }

}
