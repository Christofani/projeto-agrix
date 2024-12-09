package com.betrybe.agrix.repository;

import com.betrybe.agrix.entity.Farm;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

/**
 * The interface Farm repository.
 */
@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {
}
