package com.application.myworkout.repositories;

import com.application.myworkout.domains.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

  Optional<User> findUserByEmail(String email);

}
