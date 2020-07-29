package com.application.myworkout.domains.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterResponseDTO {

  private String username;

  public UserRegisterResponseDTO() {
  }

  public UserRegisterResponseDTO(String username) {
    this.username = username;
  }
}
