package com.application.myworkout.exceptions;

import com.application.myworkout.domains.Message;
import java.util.List;

public class MissingFieldException extends RegisterException {

  private final List<String> list;

  public MissingFieldException(List<String> list) {
    this.list = list;
  }

  @Override
  public Message getErrorMessage() {
    return new Message("error", buildMessage());
  }

  public String buildMessage() {
    String fields = String.join(", ", list);

    return "Missing field(s): " + fields + "!";
  }
}
