package com.nbadnjarevic.vacationservice.security;

import com.nbadnjarevic.vacationservice.domain.User;
import com.nbadnjarevic.vacationservice.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalService implements UserDetailsService {

  private UserMapper userMapper;

  public UserPrincipalService(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userMapper.getByUsername(username);

    return new UserPrincipal(user);
  }

}
