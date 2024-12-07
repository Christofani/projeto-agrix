package com.betrybe.agrix.services;

import com.betrybe.agrix.entity.*;
import com.betrybe.agrix.exception.FarmNotFoundException;
import com.betrybe.agrix.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class FarmService {

  private final FarmRepository farmRepository;

  @Autowired
  public FarmService(FarmRepository farmRepository) {
    this.farmRepository = farmRepository;
  }

  public Farm createFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  public List<Farm> findAllFarms() {
    return farmRepository.findAll();
  }

  public Farm findByFarmId(Long id) throws FarmNotFoundException {
    return  farmRepository.findById(id)
            .orElseThrow(FarmNotFoundException::new);
  }

  public Farm updateFarm(Farm farm) throws FarmNotFoundException {
    Farm farmExisting = farmRepository.findById(farm.getId())
            .orElseThrow(FarmNotFoundException::new);

    farmExisting.setName(farm.getName());
    farmExisting.setSize(farm.getSize());

    return farmRepository.save(farmExisting);
  }

  public String deleteFarm (Long id) throws FarmNotFoundException {
      Farm farmExisting = farmRepository.findById(id)
              .orElseThrow(FarmNotFoundException::new);
    farmRepository.delete(farmExisting);
    return "Farm was successfully deleted.";
  }
}
