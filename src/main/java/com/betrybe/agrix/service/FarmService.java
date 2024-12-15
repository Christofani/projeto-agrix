package com.betrybe.agrix.service;

import com.betrybe.agrix.entity.*;
import com.betrybe.agrix.exception.FarmNotFoundException;
import com.betrybe.agrix.repository.*;
import com.betrybe.agrix.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import java.util.*;

/**
 * The type Farm service.
 */
@Service
public class FarmService {

  private final FarmRepository farmRepository;

  /**
   * Instantiates a new Farm service.
   *
   * @param farmRepository the farm repository
   */
  @Autowired
  public FarmService(FarmRepository farmRepository) {
    this.farmRepository = farmRepository;
  }

  /**
   * Create farm farm.
   *
   * @param farm the farm
   * @return the farm
   */
  public Farm createFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  /**
   * Find all farms list.
   *
   * @return the list
   */
  public List<Farm> findAllFarms() {
    return farmRepository.findAll();
  }

  /**
   * Find by farm id farm.
   *
   * @param id the id
   * @return the farm
   * @throws FarmNotFoundException the farm not found exception
   */
  public Farm findByFarmId(Long id) throws FarmNotFoundException {
    return  farmRepository.findById(id)
            .orElseThrow(FarmNotFoundException::new);
  }

  /**
   * Update farm farm.
   *
   * @param farm the farm
   * @return the farm
   * @throws FarmNotFoundException the farm not found exception
   */
  public Farm updateFarm(Farm farm) throws FarmNotFoundException {
    Farm farmExisting = farmRepository.findById(farm.getId())
            .orElseThrow(FarmNotFoundException::new);

    farmExisting.setName(farm.getName());
    farmExisting.setSize(farm.getSize());

    return farmRepository.save(farmExisting);
  }

  /**
   * Delete farm string.
   *
   * @param id the id
   * @return the string
   * @throws FarmNotFoundException the farm not found exception
   */
  public String deleteFarm (Long id) throws FarmNotFoundException {
      Farm farmExisting = farmRepository.findById(id)
              .orElseThrow(FarmNotFoundException::new);
    farmRepository.delete(farmExisting);
    return MessageUtil.FARM_DELETED;
  }
}
