package com.application.myworkout.exceptions;

import com.application.myworkout.domains.Message;

public abstract class RegisterException extends Exception {

  public abstract Message getErrorMessage();
}
