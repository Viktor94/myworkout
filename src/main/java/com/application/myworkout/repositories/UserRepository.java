package com.application.myworkout.repositories;

import com.application.myworkout.domains.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

  User findUserByEmail(String email);
}
