package com.betrybe.agrix.repository;

import com.betrybe.agrix.entity.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface PersonRepository extends JpaRepository<Person, Long> {
  Optional<Person> findByUsername(String username);
}
