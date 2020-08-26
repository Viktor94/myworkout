package com.application.myworkout.controllers;

import com.application.myworkout.domains.Exercise;
import com.application.myworkout.domains.Message;
import com.application.myworkout.domains.User;
import com.application.myworkout.domains.Workout;
import com.application.myworkout.domains.dtos.ExerciseDTO;
import com.application.myworkout.services.ExerciseService;
import com.application.myworkout.services.UserService;
import com.application.myworkout.services.WorkoutService;
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
@RequestMapping("/exercise")
public class ExerciseController {

  private final WorkoutService workoutService;
  private final ExerciseService exerciseService;
  private final UserService userService;

  @Autowired
  public ExerciseController(WorkoutService workoutService, ExerciseService exerciseService,
      UserService userService) {
    this.workoutService = workoutService;
    this.exerciseService = exerciseService;
    this.userService = userService;
  }

  @PostMapping("/add/{id}")
  public ResponseEntity<?> postExercise(@RequestBody Exercise exercise, @PathVariable Long id,
      Authentication authentication) {
    if (id == null || workoutService.findById(id).isEmpty()) {
      return ResponseEntity.badRequest().build();
    }
    Workout workout = workoutService.findById(id).get();
    exercise.setWorkout(workout);
    exercise.setUser(getUser(authentication));
    exerciseService.saveExercise(exercise);
    ExerciseDTO exerciseDTO = new ExerciseDTO(exercise);

    return ResponseEntity.ok(exerciseDTO);
  }

  @GetMapping("/exercises")
  public ResponseEntity<?> listExercises(Authentication authentication) {
    return ResponseEntity.ok(exerciseService.listOfExercises(getUser(authentication)));
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> deleteExercise(@PathVariable Long id) {
    if (id == null) {
      return ResponseEntity.badRequest().build();
    }
    exerciseService.deleteExercise(exerciseService.findExerciseById(id));

    return ResponseEntity.ok(new Message("Exercise deleted successfully!"));
  }

  private User getUser(Authentication authentication) {
    return userService.findUserByEmail(authentication.getName());
  }

  @PutMapping("/edit/{id}")
  public ResponseEntity<?> editExercise(@RequestBody Exercise exercise, @PathVariable Long id) {
    if (id == null) {
      return ResponseEntity.badRequest().build();
    }
    exerciseService.deleteExerciseById(id);
    exerciseService.saveExercise(exercise);

    return ResponseEntity.ok(new Message("Workout has been modified successfully!"));
  }
}
