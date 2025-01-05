package com.betrybe.agrix.service;

import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.entity.Person;
import com.betrybe.agrix.exception.AccessDeniedException;
import com.betrybe.agrix.exception.FarmNotFoundException;
import com.betrybe.agrix.repository.FarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The FarmService class provides methods to handle the farm management.
 */
@Service
public class FarmService {

  private final FarmRepository farmRepository;

  @Autowired
  public FarmService(FarmRepository farmRepository) {
    this.farmRepository = farmRepository;
  }

  /**
   * List all farms for the authenticated user.
   */
  public List<Farm> findAllFarms() {
    if (hasRole("ADMIN") || hasRole("MANAGER")) {
      return farmRepository.findAll();
    } else if (hasRole("USER")) {

      Person currentUser = getCurrentUser();
      return farmRepository.findByPerson(currentUser);
    }
    throw new AccessDeniedException("Você não tem permissão para acessar fazendas.");
  }

  /**
   * Create a farm.
   */
  public Farm createFarm(Farm farm) {
    Person currentUser = getCurrentUser();


    if (hasRole("USER")) {
      farm.setPerson(currentUser);
      return farmRepository.save(farm);
    } else if (hasRole("ADMIN") || hasRole("MANAGER")) {
      return farmRepository.save(farm);
    }
    throw new AccessDeniedException("Você não tem permissão para criar fazendas.");
  }

  /**
   * Find a farm by ID and validate ownership.
   */
  public Farm findByFarmId(Long id) throws FarmNotFoundException, AccessDeniedException {
    Farm farm = farmRepository.findById(id)
            .orElseThrow(FarmNotFoundException::new);

    if (hasRole("ADMIN") || hasRole("MANAGER")) {
      return farm;
    } else if (hasRole("USER")) {
      validateFarmOwnership(farm);
      return farm;
    }
    throw new AccessDeniedException("Você não tem permissão para acessar esta fazenda.");
  }

  /**
   * Update a farm.
   */
  public Farm updateFarm(Farm farm) throws FarmNotFoundException, AccessDeniedException {
    Farm existingFarm = findByFarmId(farm.getId());

    if (!hasRole("ADMIN")) {
      validateFarmOwnership(existingFarm);
    }

    existingFarm.setName(farm.getName());
    existingFarm.setSize(farm.getSize());

    return farmRepository.save(existingFarm);
  }

  /**
   * Delete a farm.
   */
  public void deleteFarm(Long id) throws FarmNotFoundException, AccessDeniedException {
    Farm existingFarm = findByFarmId(id);

    if (!hasRole("ADMIN")) {
      validateFarmOwnership(existingFarm);
    }

    farmRepository.delete(existingFarm);
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
   * Utility method to validate if the current user owns the given farm.
   */
  private void validateFarmOwnership(Farm farm) throws AccessDeniedException {
    Person currentUser = getCurrentUser();
    if (!farm.getPerson().getId().equals(currentUser.getId())) {
      throw new AccessDeniedException("Você não tem permissão para acessar a fazenda com ID: " + farm.getId());
    }
  }
}
