package com.application.myworkout.exceptions;

import com.application.myworkout.domains.ErrorMessage;
import com.application.myworkout.domains.dtos.UserLoginDTO;
import java.util.List;

public class MissingParametersException extends LoginException {

  private final UserLoginDTO dto;

  public MissingParametersException(UserLoginDTO dto) {
    this.dto = dto;
  }

  @Override
  public ErrorMessage getErrorMessage() {
    return new ErrorMessage("error", buildMessage());
  }

  public String buildMessage() {
    List<String> nullFields = dto.getNullFields();
    String fields = String.join(", ", nullFields);

    return "Missing field(s): " + fields + "!";
  }
}
