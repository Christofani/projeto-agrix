package com.betrybe.agrix.repository;

import com.betrybe.agrix.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.util.*;

/**
 * The interface Crop repository.
 */
@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {

  List<Crop> findByFarmIdPerson(Person person);

  /**
   * Find all by harvest date between list.
   *
   * @param start the start
   * @param end   the end
   * @return the list
   */
  List<Crop> findAllByHarvestDateBetween(LocalDate start, LocalDate end);
}
