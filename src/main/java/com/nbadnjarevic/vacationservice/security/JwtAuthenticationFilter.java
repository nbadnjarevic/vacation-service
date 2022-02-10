package com.nbadnjarevic.vacationservice.security;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nbadnjarevic.vacationservice.domain.User;
import com.nbadnjarevic.vacationservice.service.dto.AuthData;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private AuthenticationManager authenticationManager;

  JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException {
    LoginModel credentials = null;
    try {
      credentials = new ObjectMapper().readValue(request.getInputStream(), LoginModel.class);
    } catch (IOException e) {
      e.printStackTrace();
    }

    assert credentials != null;
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
        credentials.getUsername(),
        credentials.getPassword(),
        new ArrayList<>()
    );

    return authenticationManager.authenticate(authenticationToken);
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request,
      HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
    UserPrincipal principal = (UserPrincipal) authResult.getPrincipal();

    String token = JWT.create()
        .withSubject(principal.getUsername())
        .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
        .sign(HMAC512(JwtProperties.SECRET));

    response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + token);
    User user = principal.getUser();
    AuthData authData = AuthData.builder().role(user.getRole()).id(user.getId()).build();
    response.getWriter().write(new ObjectMapper().writeValueAsString(authData));
  }


}
