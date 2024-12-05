package com.betrybe.agrix.entity;


import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.*;

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

  public Crop() {}

  public Crop( String name, Double plantedArea) {
    this.name = name;
    this.plantedArea = plantedArea;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Farm getFarmId() {
    return farmId;
  }

  public void setFarmId(Farm farmId) {
    this.farmId = farmId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPlantedArea() {
    return plantedArea;
  }

  public void setPlantedArea(Double plantedArea) {
    this.plantedArea = plantedArea;
  }
}
