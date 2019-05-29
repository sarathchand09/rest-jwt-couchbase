package com.example.nosqlcouchpractice.service;

import com.example.nosqlcouchpractice.entity.User;
import com.example.nosqlcouchpractice.repository.UserRepository;
import com.example.nosqlcouchpractice.security.JwtTokenProvider;
import java.util.Collections;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LoginService {

  private JwtTokenProvider jwtTokenProvider;

  private UserRepository userRepository;

  private BCryptPasswordEncoder encoder;

  public LoginService(
      JwtTokenProvider jwtTokenProvider,
      UserRepository userRepository,
      BCryptPasswordEncoder encoder) {
    this.jwtTokenProvider = jwtTokenProvider;
    this.userRepository = userRepository;
    this.encoder = encoder;
  }

  public String login(final String userName, final String password) {
    Optional<User> userOptional = userRepository.findUserByUserName(userName);

    // User not found
    if (!userOptional.isPresent()) {
      throw new RuntimeException("userName or password is wrong!");
    }

    User user = userOptional.get();

    // Password is wrong
    if (!encoder.matches(password, user.getPassword())) {
      throw new RuntimeException("userName or password is wrong!");
    }

    return jwtTokenProvider.createToken(userName, Collections.EMPTY_LIST);
  }

  public void createUser(final String userName, final String password) {
    Optional<User> userOptional = userRepository.findUserByUserName(userName);

    if (userOptional.isPresent() && userOptional.get().getUsername() != null) {
      log.info("SignUp user already exists, user: " + userName);
      throw new RuntimeException("user already exists, choose another userName...");
    }

    userRepository.save(getUserPayload(userName, password));
    log.info("SignUp successful for the user: " + userName);
  }

  private User getUserPayload(final String userName, final String password) {
    User user = new User();
    user.setUserName(userName);
    user.setPassword(encoder.encode(password));
    return user;
  }
}
