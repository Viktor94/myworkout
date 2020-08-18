package com.application.myworkout.exceptions;

import com.application.myworkout.domains.Message;

public class WrongUsernameException extends RegisterException {

  private final String parameters;

  public WrongUsernameException(String parameters) {
    this.parameters = parameters;
  }

  @Override
  public Message getErrorMessage() {
    return new Message("error", parameters);
  }
}
