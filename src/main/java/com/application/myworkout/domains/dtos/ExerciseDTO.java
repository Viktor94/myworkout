package com.application.myworkout.domains.dtos;

import com.application.myworkout.domains.Exercise;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExerciseDTO {

  private Long id;
  private String exercise;
  private Integer repetitions;
  private Double weight;

  public ExerciseDTO(Exercise exercise) {
    this.id = exercise.getId();
    this.exercise = exercise.getExercise();
    this.repetitions = exercise.getRepetitions();
    this.weight = exercise.getWeight();
  }
}
