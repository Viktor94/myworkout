package com.application.myworkout.exceptions;

import com.application.myworkout.domains.ErrorMessage;
import java.util.List;

public class MissingFieldException extends RegisterException {

  private final List<String> list;

  public MissingFieldException(List<String> list) {
    this.list = list;
  }

  @Override
  public ErrorMessage getErrorMessage() {
    return new ErrorMessage("error", buildMessage());
  }

  public String buildMessage() {
    String fields = String.join(", ", list);

    return "Missing field(s): " + fields + "!";
  }
}
