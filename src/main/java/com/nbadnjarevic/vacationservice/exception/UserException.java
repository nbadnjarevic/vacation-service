package com.nbadnjarevic.vacationservice.exception;

public class UserException extends ApplicationException {

  private static final long serialVersionUID = -8105931444385603571L;

  public UserException(ApplicationExceptionCode code, String pattern, Object... args) {
    super(code, pattern, args);
  }

  public enum UserExceptionCode implements ApplicationExceptionCode {
    USERNAME_TAKEN, USERNAME_NOT_FOUND, MAIL_NOT_FOUND, INVALID_MAIL, USER_NOT_FOUND, INVALID_PASSWORD
  }

}
