package com.betrybe.agrix.service;


import com.betrybe.agrix.entity.*;
import com.betrybe.agrix.exception.*;
import com.betrybe.agrix.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.stereotype.*;

import java.util.*;

/**
 * The type Person service.
 */
@Service
public class PersonService implements UserDetailsService {
  private final PersonRepository personRepository;

  /**
   * Instantiates a new Person service.
   *
   * @param personRepository the person repository
   */
  @Autowired
  public PersonService(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  /**
   * Create person.
   *
   * @param person the person
   * @return the person
   */
  public Person createPerson(Person person) {
    String hashPassword = new BCryptPasswordEncoder()
            .encode(person.getPassword());
    person.setPassword(hashPassword);

    return personRepository.save(person);
  }

  /**
   * Find all persons list.
   *
   * @return the list
   */
  public List<Person> findAllPersons() {
    return personRepository.findAll();
 }

  /**
   * Find by person id person.
   *
   * @param personId the person id
   * @return the person
   * @throws PersonNotFoundException the person not found exception
   */
  public Person findByPersonId(Long personId)  throws PersonNotFoundException {
    return personRepository.findById(personId)
            .orElseThrow(PersonNotFoundException::new);
  }

  /**
   * Update person.
   *
   * @param person the person
   * @return the person
   * @throws PersonNotFoundException the person not found exception
   */
  public Person updatePerson(Person person) throws PersonNotFoundException {
    Person personExisting = personRepository.findById(person.getId())
            .orElseThrow(PersonNotFoundException::new);

    personExisting.setUsername(person.getUsername());


    if (person.getPassword() != null && !person.getPassword().isEmpty()) {
      String hashPassword = new BCryptPasswordEncoder().encode(person.getPassword());
      personExisting.setPassword(hashPassword);
    }


    personExisting.setRole(person.getRole());
    return personRepository.save(personExisting);
 }

  /**
   * Delete person string.
   *
   * @param personId the person id
   * @throws PersonNotFoundException the person not found exception
   */
  public void deletePerson(Long personId) throws PersonNotFoundException {
    Person person = personRepository.findById(personId)
            .orElseThrow(PersonNotFoundException::new);
    personRepository.delete(person);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return personRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException(username));
  }
}
