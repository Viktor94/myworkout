package com.application.myworkout.domains;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String email;
  private String password;

  @OneToMany(mappedBy = "user")
  private List<Workout> workouts;

  @OneToMany(mappedBy = "user")
  private List<Exercise> exercises;

  public User() {
  }

  public User(String email, String password) {
    this.email = email;
    this.password = password;
  }
}
