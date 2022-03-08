package com.nbadnjarevic.vacationservice.rest;

import com.nbadnjarevic.vacationservice.domain.User;
import com.nbadnjarevic.vacationservice.service.UserService;
import com.nbadnjarevic.vacationservice.service.dto.ChangePasswordRequest;
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

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/registration")
  private ResponseEntity<?> register(@RequestBody User user) {
    return ResponseEntity.ok(userService.register(user));
  }

  @PostMapping("/change-password")
  private void changePassword(@RequestBody ChangePasswordRequest request) {
    userService.changePassword(request);
  }

}
