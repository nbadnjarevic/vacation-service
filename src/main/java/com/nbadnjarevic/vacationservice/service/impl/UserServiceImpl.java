package com.nbadnjarevic.vacationservice.service.impl;

import com.nbadnjarevic.vacationservice.domain.User;
import com.nbadnjarevic.vacationservice.mapper.UserMapper;
import com.nbadnjarevic.vacationservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@RequiredArgsConstructor
@Service
@Transactional
@Validated
public class UserServiceImpl implements UserService {

  final UserMapper userMapper;

  @Override
  public User save(User user) throws Exception {
    try{
      user = userMapper.save(user);
      return user;
    } catch(Exception e) {
      throw new Exception("User: " + user.getUsername() + " already exists");
    }
  }

  @Override
  public User getUser(Long userId) {
    return userMapper.getById(userId);
  }
}
