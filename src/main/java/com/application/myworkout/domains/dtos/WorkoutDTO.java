package com.application.myworkout.domains.dtos;

import com.application.myworkout.domains.Exercise;
import com.application.myworkout.domains.Workout;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkoutDTO {

  private Long id;
  private String day;

  @JsonBackReference
  private List<Exercise> exercises;

  public WorkoutDTO(Workout workout) {
    this.id = workout.getId();
    this.day = workout.getDay();
    this.exercises = workout.getExercises();
  }
}
