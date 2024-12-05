package com.betrybe.agrix.entity;


import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.*;

@Entity
@Table(name = "farms")
@EntityListeners(AuditingEntityListener.class)
public class Farm {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private Double size;

  public Farm() {}

  public Farm(String name, Double size) {
    this.name = name;
    this.size = size;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getSize() {
    return size;
  }

  public void setSize(Double size) {
    this.size = size;
  }
}
