package com.nbadnjarevic.vacationservice.service.setup;

import com.nbadnjarevic.vacationservice.domain.User;
import com.nbadnjarevic.vacationservice.domain.UserRole;
import com.nbadnjarevic.vacationservice.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserSetup {

  @Autowired
  private UserMapper userMapper;

  public User testUser() {
    return User.builder().username("test").password("123").role(UserRole.REGULAR).build();
  }

  public User testUserWithoutRole() {
    return User.builder().username("test").password("123").build();
  }

}
