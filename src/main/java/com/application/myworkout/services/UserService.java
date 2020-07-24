package com.application.myworkout.services;

import com.application.myworkout.domains.User;

public interface UserService {

  User findUserByEmail(String email);
}
