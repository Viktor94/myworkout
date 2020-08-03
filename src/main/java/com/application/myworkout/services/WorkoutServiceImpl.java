package com.application.myworkout.services;

import com.application.myworkout.domains.User;
import com.application.myworkout.domains.Workout;
import com.application.myworkout.repositories.WorkoutRepository;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkoutServiceImpl implements WorkoutService {

  private final WorkoutRepository workoutRepository;

  @Autowired
  public WorkoutServiceImpl(WorkoutRepository workoutRepository) {
    this.workoutRepository = workoutRepository;
  }

  @Override
  public List<Workout> findWorkoutByUser(User user) {
    return workoutRepository.findByUser(user);
  }

  @Override
  public Workout saveWorkout(Workout workout) {
    workoutRepository.save(workout);
    return workout;
  }

  @Override
  public Workout saveWorkoutPrefixedValues(Workout workout) {
    workout.setDay(getDayStringNew());
    workoutRepository.save(workout);
    return workout;
  }

  @Override
  public Optional<Workout> findById(Long id) {
    return workoutRepository.findById(id);
  }

  private String getDayStringNew() {
    Locale locale = new Locale.Builder().setLanguage("en").setRegion("UK").build();
    LocalDate localDate = LocalDate.now();
    DayOfWeek day = localDate.getDayOfWeek();
    return day.getDisplayName(TextStyle.FULL, locale);
  }
}
