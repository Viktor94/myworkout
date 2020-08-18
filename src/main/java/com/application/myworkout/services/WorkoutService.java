package com.application.myworkout.services;

import com.application.myworkout.domains.User;
import com.application.myworkout.domains.Workout;
import java.util.List;
import java.util.Optional;

public interface WorkoutService {

  List<Workout> findWorkoutByUser(User user);

  void saveWorkout(Workout workout);

  void saveWorkoutPrefixedValues(Workout workout);

  Optional<Workout> findById(Long id);

  void deleteWorkoutById(Long id);
}
