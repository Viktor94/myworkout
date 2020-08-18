package com.application.myworkout.services;

import com.application.myworkout.domains.Exercise;
import com.application.myworkout.domains.User;
import com.application.myworkout.repositories.ExerciseRepository;
import java.util.List;
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
  public void saveExercise(Exercise exercise) {
    exerciseRepository.save(exercise);
  }

  @Override
  public List<Exercise> listOfExercises(User user) {
    return user.getExercises();
  }

  @Override
  public Exercise findExerciseById(Long id) {
    return exerciseRepository.findById(id).get();
  }

  @Override
  public void deleteExercise(Exercise exercise) {
    exerciseRepository.delete(exercise);
  }
}
