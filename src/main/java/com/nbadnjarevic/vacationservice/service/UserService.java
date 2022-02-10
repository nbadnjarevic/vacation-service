package com.nbadnjarevic.vacationservice.service;

import com.nbadnjarevic.vacationservice.domain.User;
import com.nbadnjarevic.vacationservice.service.dto.ChangePasswordRequest;

public interface UserService {

  public User save(User user) throws Exception;

  public User getUser(Long userId);

  public User register(User user);

  public void changePassword(ChangePasswordRequest request);

}
