package com.application.myworkout.controllers;

import com.application.myworkout.domains.User;
import com.application.myworkout.domains.Workout;
import com.application.myworkout.interceptors.ExtractUserInterceptor;
import com.application.myworkout.services.UserService;
import com.application.myworkout.services.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workout")
public class WorkoutController {

  private final UserService userService;
  private final WorkoutService workoutService;
  private final ExtractUserInterceptor extractUserInterceptor;

  @Autowired
  public WorkoutController(UserService userService, WorkoutService workoutService,
      ExtractUserInterceptor extractUserInterceptor) {
    this.userService = userService;
    this.workoutService = workoutService;
    this.extractUserInterceptor = extractUserInterceptor;
  }

  @PostMapping("/add")
  public ResponseEntity<?> createWorkout(@RequestBody Workout workout,
      Authentication authentication) {
    if (workout == null) {
      return ResponseEntity.badRequest().build();
    }

    User user = userService.findUserByEmail(authentication.getName());
    workout.setUser(user);
    workoutService.saveWorkout(workout);

    return ResponseEntity.ok(workoutService.saveWorkoutPrefixedValues(workout));
  }
}
