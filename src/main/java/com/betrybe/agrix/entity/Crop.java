package com.betrybe.agrix.entity;


import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.*;

/**
 * The type Crop.
 */
@Entity
@Table(name = "crops")
@EntityListeners(AuditingEntityListener.class)
public class Crop {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "farm_id")
  private Farm farmId;

  private String name;
  private Double plantedArea;

  /**
   * Instantiates a new Crop.
   */
  public Crop() {}

  /**
   * Instantiates a new Crop.
   *
   * @param name        the name
   * @param plantedArea the planted area
   */
  public Crop( String name, Double plantedArea) {
    this.name = name;
    this.plantedArea = plantedArea;
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
   * Gets farm id.
   *
   * @return the farm id
   */
  public Farm getFarmId() {
    return farmId;
  }

  /**
   * Sets farm id.
   *
   * @param farmId the farm id
   */
  public void setFarmId(Farm farmId) {
    this.farmId = farmId;
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
   * Gets planted area.
   *
   * @return the planted area
   */
  public Double getPlantedArea() {
    return plantedArea;
  }

  /**
   * Sets planted area.
   *
   * @param plantedArea the planted area
   */
  public void setPlantedArea(Double plantedArea) {
    this.plantedArea = plantedArea;
  }
}
