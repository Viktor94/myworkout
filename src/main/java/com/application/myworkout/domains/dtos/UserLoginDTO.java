package com.application.myworkout.domains.dtos;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class UserLoginDTO {

  private String email;
  private String password;

  public UserLoginDTO() {
  }

  public UserLoginDTO(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public List<String> getNullFields() {
    List<String> nullFields = new ArrayList<>();
    for (Field f : getClass().getDeclaredFields()) {
      try {
        if (f.get(this) == null) {
          nullFields.add(f.getName());
        }
      } catch (IllegalAccessException ignored) {
      }
    }

    return nullFields;
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
}
