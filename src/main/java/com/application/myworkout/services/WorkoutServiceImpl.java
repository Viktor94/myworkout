package com.application.myworkout.services;

import com.application.myworkout.domains.User;
import com.application.myworkout.domains.Workout;
import com.application.myworkout.repositories.WorkoutRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkoutServiceImpl implements WorkoutService {

  private final WorkoutRepository workoutRepository;

  @Autowired
  public WorkoutServiceImpl(
      WorkoutRepository workoutRepository) {
    this.workoutRepository = workoutRepository;
  }

  @Override
  public List<Workout> findWorkoutByUser(User user) {
    return workoutRepository.findByUser(user);
  }
}
