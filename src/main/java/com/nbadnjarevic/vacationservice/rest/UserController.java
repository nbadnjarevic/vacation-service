package com.nbadnjarevic.vacationservice.rest;

import com.nbadnjarevic.vacationservice.domain.User;
import com.nbadnjarevic.vacationservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/registration")
  private ResponseEntity<?> register(@RequestBody User user) {
    return ResponseEntity.ok(userService.register(user));
  }

}
