package com.betrybe.agrix.repository;

import com.betrybe.agrix.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

/**
 * The interface Fertilizer repository.
 */
@Repository
public interface FertilizerRepository extends JpaRepository<Fertilizer, Long> {


  // Consulta para obter todos os fertilizantes associados a uma pessoa (admin ou gerente)
  @Query("SELECT f FROM Fertilizer f JOIN f.persons p WHERE p.id = :personId")
  List<Fertilizer> findByPersonId(Long personId);
  }
