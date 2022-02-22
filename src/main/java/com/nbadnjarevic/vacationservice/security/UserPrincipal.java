package com.nbadnjarevic.vacationservice.security;

import com.nbadnjarevic.vacationservice.domain.User;
import com.nbadnjarevic.vacationservice.domain.UserRole;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {

  private static final long serialVersionUID = -6818946193864908859L;
  private User user;
  private Long id;

  UserPrincipal(User user) {
    this.user = user;
    this.id = user.getId();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<>();
    UserRole role = this.user.getRole();
    GrantedAuthority authority = new SimpleGrantedAuthority(role.toString());
    authorities.add(authority);
    return authorities;
  }

  @Override
  public String getPassword() {
    return this.user.getPassword();
  }

  @Override
  public String getUsername() {
    return this.user.getUsername();
  }

  public Long getId() { return this.user.getId(); }

  public User getUser() {
    return this.user;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }


}
