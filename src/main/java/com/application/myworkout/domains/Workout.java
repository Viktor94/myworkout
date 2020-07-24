package com.application.myworkout.domains;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Workout {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String day;
  private String exercise;
  private String repetitions;

  @ManyToOne
  private User user;

  public Workout() {
  }

  public Workout(String day, String exercise, String repetitions,
      User user) {
    this.day = day;
    this.exercise = exercise;
    this.repetitions = repetitions;
    this.user = user;
  }

  public String getDay() {
    return day;
  }

  public void setDay(String day) {
    this.day = day;
  }

  public String getExercise() {
    return exercise;
  }

  public void setExercise(String exercise) {
    this.exercise = exercise;
  }

  public String getRepetitions() {
    return repetitions;
  }

  public void setRepetitions(String repetitions) {
    this.repetitions = repetitions;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
