package com.betrybe.agrix.service;


import com.betrybe.agrix.entity.*;
import com.betrybe.agrix.exception.*;
import com.betrybe.agrix.repository.*;
import com.betrybe.agrix.utils.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

/**
 * The type Fertilizer service.
 */
@Service
public class FertilizerService {

  private final FertilizerRepository fertilizerRepository;
  private final CropRepository cropRepository;

  /**
   * Instantiates a new Fertilizer service.
   *
   * @param fertilizerRepository the fertilizer repository
   */
  @Autowired
  public FertilizerService(FertilizerRepository fertilizerRepository, CropRepository cropRepository) {
    this.fertilizerRepository = fertilizerRepository;
    this.cropRepository = cropRepository;
  }

  /**
   * Create fertilizer fertilizer.
   *
   * @param fertilizer the fertilizer
   * @return the fertilizer
   */
  public Fertilizer createFertilizer(Fertilizer fertilizer) {
    return fertilizerRepository.save(fertilizer);
  }


  /**
   * Find all fertilizers list.
   *
   * @return the list
   */
  public List<Fertilizer> findAllFertilizers() {
    return fertilizerRepository.findAll();
  }

  /**
   * Find by fertilizer id fertilizer.
   *
   * @param id the id
   * @return the fertilizer
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  public Fertilizer findByFertilizerId(Long id) throws FertilizerNotFoundException {
    return fertilizerRepository.findById(id)
            .orElseThrow(FertilizerNotFoundException::new);
  }

  /**
   * Update fertilizer fertilizer.
   *
   * @param fertilizer the fertilizer
   * @return the fertilizer
   */
  public Fertilizer updateFertilizer(Fertilizer fertilizer) {
    Fertilizer fertilizerExisting = fertilizerRepository.findById(fertilizer.getId())
            .orElseThrow(FertilizerNotFoundException::new);

    fertilizerExisting.setName(fertilizer.getName());
    fertilizerExisting.setBrand(fertilizer.getBrand());
    fertilizerExisting.setComposition(fertilizer.getComposition());

    return fertilizerRepository.save(fertilizerExisting);
  }

  /**
   * Delete fertilizer string.
   *
   * @param id the id
   * @return the string
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  public String deleteFertilizer(Long id) throws FertilizerNotFoundException {
    Fertilizer fertilizerExisting = fertilizerRepository.findById(id)
            .orElseThrow(FertilizerNotFoundException::new);
    fertilizerRepository.delete(fertilizerExisting);
    return MessageUtil.FERTILIZER_DELETED;
  }

  public void associateCropWithFertilizer(Crop crop, Fertilizer fertilizer)
          throws FertilizerNotFoundException {
    crop.getFertilizers().add(fertilizer);

    cropRepository.save(crop);
  }
}
