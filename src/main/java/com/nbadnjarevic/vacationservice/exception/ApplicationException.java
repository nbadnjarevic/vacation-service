package com.nbadnjarevic.vacationservice.exception;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {

    final ApplicationExceptionCode code;

    public ApplicationException(
        ApplicationExceptionCode code, String pattern, Object... args) {
        super(String.format("[%s] ", code) + String.format(pattern, args), extractCause(args));
        this.code = code;
    }

    static Throwable extractCause(Object... args) {
        if (args == null || args.length == 0) {
            return null;
        }
        if (args[args.length - 1] instanceof Throwable) {
            return (Throwable) args[args.length - 1];
        }
        return null;
    }

}