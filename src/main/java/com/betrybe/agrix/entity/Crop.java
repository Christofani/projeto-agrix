package com.betrybe.agrix.entity;


import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.*;

import java.time.*;
import java.util.*;

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

  private String name;
  private Double plantedArea;
  private LocalDate plantedDate;
  private LocalDate harvestDate;

  @ManyToOne
  @JoinColumn(name = "farm_id")
  private Farm farmId;


  @ManyToMany
  @JoinTable(
          name = "crop_fertilizer",
          joinColumns = @JoinColumn(name = "crop_id"),
          inverseJoinColumns = @JoinColumn(name ="fertilizer_id")
  )
  private List<Fertilizer> fertilizers;




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
  public Crop( String name, Double plantedArea, LocalDate plantedDate, LocalDate harvestDate ) {
    this.name = name;
    this.plantedArea = plantedArea;
    this.plantedDate = plantedDate;
    this.harvestDate = harvestDate;
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

  public LocalDate getPlantedDate() {
    return plantedDate;
  }

  public void setPlantedDate(LocalDate plantedDate) {
    this.plantedDate = plantedDate;
  }

  public LocalDate getHarvestDate() {
    return harvestDate;
  }

  public void setHarvestDate(LocalDate harvestDate) {
    this.harvestDate = harvestDate;
  }

  public List<Fertilizer> getFertilizers() {
    return fertilizers;
  }

  public void setFertilizers(List<Fertilizer> fertilizers) {
    this.fertilizers = fertilizers;
  }
}
