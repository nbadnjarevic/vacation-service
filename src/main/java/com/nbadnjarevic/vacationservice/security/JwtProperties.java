package com.nbadnjarevic.vacationservice.security;

class JwtProperties {

  static final String SECRET = "TEMP";
  static final int EXPIRATION_TIME = 864_000_000;
  static final String TOKEN_PREFIX = "Bearer ";
  static final String HEADER_STRING = "Authorization";

  
}
