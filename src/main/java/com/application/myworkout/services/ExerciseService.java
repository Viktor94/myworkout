package com.application.myworkout.services;

import com.application.myworkout.domains.Exercise;
import com.application.myworkout.domains.User;
import java.util.List;

public interface ExerciseService {

  void saveExercise(Exercise exercise);

  List<Exercise> listOfExercises(User user);

  Exercise findExerciseById(Long id);

  void deleteExercise(Exercise exercise);

  void deleteExerciseById(Long id);
}
