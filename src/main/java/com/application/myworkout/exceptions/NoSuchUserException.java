package com.application.myworkout.exceptions;

import com.application.myworkout.domains.ErrorMessage;
import com.application.myworkout.domains.dtos.UserLoginDTO;

public class NoSuchUserException extends LoginException {

  private final String username;

  public NoSuchUserException(UserLoginDTO dto) {
    this.username = dto.getEmail();
  }

  @Override
  public ErrorMessage getErrorMessage() {
    return new ErrorMessage("error", "No such user: " + this.username + "!");
  }
}
