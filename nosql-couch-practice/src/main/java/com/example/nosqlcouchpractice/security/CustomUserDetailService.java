package com.example.nosqlcouchpractice.security;

import com.example.nosqlcouchpractice.entity.User;
import com.example.nosqlcouchpractice.repository.UserRepository;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailService implements UserDetailsService {

  private UserRepository repository;

  public CustomUserDetailService(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    Optional<User> userOptional = repository.findUserByUserName(userName);
    if (!userOptional.isPresent()) {
      throw new UsernameNotFoundException("user not found!");
    }
    return userOptional.get();
  }
}
