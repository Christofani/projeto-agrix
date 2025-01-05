package com.betrybe.agrix.repository;

import com.betrybe.agrix.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.awt.*;
import java.util.List;

/**
 * The interface Farm repository.
 */
@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {

  List<Farm> findByPerson(Person person);
}
