package com.example.nosqlcouchpractice.controller;

import com.example.nosqlcouchpractice.dto.SamplTestDto;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class SampleController {

  @GetMapping(value = "/sample/test", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
  private SamplTestDto test() {
    return new SamplTestDto("invoked service call");
  }

}
