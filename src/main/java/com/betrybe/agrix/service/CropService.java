package com.betrybe.agrix.service;

import com.betrybe.agrix.entity.*;
import com.betrybe.agrix.exception.*;

import com.betrybe.agrix.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.context.*;
import org.springframework.stereotype.*;

import java.time.*;
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
  public Crop createCropForFarm(Long farmId, Crop crop) throws FarmNotFoundException, AccessDeniedException {
    Farm farm = farmService.findByFarmId(farmId);

    Person currentUser = (Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (!farm.getPerson().equals(currentUser) && !hasRole("ADMIN") && !hasRole("MANAGER")) {
      throw new AccessDeniedException("Você não tem permissão para criar plantações,fale com um responsável!");
    }

    crop.setFarmId(farm);
    return cropRepository.save(crop);
  }

  /**
   * Find all crops for the current user.
   *
   * @return the list of crops
   */
  public List<Crop> findAllCropsForCurrentUser() {
    Person currentUser = (Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    if (hasRole("ADMIN") || hasRole("MANAGER")) {
      return cropRepository.findAll();
    } else {
      return cropRepository.findByFarmIdPerson(currentUser);
    }
  }

  /**
   * Find by crop id crop.
   *
   * @param cropId the crop id
   * @return the crop
   * @throws CropNotFoundException the crop not found exception
   */
  public Crop findByCropId(Long cropId) throws CropNotFoundException, AccessDeniedException {
    Crop crop = cropRepository.findById(cropId)
            .orElseThrow(CropNotFoundException::new);

    Person currentUser = (Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (!crop.getFarmId().getPerson().equals(currentUser) && !hasRole("ADMIN") && !hasRole("MANAGER")) {
      throw new AccessDeniedException();
    }

    return crop;
  }


  /**
   * Find crop by farm id list.
   *
   * @param farmId the farm id
   * @return the list of crops
   * @throws FarmNotFoundException the farm not found exception
   */
  public List<Crop> findCropByFarmId(Long farmId) throws FarmNotFoundException, AccessDeniedException {
    Farm farm = farmService.findByFarmId(farmId);


    Person currentUser = (Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (!farm.getPerson().equals(currentUser) && !hasRole("ADMIN") && !hasRole("MANAGER")) {
      throw new AccessDeniedException("Você não tem permissão para acessar essa plantação !");
    }

    return cropRepository.findAll().stream()
            .filter(crop -> crop.getFarmId().equals(farm))
            .collect(Collectors.toList());
  }

  /**
   * Find crops by search date for current user.
   *
   * @param start the start date
   * @param end   the end date
   * @return the list of crops
   */
  public List<Crop> findCropsBySearchDate(LocalDate start, LocalDate end) {
    Person currentUser = (Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return cropRepository.findAllByHarvestDateBetween(start, end).stream()
            .filter(crop -> crop.getFarmId().getPerson().equals(currentUser) || hasRole("ADMIN") || hasRole("MANAGER"))
            .collect(Collectors.toList());
  }

  /**
   * Updated crop.
   *
   * @param crop the crop
   * @return the crop
   * @throws CropNotFoundException the crop not found exception
   */
  public Crop updatedCrop(Crop crop) throws CropNotFoundException, AccessDeniedException {
    Crop existingCrop = cropRepository.findById(crop.getId())
            .orElseThrow(CropNotFoundException::new);

    // Verifica se a crop pertence ao usuário autenticado ou se o usuário tem a role de ADMIN ou MANAGER
    Person currentUser = (Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (!existingCrop.getFarmId().getPerson().equals(currentUser) && !hasRole("ADMIN")) {
      throw new AccessDeniedException("Você não tem acesso para editar essa plantação, peça ao ADMIN!");
    }

    existingCrop.setName(crop.getName());
    existingCrop.setPlantedArea(crop.getPlantedArea());
    existingCrop.setPlantedDate(crop.getPlantedDate());
    existingCrop.setHarvestDate(crop.getHarvestDate());
    return cropRepository.save(existingCrop);
  }

  /**
   * Delete crop string.
   *
   * @param id the id
   * @throws CropNotFoundException the crop not found exception
   */
  public void deleteCrop(Long id) throws CropNotFoundException, AccessDeniedException {
    Crop existingCrop = cropRepository.findById(id)
            .orElseThrow(CropNotFoundException::new);

    // Verifica se a crop pertence ao usuário autenticado
    Person currentUser = (Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (!existingCrop.getFarmId().getPerson().equals(currentUser) && !hasRole("ADMIN")) {
      throw new AccessDeniedException("Você não tem acesso para excluir essa plantação, peça ao ADMIN!");
    }

    cropRepository.delete(existingCrop);
  }

  private boolean hasRole(String role) {
    return SecurityContextHolder.getContext().getAuthentication()
            .getAuthorities().stream()
            .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(role));
  }
}
