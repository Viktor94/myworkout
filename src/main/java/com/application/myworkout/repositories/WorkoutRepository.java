package com.application.myworkout.repositories;

import com.application.myworkout.domains.User;
import com.application.myworkout.domains.Workout;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutRepository extends CrudRepository<Workout, Long> {

  List<Workout> findByUser(User user);

  Optional<Workout> findById(Long id);
}
