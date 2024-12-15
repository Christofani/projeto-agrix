package com.betrybe.agrix.repository;

import com.betrybe.agrix.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

/**
 * The interface Crop repository.
 */
@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {
}
