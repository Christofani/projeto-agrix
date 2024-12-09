package com.betrybe.agrix.entity;


import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.*;

import java.util.*;

/**
 * The type Farm.
 */
@Entity
@Table(name = "farms")
@EntityListeners(AuditingEntityListener.class)
public class Farm {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private Double size;

  @OneToMany(mappedBy = "farmId", cascade = CascadeType.ALL)
  private List<Crop> crops = new ArrayList<>();

  /**
   * Instantiates a new Farm.
   */
  public Farm() {}

  /**
   * Instantiates a new Farm.
   *
   * @param name the name
   * @param size the size
   */
  public Farm(String name, Double size) {
    this.name = name;
    this.size = size;
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

  /**
   * Gets name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets name.
   *
   * @param name the name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets size.
   *
   * @return the size
   */
  public Double getSize() {
    return size;
  }

  /**
   * Sets size.
   *
   * @param size the size
   */
  public void setSize(Double size) {
    this.size = size;
  }

  /**
   * Gets crops.
   *
   * @return the crops
   */
  public List<Crop> getCrops() {
    return crops;
  }

  /**
   * Sets crops.
   *
   * @param crops the crops
   */
  public void setCrops(List<Crop> crops) {
    this.crops = crops;
  }
}
