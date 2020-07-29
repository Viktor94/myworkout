package com.application.myworkout.exceptions;

import com.application.myworkout.domains.ErrorMessage;

public class WrongPasswordException extends LoginException {

  @Override
  public ErrorMessage getErrorMessage() {
    return new ErrorMessage("error", "Wrong password!");
  }
}
