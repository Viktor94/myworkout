package com.application.myworkout.exceptions;

import com.application.myworkout.domains.ErrorMessage;

public class WrongUsernameException extends RegisterException {

  private final String parameters;

  public WrongUsernameException(String parameters) {
    this.parameters = parameters;
  }

  @Override
  public ErrorMessage getErrorMessage() {
    return new ErrorMessage("error", parameters);
  }
}
