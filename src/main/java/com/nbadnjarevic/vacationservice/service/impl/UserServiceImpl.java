package com.nbadnjarevic.vacationservice.service.impl;

import com.nbadnjarevic.vacationservice.domain.User;
import com.nbadnjarevic.vacationservice.domain.UserRole;
import com.nbadnjarevic.vacationservice.exception.UserException;
import com.nbadnjarevic.vacationservice.exception.UserException.UserExceptionCode;
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
      throw new UserException(UserExceptionCode.USERNAME_TAKEN, "Username is already taken!", user.getUsername());
    }
  }

  @Override
  public User getUser(Long userId) {
    return userMapper.getById(userId);
  }

  @Override
  public User register(User user) {
    User existingUser = userMapper.getByUsername(user.getUsername());
    if(existingUser != null) {
      throw new UserException(UserExceptionCode.USERNAME_TAKEN, "Username is already taken!", user.getUsername());
    }
    user.setRole(UserRole.REGULAR);
    return userMapper.save(user);
  }
}
