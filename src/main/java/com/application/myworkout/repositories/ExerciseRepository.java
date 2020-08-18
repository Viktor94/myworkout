package com.application.myworkout.repositories;

import com.application.myworkout.domains.Exercise;
import com.application.myworkout.domains.Workout;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends CrudRepository<Exercise, Long> {

  List<Exercise> findByWorkout(Workout workout);
}
