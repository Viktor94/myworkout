package com.application.myworkout.services;

import com.application.myworkout.domains.User;
import com.application.myworkout.domains.dtos.UserLoginDTO;
import com.application.myworkout.domains.dtos.UserRegisterDTO;
import com.application.myworkout.exceptions.MissingFieldException;
import com.application.myworkout.exceptions.MissingParametersException;
import com.application.myworkout.exceptions.NoSuchUserException;
import com.application.myworkout.exceptions.WrongPasswordException;
import com.application.myworkout.exceptions.WrongUsernameException;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

  User findUserByEmail(String email);

  UserDetails loadUserByUsername(String username);

  void checkUserRegisterDTO(UserRegisterDTO userRegisterDTO)
      throws MissingFieldException, WrongUsernameException;

  Boolean isUserValidByEmail(String email);

  void addUser(User user);

  void validatePlayerLogin(UserLoginDTO dto)
      throws MissingParametersException, NoSuchUserException, WrongPasswordException;
}
