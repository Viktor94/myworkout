package com.application.myworkout.domains;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String email;
  private String password;

  @OneToMany(mappedBy = "user")
  private List<Workout> workouts;

  public User() {
  }

  public User(String email, String password, List<Workout> workouts, Long id) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.workouts = workouts;
  }

  public User(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Workout> getWorkouts() {
    return workouts;
  }

  public void setWorkouts(List<Workout> workouts) {
    this.workouts = workouts;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
