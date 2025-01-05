package com.betrybe.agrix.service;

import com.betrybe.agrix.entity.*;
import com.betrybe.agrix.exception.*;
import com.betrybe.agrix.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.*;

import java.util.*;

/**
 * The FertilizerService class provides methods to handle the fertilizer management.
 */
@Service
public class FertilizerService {

  private final FertilizerRepository fertilizerRepository;
  private final CropRepository cropRepository;

  @Autowired
  public FertilizerService(FertilizerRepository fertilizerRepository, CropRepository cropRepository) {
    this.fertilizerRepository = fertilizerRepository;
    this.cropRepository = cropRepository;
  }

  /**
   * List all fertilizers for the authenticated user.
   */
  public List<Fertilizer> findAllFertilizers() {

      return fertilizerRepository.findAll();

  }

  /**
   * Create a fertilizer.
   */
  public Fertilizer createFertilizer(Fertilizer fertilizer) {
    if (hasRole("ADMIN")) {
      return fertilizerRepository.save(fertilizer);
    } else if (hasRole("MANAGER") || hasRole("USER")) {
      throw new AccessDeniedException("Somente ADMIN pode criar fertilizantes.");
    }
    throw new AccessDeniedException("Você não tem permissão para criar fertilizantes.");
  }

  /**
   * Find a fertilizer by ID and validate ownership.
   */
  public Fertilizer findByFertilizerId(Long id) throws FertilizerNotFoundException, AccessDeniedException {
    Fertilizer fertilizer = fertilizerRepository.findById(id)
            .orElseThrow(FertilizerNotFoundException::new);

    if (hasRole("ADMIN") || hasRole("MANAGER")) {
      return fertilizer;
    } else if (hasRole("USER")) {
      validateFertilizerOwnership(fertilizer);
      return fertilizer;
    }
    throw new AccessDeniedException("Você não tem permissão para acessar este fertilizante.");
  }

  /**
   * Update a fertilizer.
   */
  public Fertilizer updateFertilizer(Fertilizer fertilizer) throws FertilizerNotFoundException, AccessDeniedException {
    Fertilizer existingFertilizer = fertilizerRepository.findById(fertilizer.getId())
            .orElseThrow(FertilizerNotFoundException::new);

    if (!hasRole("ADMIN")) {
      throw new AccessDeniedException("Você não tem permissão para editar fertilizantes, peça ao ADMIN!");
    }

    existingFertilizer.setName(fertilizer.getName());
    existingFertilizer.setBrand(fertilizer.getBrand());
    existingFertilizer.setComposition(fertilizer.getComposition());

    return fertilizerRepository.save(existingFertilizer);
  }

  /**
   * Delete a fertilizer.
   */
  public void deleteFertilizer(Long id) throws FertilizerNotFoundException, AccessDeniedException {
    if (!hasRole("ADMIN")) {
      throw new AccessDeniedException("Somente ADMIN pode excluir fertilizantes.");
    }

    Fertilizer existingFertilizer = findByFertilizerId(id);
    fertilizerRepository.delete(existingFertilizer);
  }

  /**
   * Associate a fertilizer with a crop.
   */
  public void associateCropWithFertilizer(Crop crop, Fertilizer fertilizer) throws FertilizerNotFoundException, AccessDeniedException {
    // Verificar permissões
    if (!hasRole("ADMIN") && !hasRole("MANAGER")) {
      throw new AccessDeniedException("Somente ADMIN ou MANAGER pode associar fertilizantes.");
    }

    if (fertilizer == null || !fertilizerRepository.existsById(fertilizer.getId())) {
      throw new FertilizerNotFoundException();
    }

    if (crop == null || !cropRepository.existsById(crop.getId())) {
      throw new CropNotFoundException();
    }

    crop.getFertilizers().add(fertilizer);
    cropRepository.save(crop);
  }

  /**
   * Utility method to get the current authenticated user.
   */
  private Person getCurrentUser() {
    return (Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }

  private boolean hasRole(String role) {
    return SecurityContextHolder.getContext().getAuthentication()
            .getAuthorities().contains(new SimpleGrantedAuthority(role));
  }

  /**
   * Utility method to validate if the current user owns the given fertilizer through their crops.
   */
  private void validateFertilizerOwnership(Fertilizer fertilizer) throws AccessDeniedException {
    Person currentUser = getCurrentUser();
    boolean ownsFertilizer = fertilizer.getCrops().stream()
            .anyMatch(crop -> crop.getFarmId().getPerson().getId().equals(currentUser.getId()));

    if (!ownsFertilizer) {
      throw new AccessDeniedException("Você não tem permissão para acessar este fertilizante.");
    }
  }
}

