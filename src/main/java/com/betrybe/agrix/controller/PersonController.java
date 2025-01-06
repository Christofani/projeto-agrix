package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.*;
import com.betrybe.agrix.entity.*;
import com.betrybe.agrix.exception.*;
import com.betrybe.agrix.service.*;
import com.betrybe.agrix.utils.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.access.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/persons")

public class PersonController {

  PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PersonDto createPerson(@RequestBody PersonCreationDto personCreation) {
    return PersonDto.fromEntity(
            personService.createPerson(personCreation.toEntity())
    );
  }

  @GetMapping
  public List<PersonDto> findAllPersons() {
    List<Person> allPersons = personService.findAllPersons();
    return allPersons.stream().map(PersonDto::fromEntity).toList();
  }

  @GetMapping("/{id}")
  public PersonDto findPersonById(@PathVariable Long id) throws PersonNotFoundException {
    return PersonDto.fromEntity(personService.findByPersonId(id));
  }

  // Listar por Username

  @PutMapping("/{id}")
  public PersonDto updatePersonById(@PathVariable Long id, @RequestBody PersonCreationDto person) throws PersonNotFoundException {
    Person personExisting = personService.findByPersonId(id);

    personExisting.setUsername(person.username());
    personExisting.setPassword(person.password());
    personExisting.setRole(person.role());

    Person updatedPerson = personService.updatePerson(personExisting);
    return PersonDto.fromEntity(updatedPerson);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public String deletePersonById(@PathVariable Long id) throws PersonNotFoundException {
    personService.deletePerson(id);
    return MessageUtil.PERSON_DELETED;
  }
}
