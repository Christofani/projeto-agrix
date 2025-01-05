package com.betrybe.agrix.entity;


import com.betrybe.agrix.security.Role;
import jakarta.persistence.*;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.userdetails.*;

import java.util.*;

/**
 * Class representing a person.
 */
@Entity
public class Person implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String username;

  private String password;

  private Role role;

  @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Farm> farms = new ArrayList<>();

  @ManyToMany(mappedBy = "persons")
  private List<Fertilizer> fertilizers = new ArrayList<>();

  /**
   * Instantiates a new Person.
   */
  public Person() {
  }

  /**
   * Instantiates a new Person.
   *
   * @param username the username
   * @param password the password
   * @param role     the role
   */
  public Person(String username, String password, Role role) {
    this.username = username;
    this.password = password;
    this.role = role;
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(Long id) {
    this.id = id;
  }


  @Override
  public String getUsername() {
    return username;
  }

  /**
   * Sets username.
   *
   * @param username the username
   */
  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public String getPassword() {
    return password;
  }

  /**
   * Sets password.
   *
   * @param password the password
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Gets role.
   *
   * @return the role
   */
  public Role getRole() {
    return role;
  }

  /**
   * Sets role.
   *
   * @param role the role
   */
  public void setRole(Role role) {
    this.role = role;
  }

  /**
   * Gets farms.
   *
   * @return the farms
   */
  public List<Farm> getFarms() {
    return farms;
  }

  /**
   * Sets farms.
   *
   * @param farms the farms
   */
  public void setFarms(List<Farm> farms) {
    this.farms = farms;
  }

  public List<Fertilizer> getFertilizers() {
    return fertilizers;
  }

  public void setFertilizers(List<Fertilizer> fertilizers) {
    this.fertilizers = fertilizers;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(
            new SimpleGrantedAuthority(role.name())
    );
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Person person = (Person) o;
    return Objects.equals(id, person.id) && Objects.equals(username,
            person.username) && Objects.equals(password, person.password)
            && Objects.equals(role, person.role);
  }
}