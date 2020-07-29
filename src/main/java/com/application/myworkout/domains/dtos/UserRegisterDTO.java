package com.application.myworkout.domains.dtos;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class UserRegisterDTO {

  private String username;
  private String password;

  public UserRegisterDTO() {
  }

  public UserRegisterDTO(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<String> getNullFields() {
    List<String> nullFields = new ArrayList<>();
    for (Field f : getClass().getDeclaredFields()) {
      try {
        if (f.get(this) == null || f.get(this).equals("")) {
          nullFields.add(f.getName());
        }
      } catch (IllegalAccessException ignored) {
      }
    }
    return nullFields;
  }
}
