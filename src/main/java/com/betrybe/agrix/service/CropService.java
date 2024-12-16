package com.betrybe.agrix.service;

import com.betrybe.agrix.entity.*;
import com.betrybe.agrix.exception.CropNotFoundException;
import com.betrybe.agrix.exception.FarmNotFoundException;

import com.betrybe.agrix.repository.*;
import com.betrybe.agrix.utils.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

/**
 * The type Crop service.
 */
@Service
public class CropService {

  private final CropRepository cropRepository;
  private final FarmService farmService;


  /**
   * Instantiates a new Crop service.
   *
   * @param cropRepository the crop repository
   * @param farmService    the farm service
   */
  @Autowired
  public CropService(CropRepository cropRepository, FarmService farmService) {
    this.cropRepository = cropRepository;
    this.farmService = farmService;
  }

  /**
   * Create crop for farm crop.
   *
   * @param farmId the farm id
   * @param crop   the crop
   * @return the crop
   * @throws FarmNotFoundException the farm not found exception
   */
  public Crop createCropForFarm(Long farmId, Crop crop) throws FarmNotFoundException {
    Farm farm = farmService.findByFarmId(farmId);
    crop.setFarmId(farm);

    return cropRepository.save(crop);
  }

  /**
   * Find all crops list.
   *
   * @return the list
   */
  public List<Crop> findAllCrops() {
    return cropRepository.findAll();
  }

  /**
   * Find by crop id crop.
   *
   * @param cropId the crop id
   * @return the crop
   * @throws CropNotFoundException the crop not found exception
   */
  public Crop findByCropId(Long cropId) throws CropNotFoundException {
    return cropRepository.findById(cropId)
            .orElseThrow(CropNotFoundException::new);
  }

  /**
   * Find crop by farm id list.
   *
   * @param farmId the farm id
   * @return the list
   * @throws FarmNotFoundException the farm not found exception
   */
  public List<Crop> findCropByFarmId(Long farmId) throws FarmNotFoundException {
    Farm farm = farmService.findByFarmId(farmId);

    return findAllCrops().stream()
            .filter(crop -> crop.getFarmId().equals(farm))
            .collect(Collectors.toList());
  }

  /**
   * Updated crop.
   *
   * @param crop the crop
   * @return the crop
   * @throws CropNotFoundException the crop not found exception
   */
  public Crop updatedCrop(Crop crop) throws CropNotFoundException {
    Crop existingCrop = cropRepository.findById(crop.getId())
            .orElseThrow(CropNotFoundException::new);

    existingCrop.setName(crop.getName());
    existingCrop.setPlantedArea(crop.getPlantedArea());
    return cropRepository.save(existingCrop);
  }

  /**
   * Delete crop string.
   *
   * @param id the id
   * @return the string
   * @throws CropNotFoundException the crop not found exception
   */
  public String deleteCrop(Long id) throws CropNotFoundException {
    Crop existingCrop = cropRepository.findById(id)
            .orElseThrow(CropNotFoundException::new);
    cropRepository.delete(existingCrop);
    return MessageUtil.CROP_DELETED;
  }
}
