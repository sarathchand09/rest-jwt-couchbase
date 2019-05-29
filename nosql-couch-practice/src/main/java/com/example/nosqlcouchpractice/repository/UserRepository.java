package com.example.nosqlcouchpractice.repository;

import com.example.nosqlcouchpractice.entity.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

  Optional<User> findUserByUserName(String userName);
}
