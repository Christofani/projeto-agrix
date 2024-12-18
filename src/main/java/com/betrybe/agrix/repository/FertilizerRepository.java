package com.betrybe.agrix.repository;


import com.betrybe.agrix.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

/**
 * The interface Fertilizer repository.
 */
@Repository
public interface FertilizerRepository extends JpaRepository<Fertilizer, Long> {
}
