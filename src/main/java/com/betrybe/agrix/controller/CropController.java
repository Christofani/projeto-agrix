package com.betrybe.agrix.controller;


import com.betrybe.agrix.controller.dto.*;
import com.betrybe.agrix.entity.*;
import com.betrybe.agrix.exception.*;
import com.betrybe.agrix.service.*;
import com.betrybe.agrix.utils.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.format.annotation.*;
import org.springframework.http.*;
import org.springframework.security.access.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.util.*;
import java.util.stream.*;

/**
 * The type Crop controller.
 */
@RestController
@RequestMapping("/crops")
@Secured({"MANAGER","ADMIN"})
public class CropController {

  private final CropService cropService;
  private final FertilizerService fertilizerService;

  /**
   * Instantiates a new Crop controller.
   *
   * @param cropService       the crop service
   * @param fertilizerService the fertilizer service
   */
  @Autowired
  public CropController(CropService cropService, FertilizerService fertilizerService) {
    this.cropService = cropService;
    this.fertilizerService = fertilizerService;
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
   * Search crops by date range list.
   *
   * @param start the start
   * @param end   the end
   * @return the list
   */
  @GetMapping("search")
  public  List<CropDto> searchCropsByDateRange(
          @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate start,
          @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate end) {
    return cropService.findCropsBySearchDate(start, end).stream()
            .map(CropDto::fromEntity)
            .collect(Collectors.toList());
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
    cropToUpdate.setPlantedDate(crop.plantedDate());
    cropToUpdate.setHarvestDate(crop.harvestDate());

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

  /**
   * Associate crop with fertilizer string.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   * @return the string
   * @throws CropNotFoundException       the crop not found exception
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  @ResponseStatus(HttpStatus.CREATED)
  public String associateCropWithFertilizer(
          @PathVariable Long cropId, @PathVariable Long fertilizerId
  ) throws CropNotFoundException, FertilizerNotFoundException {
    Crop crop = cropService.findByCropId(cropId);
    Fertilizer fertilizer = fertilizerService.findByFertilizerId(fertilizerId);

    fertilizerService.associateCropWithFertilizer(crop, fertilizer);
    return MessageUtil.CROP_ASSOCIATE_FERTILIZER;
  }


  /**
   * Find all fertilizers list.
   *
   * @param cropId the crop id
   * @return the list
   * @throws CropNotFoundException the crop not found exception
   */
  @GetMapping("/{cropId}/fertilizers")
  public List<FertilizerDto> findAllFertilizers(@PathVariable Long cropId) throws CropNotFoundException {
    Crop crop = cropService.findByCropId(cropId);

    List<Fertilizer> fertilizers = crop.getFertilizers();

    List<FertilizerDto> allFertilizers = fertilizers.stream()
            .map(FertilizerDto::fromEntity)
            .collect(Collectors.toList());

    return allFertilizers;
  }
}
