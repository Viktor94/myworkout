package com.application.myworkout.services;

import com.application.myworkout.domains.User;
import com.application.myworkout.domains.dtos.UserLoginDTO;
import com.application.myworkout.domains.dtos.UserRegisterDTO;
import com.application.myworkout.exceptions.MissingFieldException;
import com.application.myworkout.exceptions.MissingParametersException;
import com.application.myworkout.exceptions.NoSuchUserException;
import com.application.myworkout.exceptions.WrongPasswordException;
import com.application.myworkout.exceptions.WrongUsernameException;
import com.application.myworkout.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User findUserByEmail(String email) {
    return userRepository.findUserByEmail(email).get();
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User temp = userRepository.findUserByEmail(username).get();
    return new org.springframework.security.core.userdetails.User(username, temp.getPassword(),
        new ArrayList<>());
  }

  @Override
  public Boolean isUserValidByEmail(String email) {
    return userRepository.findUserByEmail(email).isPresent();
  }

  @Override
  public void addUser(User user) {
    userRepository.save(user);
  }

  @Override
  public void checkUserRegisterDTO(UserRegisterDTO userRegisterDTO)
      throws MissingFieldException, WrongUsernameException {

    if (userRegisterDTO == null) {
      userRegisterDTO = new UserRegisterDTO();
      List<String> nullFields = userRegisterDTO.getNullFields();
      throw new MissingFieldException(nullFields);
    }

    List<String> temp = userRegisterDTO.getNullFields();
    if (temp.size() > 0) {
      throw new MissingFieldException(temp);
    }
    if (isUserValidByEmail(userRegisterDTO.getUsername())) {
      throw new WrongUsernameException("Username already taken, please choose an other one.");
    }

    User user = new User(userRegisterDTO.getUsername(),
        passwordEncoder().encode(userRegisterDTO.getPassword()));
    addUser(user);
  }

  @Override
  public void validatePlayerLogin(UserLoginDTO dto)
      throws MissingParametersException, NoSuchUserException, WrongPasswordException {

    if (dto == null) {
      dto = new UserLoginDTO();
      throw new MissingParametersException(dto);
    }

    if (dto.getNullFields().size() > 0) {
      throw new MissingParametersException(dto);
    }
    Optional<User> user = userRepository.findUserByEmail(dto.getEmail());

    if (user.isEmpty()) {
      throw new NoSuchUserException(dto);
    }

    if (!isEncodedPasswordMatches(dto, user.get())) {
      throw new WrongPasswordException();
    }
  }

  private Boolean isEncodedPasswordMatches(UserLoginDTO dto, User user) {
    return passwordEncoder().matches(dto.getPassword(), user.getPassword());
  }

  private PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
