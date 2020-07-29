package com.application.myworkout.controllers;

import com.application.myworkout.domains.Workout;
import com.application.myworkout.services.UserService;
import com.application.myworkout.services.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkoutController {

  private final UserService userService;
  private final WorkoutService workoutService;

  @Autowired
  public WorkoutController(UserService userService,
      WorkoutService workoutService) {
    this.userService = userService;
    this.workoutService = workoutService;
  }

  @PostMapping("/workout")
  public ResponseEntity<?> createWorkout(@RequestBody Workout workout) {
    if (workout == null) {
      return ResponseEntity.badRequest().build();
    }
    return ResponseEntity.ok(workoutService.saveWorkoutPrefixedValues(workout));
  }
}
