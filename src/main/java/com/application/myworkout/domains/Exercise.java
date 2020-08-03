package com.application.myworkout.domains;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Exercise {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String exercise;
  private Integer repetitions;
  private Double weight;

  @ManyToOne
  private Workout workout;

  public Exercise() {
  }

  public Exercise(String exercise, Integer repetitions, Double weight) {
    this.exercise = exercise;
    this.repetitions = repetitions;
    this.weight = weight;
  }
}
