package com.application.myworkout.services;

import com.application.myworkout.domains.Exercise;
import com.application.myworkout.repositories.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseServiceImpl implements ExerciseService {

  private final ExerciseRepository exerciseRepository;

  @Autowired
  public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
    this.exerciseRepository = exerciseRepository;
  }

  @Override
  public Exercise saveExercise(Exercise exercise) {
    exerciseRepository.save(exercise);
    return exercise;
  }
}
