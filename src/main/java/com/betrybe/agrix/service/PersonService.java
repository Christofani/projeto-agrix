package com.betrybe.agrix.service;


import com.betrybe.agrix.entity.*;
import com.betrybe.agrix.exception.*;
import com.betrybe.agrix.repository.*;
import com.betrybe.agrix.utils.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

/**
 * The type Person service.
 */
@Service
public class PersonService {
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
   * Find person by username person.
   *
   * @param username the username
   * @return the person
   * @throws PersonNotFoundException the person not found exception
   */
  public Person findPersonByUsername(String username) throws PersonNotFoundException {
    return personRepository.findByUsername(username)
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
    personExisting.setPassword(person.getPassword());
    personExisting.setRole(person.getRole());
    return personRepository.save(personExisting);
 }

  /**
   * Delete person string.
   *
   * @param personId the person id
   * @return the string
   * @throws PersonNotFoundException the person not found exception
   */
  public String deletePerson(Long personId) throws PersonNotFoundException {
    Person person = personRepository.findById(personId)
            .orElseThrow(PersonNotFoundException::new);
    personRepository.delete(person);
    return MessageUtil.PERSON_DELETED;
 }

}
