package com.application.myworkout.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WorkoutExceptionHandler {

  public WorkoutExceptionHandler() {
  }

  @ExceptionHandler(value = WrongUsernameException.class)
  public ResponseEntity<?> wrongUsernameException(WrongUsernameException e) {
    return new ResponseEntity<>(e.getErrorMessage(), HttpStatus.CONFLICT);
  }

  @ExceptionHandler(value = MissingFieldException.class)
  public ResponseEntity<?> missingFieldException(MissingFieldException e) {
    return new ResponseEntity<>(e.getErrorMessage(), HttpStatus.BAD_REQUEST);
  }
}
