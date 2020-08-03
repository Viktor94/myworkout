package com.application.myworkout.services;

import com.application.myworkout.domains.User;
import com.application.myworkout.domains.Workout;
import java.util.List;
import java.util.Optional;

public interface WorkoutService {

  List<Workout> findWorkoutByUser(User user);

  Workout saveWorkout(Workout workout);

  Workout saveWorkoutPrefixedValues(Workout workout);

  Optional<Workout> findById(Long id);
}
