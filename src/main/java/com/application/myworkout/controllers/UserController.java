package com.application.myworkout.controllers;

import com.application.myworkout.domains.User;
import com.application.myworkout.domains.dtos.UserLoginDTO;
import com.application.myworkout.domains.dtos.UserRegisterDTO;
import com.application.myworkout.domains.dtos.UserRegisterResponseDTO;
import com.application.myworkout.exceptions.MissingFieldException;
import com.application.myworkout.exceptions.MissingParametersException;
import com.application.myworkout.exceptions.NoSuchUserException;
import com.application.myworkout.exceptions.WrongPasswordException;
import com.application.myworkout.exceptions.WrongUsernameException;
import com.application.myworkout.services.UserService;
import com.application.myworkout.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private final JwtUtil jwtUtil;
  private final UserService userService;

  @Autowired
  public UserController(JwtUtil jwtUtil, UserService userService) {
    this.jwtUtil = jwtUtil;
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(
      @RequestBody(required = false) UserRegisterDTO userRegisterDTO)
      throws WrongUsernameException, MissingFieldException {

    userService.checkUserRegisterDTO(userRegisterDTO);
    User user = userService.findUserByEmail(userRegisterDTO.getUsername());
    UserRegisterResponseDTO dto = new UserRegisterResponseDTO(user.getEmail());

    return new ResponseEntity<>(dto, HttpStatus.OK);
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody(required = false) UserLoginDTO dto)
      throws NoSuchUserException, MissingParametersException, WrongPasswordException {
    userService.validatePlayerLogin(dto);

    final UserDetails userDetails = userService.loadUserByUsername(dto.getEmail());
    final String jwt = jwtUtil.generateToken(userDetails, 60);

    return new ResponseEntity<>(jwt, HttpStatus.OK);
  }
}
