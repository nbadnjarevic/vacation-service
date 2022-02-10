package com.nbadnjarevic.vacationservice.service.impl;

import com.nbadnjarevic.vacationservice.domain.User;
import com.nbadnjarevic.vacationservice.domain.UserRole;
import com.nbadnjarevic.vacationservice.exception.UserException;
import com.nbadnjarevic.vacationservice.exception.UserException.UserExceptionCode;
import com.nbadnjarevic.vacationservice.mapper.UserMapper;
import com.nbadnjarevic.vacationservice.service.UserService;
import com.nbadnjarevic.vacationservice.service.dto.ChangePasswordRequest;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@RequiredArgsConstructor
@Service
@Transactional
@Validated
public class UserServiceImpl implements UserService {

  final UserMapper userMapper;

  @Autowired
  private PasswordEncoder encoder;

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
  @Cacheable(value = "userCache")
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

  @Override
  public void changePassword(ChangePasswordRequest request) {
    User user = userMapper.getById(request.getId());
    if(user == null) {
      throw new UserException(UserExceptionCode.USER_NOT_FOUND, "User not found!");
    }
    if(!encoder.matches(request.getOldPassword(), user.getPassword())) {
      throw new UserException(UserExceptionCode.INVALID_PASSWORD, "Invalid old password! Please try again.");
    }
    user.setPassword(encoder.encode(request.getNewPassword()));
    userMapper.save(user);
  }
}
