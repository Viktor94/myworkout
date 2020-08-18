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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
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
  public ResponseEntity<?> postExercise(@RequestBody Exercise exercise, @PathVariable Long id) {
    Workout workout = workoutService.findById(id).get();
    exercise.setWorkout(workout);
    exerciseService.saveExercise(exercise);
    ExerciseDTO exerciseDTO = new ExerciseDTO(exercise);

    return ResponseEntity.ok(exerciseDTO);
  }

  @GetMapping("/exercises")
  public ResponseEntity<?> listExercises(Authentication authentication) {
    User user = userService.findUserByEmail(authentication.getName());

    return ResponseEntity.ok(exerciseService.listOfExercises(user));
  }

  @PostMapping("/delete/{id}")
  public ResponseEntity<?> deleteExercise(@PathVariable Long id) {
    if (id == null) {
      return ResponseEntity.badRequest().build();
    }
    exerciseService.deleteExercise(exerciseService.findExerciseById(id));

    return ResponseEntity.ok(new Message("Exercise deleted successfully!"));
  }
}
