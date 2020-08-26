package com.application.myworkout.controllers;

import com.application.myworkout.domains.Message;
import com.application.myworkout.domains.User;
import com.application.myworkout.domains.Workout;
import com.application.myworkout.domains.dtos.WorkoutDTO;
import com.application.myworkout.services.UserService;
import com.application.myworkout.services.WorkoutService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workout")
public class WorkoutController {

  private final UserService userService;
  private final WorkoutService workoutService;

  @Autowired
  public WorkoutController(UserService userService, WorkoutService workoutService) {
    this.userService = userService;
    this.workoutService = workoutService;
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
    workoutService.saveWorkoutPrefixedValues(workout);
    WorkoutDTO workoutDTO = new WorkoutDTO(workout);

    return ResponseEntity.ok(workoutDTO);
  }

  @GetMapping("/workouts")
  public ResponseEntity<?> getUserWorkouts(Authentication authentication) {
    User user = userService.findUserByEmail(authentication.getName());
    List<WorkoutDTO> workoutDTOS = new ArrayList<>();
    List<Workout> workouts = workoutService.findWorkoutByUser(user);

    for (Workout workout : workouts) {
      workoutDTOS.add(new WorkoutDTO(workout));
    }
    return ResponseEntity.ok(workoutDTOS);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> deleteWorkout(@PathVariable Long id) {
    if (id == null) {
      return ResponseEntity.badRequest().build();
    }
    workoutService.deleteWorkoutById(id);

    return ResponseEntity.ok(new Message("Workout deleted successfully!"));
  }

  @PutMapping("/edit/{id}")
  public ResponseEntity<?> editWorkout(@PathVariable Long id, @RequestBody Workout workout) {
    if (id == null) {
      return ResponseEntity.badRequest().build();
    }
    workoutService.deleteWorkoutById(id);
    workoutService.saveWorkout(workout);

    return ResponseEntity.ok(new Message("Workout has been modified successfully!"));
  }
}
