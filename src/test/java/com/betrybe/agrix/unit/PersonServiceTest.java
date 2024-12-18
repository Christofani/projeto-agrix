package com.betrybe.agrix.unit;

import com.betrybe.agrix.entity.*;
import com.betrybe.agrix.exception.*;
import com.betrybe.agrix.repository.*;
import com.betrybe.agrix.security.*;
import org.mockito.Mockito;

import com.betrybe.agrix.service.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Testing the Person service layer ")
public class PersonServiceTest {

  @Autowired
  PersonService personService;

  @MockBean
  PersonRepository personRepository;

  @Test
  @DisplayName("Test - Create new Person")
  public void testCreationPerson() {
    Person person = new Person();
    person.setUsername("Christofani");
    person.setPassword("Rodrigo123");
    person.setRole(Role.USER);

    Person personToReturn = new Person();
    personToReturn.setId(123L);
    personToReturn.setUsername(person.getUsername());
    personToReturn.setPassword(person.getPassword());
    personToReturn.setRole(person.getRole());

    Mockito.when(personRepository.save(any())).thenReturn(personToReturn);


    Person savedPerson = personService.createPerson(person);

    Mockito.verify(personRepository).save(any());

    assertEquals(123L, savedPerson.getId());
    assertEquals(person.getUsername(), savedPerson.getUsername());
    assertEquals(person.getPassword(), savedPerson.getPassword());
    assertEquals(person.getRole(), savedPerson.getRole());
  }

  @Test
  @DisplayName("Test - Search Person by Id ")
  public void testPersonRetrieval() {
    Person person = new Person();
    person.setId(123L);
    person.setUsername("Christofani");
    person.setPassword("Rodrigo123");
    person.setRole(Role.USER);

    Mockito.when(personRepository.findById(eq(123L)))
            .thenReturn(Optional.of(person));

    Person personReturned = personService.findByPersonId(123L);

    Mockito.verify(personRepository).findById(eq(123L));

    assertEquals(personReturned.getId(), person.getId());
    assertEquals(personReturned.getUsername(), person.getUsername());
    assertEquals(personReturned.getPassword(), person.getPassword());
    assertEquals(personReturned.getRole(), person.getRole());
  }

  @Test
  @DisplayName("Test - Testing search for person id not found ")
  public void testPersonIdNotFound() {
    Mockito.when(personRepository.findById(any()))
            .thenReturn(Optional.empty());

    assertThrows(PersonNotFoundException.class, () -> personService.findByPersonId(456L));

    Mockito.verify(personRepository).findById(eq(456L));
  }
}
