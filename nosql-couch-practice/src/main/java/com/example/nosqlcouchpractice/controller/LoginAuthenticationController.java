package com.example.nosqlcouchpractice.controller;

import com.example.nosqlcouchpractice.payload.LoginPayload;
import com.example.nosqlcouchpractice.service.LoginService;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/noAuth")
public class LoginAuthenticationController {

  private LoginService loginService;

  public LoginAuthenticationController(LoginService loginService) {
    this.loginService = loginService;
  }

  @PostMapping(value = "/login", consumes = MimeTypeUtils.APPLICATION_JSON_VALUE,produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
  private String login(@RequestBody LoginPayload loginPayload) {
    return loginService.login(loginPayload.getUserName(),loginPayload.getPassword());
  }

  @PostMapping(value = "/signUp", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
  private void signUp(@RequestBody LoginPayload loginPayload) {
    loginService.createUser(loginPayload.getUserName(), loginPayload.getPassword());
  }
}
