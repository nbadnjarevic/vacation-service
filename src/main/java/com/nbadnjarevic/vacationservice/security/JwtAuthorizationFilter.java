package com.nbadnjarevic.vacationservice.security;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

import com.auth0.jwt.JWT;
import com.nbadnjarevic.vacationservice.domain.User;
import com.nbadnjarevic.vacationservice.mapper.UserMapper;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

  private UserMapper userMapper;

  JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserMapper userMapper) {
    super(authenticationManager);
    this.userMapper = userMapper;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    String header = request.getHeader(JwtProperties.HEADER_STRING);

    if (header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
      chain.doFilter(request, response);
      return;
    }

    Authentication authentication = getUsernamePasswordAuthentication(request);
    SecurityContextHolder.getContext().setAuthentication(authentication);

    chain.doFilter(request, response);

  }

  private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
    String token = request.getHeader(JwtProperties.HEADER_STRING)
        .replace(JwtProperties.TOKEN_PREFIX, "");

    if (token != null) {
      String username = JWT.require(HMAC512(JwtProperties.SECRET.getBytes()))
          .build()
          .verify(token)
          .getSubject();

      if (username != null) {
        User user = userMapper.getByUsername(username);
        UserPrincipal principal = new UserPrincipal(user);

        return new UsernamePasswordAuthenticationToken(username, null,
            principal.getAuthorities());
      }

      return null;
    }

    return null;
  }


}
