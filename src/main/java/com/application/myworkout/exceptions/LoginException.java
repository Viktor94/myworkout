package com.application.myworkout.exceptions;

import com.application.myworkout.domains.ErrorMessage;

public abstract class LoginException extends Exception {

  public abstract ErrorMessage getErrorMessage();
}
