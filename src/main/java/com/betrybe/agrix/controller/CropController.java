package com.betrybe.agrix.controller;


import com.betrybe.agrix.controller.dto.*;
import com.betrybe.agrix.entity.*;
import com.betrybe.agrix.exception.*;
import com.betrybe.agrix.service.*;
import com.betrybe.agrix.utils.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.*;

/**
 * The type Crop controller.
 */
@RestController
@RequestMapping("/crops")
public class CropController {

  private final CropService cropService;

  /**
   * Instantiates a new Crop controller.
   *
   * @param cropService the crop service
   */
  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * Find all crops list.
   *
   * @return the list
   */
  @GetMapping
  public List<CropDto> findAllCrops() {
    List<Crop> allCrops = cropService.findAllCrops();
    return allCrops.stream()
            .map(CropDto::fromEntity)
            .collect(Collectors.toList());
  }

  /**
   * Find crop by id crop dto.
   *
   * @param id the id
   * @return the crop dto
   * @throws CropNotFoundException the crop not found exception
   */
  @GetMapping("/{id}")
  public CropDto findCropById(@PathVariable Long id) throws CropNotFoundException {
    return CropDto.fromEntity(
            cropService.findByCropId(id)
    );
  }

  /**
   * Updated crop crop dto.
   *
   * @param id   the id
   * @param crop the crop
   * @return the crop dto
   * @throws CropNotFoundException the crop not found exception
   */
  @PutMapping("/{id}")
  public CropDto updatedCrop(@PathVariable Long id, @RequestBody CropCreationDto crop) throws CropNotFoundException {
    Crop cropToUpdate = cropService.findByCropId(id);

    cropToUpdate.setName(crop.name());
    cropToUpdate.setPlantedArea(crop.plantedArea());

    Crop updatedCrop = cropService.updatedCrop(cropToUpdate);
    return CropDto.fromEntity(updatedCrop);
  }

  /**
   * Deleted crop string.
   *
   * @param id the id
   * @return the string
   * @throws CropNotFoundException the crop not found exception
   */
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public String deletedCrop(@PathVariable Long id) throws CropNotFoundException {
    cropService.deleteCrop(id);
    return MessageUtil.CROP_DELETED;
  }

}
