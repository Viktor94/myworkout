package com.application.myworkout.controllers;

import com.application.myworkout.domains.Exercise;
import com.application.myworkout.domains.Workout;
import com.application.myworkout.services.ExerciseService;
import com.application.myworkout.services.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exercise")
public class ExerciseController {

  private final WorkoutService workoutService;
  private final ExerciseService exerciseService;

  @Autowired
  public ExerciseController(WorkoutService workoutService, ExerciseService exerciseService) {
    this.workoutService = workoutService;
    this.exerciseService = exerciseService;
  }

  @PostMapping("/add/{id}")
  public ResponseEntity<?> postExercise(@RequestBody Exercise exercise,@PathVariable Long id) {
    Workout workout = workoutService.findById(id).get();
    exercise.setWorkout(workout);
    exerciseService.saveExercise(exercise);

    return ResponseEntity.ok(exercise);
  }
}
