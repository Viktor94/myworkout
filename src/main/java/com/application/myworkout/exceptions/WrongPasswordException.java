package com.application.myworkout.exceptions;

import com.application.myworkout.domains.Message;

public class WrongPasswordException extends LoginException {

  @Override
  public Message getErrorMessage() {
    return new Message("error", "Wrong password!");
  }
}
